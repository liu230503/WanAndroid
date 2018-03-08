package org.lmy.open.wanandroid.core.widget;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import org.lmy.open.utillibrary.WaitForCalm;
import org.lmy.open.wanandroid.R;

/**********************************************************************
 *
 *
 * @类名 ToolButtonDialog
 * @包名 org.lmy.open.wanandroid.core.widget
 * @author lmy
 * @创建日期 2018/3/8
 ***********************************************************************/
public class ToolButtonDialog extends Dialog {
    /**
     * 自动消失时间
     */
    private static final long DISAPPEARANCE_TIME = 10 * 1000;
    /**
     * Dialog 根视图
     */
    private Window mWindow;
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 等时任务
     */
    private WaitForCalm mWaitForCalm;
    /**
     * 置顶布局
     */
    private LinearLayout mTopLayout;
    /**
     * 搜索布局
     */
    private LinearLayout mSearchLayout;

    private AnimatorSet mTopLayoutAnim;
    private AnimatorSet mSearchLayoutAnim;

    /**
     * 构造函数
     *
     * @param context 上下文
     */
    public ToolButtonDialog(@NonNull Context context) {
        this(context, R.style.user_cumstem_dialog);
    }

    /**
     * 构造器
     *
     * @param context 上下文
     * @param theme   风格样式
     */
    @SuppressLint("ResourceType")
    private ToolButtonDialog(Context context, int theme) {
        super(context, theme);
        mContext = context;
        mWaitForCalm = new WaitForCalm(DISAPPEARANCE_TIME);
        initDialog();
        mWaitForCalm.activity();
        mWaitForCalm.setOnJobListener(new WaitForCalm.OnJobListener() {
            @Override
            public void onJob() {
                dismiss();
            }
        });
        mTopLayoutAnim = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.anim.add_bill_anim);
        mSearchLayoutAnim = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.anim.add_bill_anim);
    }

    /**
     * 构造 Dialog
     */
    private void initDialog() {
        setCancelable(false);
        show();
        mWindow = getWindow();
        WindowManager.LayoutParams params = mWindow.getAttributes();
        mWindow.setGravity(Gravity.TOP);
        params.flags = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        params.x = 0;
        params.width = mWindow.getWindowManager().getDefaultDisplay().getWidth();
        params.height = mWindow.getWindowManager().getDefaultDisplay().getHeight();
        mWindow.setAttributes(params);
        mWindow.setContentView(R.layout.layout_toolbutton_list);
        initView();
    }

    /**
     * 初始化view
     */
    private void initView() {
        mTopLayout = mWindow.findViewById(R.id.lly_top);
        mSearchLayout = mWindow.findViewById(R.id.lly_search);
        mSearchLayoutAnim.setTarget(mSearchLayout);
        mSearchLayoutAnim.start();
        mTopLayoutAnim.setTarget(mTopLayout);
        mTopLayoutAnim.setStartDelay(150);
    }

    @Override
    public void dismiss() {
        mWaitForCalm.destory();
        mWaitForCalm.setOnJobListener(null);
    }

    @Override
    public void show() {
        super.show();
    }

}
