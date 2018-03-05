package org.lmy.open.wanandroid.business.splash;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import org.lmy.open.wanandroid.R;
import org.lmy.open.wanandroid.core.base.BaseFragment;
import org.lmy.open.wanandroid.core.fhelp.FragmentPageManager;
import org.lmy.open.wanandroid.core.widget.SplashLogView;

/**********************************************************************
 *
 *
 * @类名 SplashFragment
 * @包名 org.lmy.open.wanandroid.business.main.fragment
 * @author lmy
 * @创建日期 2018/3/2
 ***********************************************************************/
public class SplashFragment extends BaseFragment implements Handler.Callback {
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
     * Handler
     */
    private Handler mHandler;

    /**
     * 创建自身实例
     *
     * @param bundle 参数列表
     * @return SplashFragment
     */
    public static SplashFragment newInstance(Bundle bundle) {
        SplashFragment fragment = new SplashFragment();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_splash;
    }

    @Override
    protected void initData() {
        mHandler = new Handler(this);
    }

    @Override
    protected void getViews() {
        mSplashLogView = findView(R.id.splash);
    }

    @Override
    protected void setViewsValue() {
        mHandler.sendEmptyMessage(HANDLER_MESSAGE_START_FRAGMENT);
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
                FragmentPageManager.getInstance().onStartMainFragment(null, null);
                break;
            default:
                break;
        }
        return false;
    }
}
