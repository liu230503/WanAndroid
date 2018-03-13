package org.lmy.open.wanandroid.core.widget;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import org.lmy.open.wanandroid.R;
import org.lmy.open.wanandroid.business.main.adapter.ClassAAdapter;
import org.lmy.open.wanandroid.business.main.adapter.ClassBAdapter;
import org.lmy.open.wanandroid.business.main.bean.BeanRespClassify;
import org.lmy.open.wanandroid.business.main.bean.BeanRespClassifyChildren;
import org.lmy.open.wanandroid.core.base.OnItemClickListener;

import java.util.List;


/**********************************************************************
 *
 *
 * @类名 CustomClassDialog
 * @包名 org.lmy.open.wanandroid.core.widget
 * @author lmy
 * @创建日期 2018/3/12
 ***********************************************************************/
public class CustomClassDialog extends Dialog {
    /**
     * Dialog 根视图
     */
    private Window mWindow;
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 一级分类适配器
     */
    private ClassAAdapter mClassAAdapter;
    /**
     * 一级分类列表
     */
    private RecyclerView mClassAView;
    /**
     * 二级分类适配器
     */
    private ClassBAdapter mClassBAdapter;
    /**
     * 二级分类列表
     */
    private RecyclerView mClassBView;

    private View mTopView;

    private View mLeftView;
    /**
     * 监听器
     */
    private OnNavigationListener mOnNavigationListener;

    /**
     * 构造函数
     *
     * @param context 上下文
     */
    public CustomClassDialog(Context context) {
        this(context, R.style.user_cumstem_dialog);
    }

    /**
     * 构造器
     *
     * @param context 上下文
     * @param theme   风格样式
     */
    @SuppressLint("ResourceType")
    private CustomClassDialog(Context context, int theme) {
        super(context, theme);
        initData(context);
        initDialog(context);
    }

    /**
     * 初始化数据
     *
     * @param context 上下文
     */
    private void initData(Context context) {
        mClassAAdapter = new ClassAAdapter(context, new OnItemClickListener() {
            @Override
            public void onItemClick(View view) {
                TextView mNameView = view.findViewById(R.id.tv_name);
                int position = (Integer) mNameView.getTag();
                mClassAAdapter.setSelectedItem(position);
                mClassBAdapter.clear();
                mClassBAdapter.addFooterItem(mClassAAdapter.getItem(position));
            }

            @Override
            public void onItemLongClick(View view) {

            }
        });
        mClassBAdapter = new ClassBAdapter(context, new OnItemClickListener() {
            @Override
            public void onItemClick(View view) {
                TextView mNameView = view.findViewById(R.id.tv_name);
                int position = (Integer) mNameView.getTag();
                if (mOnNavigationListener != null) {
                    mOnNavigationListener.onSelect(mClassBAdapter.getItem(position));
                }
            }

            @Override
            public void onItemLongClick(View view) {

            }
        });
    }


    /**
     * 初始化
     *
     * @param context 上下文
     */
    private void initDialog(Context context) {
        setCancelable(true);
        show();
        mWindow = getWindow();
        WindowManager.LayoutParams params = mWindow.getAttributes();
        mWindow.setGravity(Gravity.TOP);
        mWindow.setWindowAnimations(R.style.dialogWindowAnim);
        params.flags = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        params.x = 0;
        params.width = mWindow.getWindowManager().getDefaultDisplay().getWidth();
        params.height = mWindow.getWindowManager().getDefaultDisplay().getHeight();
        mWindow.setAttributes(params);
        mWindow.setContentView(R.layout.layout_navigation_end_header);
        initView(context);
    }

    /**
     * 初始化view
     *
     * @param context 上下文
     */
    private void initView(Context context) {
        mClassAView = mWindow.findViewById(R.id.rcv_classA);
        mClassBView = mWindow.findViewById(R.id.rcv_classB);
        mTopView = mWindow.findViewById(R.id.v_top);
        mLeftView = mWindow.findViewById(R.id.v_tra);
        mClassAView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mClassAView.setAdapter(mClassAAdapter);
        mClassBView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mClassBView.setAdapter(mClassBAdapter);
        mTopView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        mLeftView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    @Override
    public void show() {
        if (isShowing()) {
            return;
        }
        super.show();
        if (mOnNavigationListener != null) {
            mOnNavigationListener.onShow();
        }
    }

    @Override
    public void dismiss() {
        if (!isShowing()) {
            return;
        }
        super.dismiss();
        if (mOnNavigationListener != null) {
            mOnNavigationListener.onHide();
        }
    }

    /**
     * 设置数据
     *
     * @param classifies 数据
     */
    public void setData(List<BeanRespClassify> classifies) {
        mClassAAdapter.clear();
        mClassAAdapter.addFooterItem(classifies);
        mClassBAdapter.clear();
        mClassBAdapter.addFooterItem(classifies.get(0).getChildren());
    }

    public void setOnNavigationListener(OnNavigationListener onNavigationListener) {
        mOnNavigationListener = onNavigationListener;
    }

    /**
     * 监听
     */
    public interface OnNavigationListener {
        /**
         * 打开
         */
        void onShow();

        /**
         * 关闭
         */
        void onHide();

        /**
         * 选中
         *
         * @param children 选中项
         */
        void onSelect(BeanRespClassifyChildren children);
    }

}
