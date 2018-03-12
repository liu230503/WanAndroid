package org.lmy.open.wanandroid.core.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.lmy.open.utillibrary.AnimationHelper;
import org.lmy.open.wanandroid.R;
import org.lmy.open.wanandroid.business.course.bean.BeanRespClassify;
import org.lmy.open.wanandroid.business.course.bean.BeanRespClassifyChildren;
import org.lmy.open.wanandroid.business.main.adapter.ClassAAdapter;
import org.lmy.open.wanandroid.business.main.adapter.ClassBAdapter;
import org.lmy.open.wanandroid.core.base.OnItemClickListener;

import java.util.List;

/**********************************************************************
 *
 *
 * @类名 CustomNavigationView
 * @包名 org.lmy.open.wanandroid.core.widget
 * @author lmy
 * @创建日期 2018/3/12
 ***********************************************************************/
public class CustomNavigationView extends LinearLayout {
    /**
     * 根布局
     */
    private View mContentView;
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
    /**
     * 监听器
     */
    private OnNavigationListener mOnNavigationListener;

    public CustomNavigationView(Context context) {
        super(context);
        init(context);
    }

    public CustomNavigationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomNavigationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化
     *
     * @param context 上下文
     */
    private void init(Context context) {
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
        mContentView = View.inflate(getContext(), R.layout.layout_navigation_end_header, this);
        this.setVisibility(GONE);
        mClassAView = mContentView.findViewById(R.id.rcv_classA);
        mClassBView = mContentView.findViewById(R.id.rcv_classB);
        mClassAView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mClassAView.setAdapter(mClassAAdapter);
        mClassBView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mClassBView.setAdapter(mClassBAdapter);
    }

    public void onShow() {
        if (this.getVisibility() == VISIBLE) {
            return;
        }
        this.setVisibility(VISIBLE);
        final Rect target = new Rect();
        Rect viewRect = new Rect();
        mContentView.getGlobalVisibleRect(viewRect);
        AnimationHelper.getInstance().doTranslateAnimation(mContentView, viewRect, target, new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (mOnNavigationListener != null) {
                    mOnNavigationListener.onShow();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void onHide() {
        if (this.getVisibility() == GONE || this.getVisibility() == INVISIBLE) {
            return;
        }
        this.setVisibility(GONE);
        if (mOnNavigationListener != null) {
            mOnNavigationListener.onHide();
        }
        final Rect target = new Rect();
        Rect viewRect = new Rect();
        mContentView.getGlobalVisibleRect(viewRect);
        AnimationHelper.getInstance().doTranslateAnimation(mContentView, viewRect, target, new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mContentView.setVisibility(GONE);
                if (mOnNavigationListener != null) {
                    mOnNavigationListener.onHide();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void setData(List<BeanRespClassify> classifies) {
        mClassAAdapter.clear();
        mClassAAdapter.addFooterItem(classifies);
        mClassBAdapter.clear();
        mClassBAdapter.addFooterItem(classifies.get(0).getChildren());
    }

    public void setOnNavigationListener(OnNavigationListener onNavigationListener) {
        mOnNavigationListener = onNavigationListener;
    }

    public interface OnNavigationListener {
        void onShow();

        void onHide();

        void onSelect(BeanRespClassifyChildren children);
    }

}
