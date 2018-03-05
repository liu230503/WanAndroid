package org.lmy.open.wanandroid.business.main.presenter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import org.lmy.open.wanandroid.R;
import org.lmy.open.wanandroid.business.main.contract.MainContract;
import org.lmy.open.wanandroid.business.main.fragment.MainFragment;
import org.lmy.open.wanandroid.core.application.WanAndroidApp;
import org.lmy.open.wanandroid.core.base.BasePresenter;

/**********************************************************************
 *
 *
 * @类名 MainPresenter
 * @包名 org.lmy.open.wanandroid.business.main.presenter
 * @author lmy
 * @创建日期 2018/3/5
 ***********************************************************************/
public class MainPresenter extends BasePresenter<MainFragment> implements MainContract.MainIPresenter, Handler.Callback {
    /**
     * 切换布局消息
     */
    private static final int HANDLER_MESSAGE_SWITCH_LAYOUT = 10001;
    /**
     * 延迟时间
     */
    private static final long DELAY_TIME = 300;

    /**
     * Handler
     */
    private Handler mHandler;

    public MainPresenter() {
        mHandler = new Handler(this);
    }

    @Override
    public void onShowLogoAnim() {
        mHandler.sendEmptyMessageDelayed(HANDLER_MESSAGE_SWITCH_LAYOUT, DELAY_TIME);
        getView().getSplashLogView().startAnim();
    }

    @Override
    public boolean handleMessage(Message message) {
        switch (message.what) {
            case HANDLER_MESSAGE_SWITCH_LAYOUT:
                getView().onShowMainLayout();
                break;
            default:
                break;
        }
        return false;
    }
}
