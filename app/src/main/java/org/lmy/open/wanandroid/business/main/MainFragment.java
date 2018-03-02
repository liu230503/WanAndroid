package org.lmy.open.wanandroid.business.main;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import org.lmy.open.wanandroid.R;
import org.lmy.open.wanandroid.core.base.BaseFragment;
import org.lmy.open.wanandroid.core.widget.SplashLogView;

/**********************************************************************
 *
 *
 * @类名 MainFragment
 * @包名 org.lmy.open.wanandroid.business.main
 * @author lmy
 * @创建日期 2018/2/27
 ***********************************************************************/
public class MainFragment extends BaseFragment implements Handler.Callback {
    /**
     * 启动fragment消息
     */
    private static final int HANDLER_MESSAGE_START_FRAGMENT = 10001;
    /**
     * 延迟时间
     */
    private static final long DELAY_TIME = 300;
    /**
     * SplashLogView
     */
    private SplashLogView mSplashLogView;
    /**
     * 根布局
     */
    private ConstraintLayout mRootView;
    /**
     * 主画面
     */
    private LinearLayout mMainLayout;

    /**
     * Handler
     */
    private Handler mHandler;

    /**
     * 创建自身实例
     *
     * @param bundle 参数列表
     * @return MainFragment
     */
    public static MainFragment newInstance(Bundle bundle) {
        MainFragment fragment = new MainFragment();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        mHandler = new Handler(this);
    }

    @Override
    protected void getViews() {
        mSplashLogView = findView(R.id.splash);
        mRootView = findView(R.id.view_root);
        mMainLayout = findView(R.id.lly_main);
    }

    @Override
    protected void setViewsValue() {
        mHandler.sendEmptyMessageDelayed(HANDLER_MESSAGE_START_FRAGMENT, DELAY_TIME);
        mSplashLogView.startAnim();
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void processClick(View v) {

    }

    @Override
    public boolean handleMessage(Message message) {
        switch (message.what) {
            case HANDLER_MESSAGE_START_FRAGMENT:
                mRootView.removeView(mSplashLogView);
                Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.narrow);
                mMainLayout.startAnimation(animation);
                break;
            default:
                break;
        }
        return false;
    }
}
