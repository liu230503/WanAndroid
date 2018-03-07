package org.lmy.open.wanandroid.business.main.presenter;

import android.os.Handler;
import android.os.Message;

import com.umeng.analytics.MobclickAgent;

import org.lmy.open.netlibrary.internet.api.ISendRequest;
import org.lmy.open.netlibrary.internet.api.JsonUtil;
import org.lmy.open.netlibrary.internet.api.RequestProxy;
import org.lmy.open.utillibrary.LogHelper;
import org.lmy.open.wanandroid.business.main.bean.BeanRespArticleList;
import org.lmy.open.wanandroid.business.main.bean.BeanRespBanner;
import org.lmy.open.wanandroid.business.main.contract.MainContract;
import org.lmy.open.wanandroid.business.main.fragment.MainFragment;
import org.lmy.open.wanandroid.core.base.BasePresenter;
import org.lmy.open.widgetlibrary.banner.BeanBanner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
    public void loadBanner() {
        RequestProxy.getInstance().getBanner(mBannerListener);
    }

    @Override
    public void loadArticle(int page) {
        RequestProxy.getInstance().getArticle(page, mArticleListener);
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

    /**
     * 获取banner数据的监听
     */
    private ISendRequest.RequestListener mBannerListener = new ISendRequest.RequestListener() {
        @Override
        public void onSuccess(String data) {
            List<BeanRespBanner> banners = JsonUtil.parseArray(data, BeanRespBanner.class);
            if (banners == null || banners.size() <= 0) {
                return;
            }
            List<BeanBanner> list = new ArrayList<>();
            // 此处排序 排列banner顺序
            Collections.sort(banners, new Comparator<BeanRespBanner>() {
                @Override
                public int compare(BeanRespBanner beanRespBanner, BeanRespBanner t1) {
                    return beanRespBanner.getOrder() - t1.getOrder();
                }
            });
            for (BeanRespBanner bean : banners) {
                list.add(new BeanBanner(bean.getTitle(), bean.getImagePath(), bean.getUrl()));
            }
            getView().initBanner(list);
        }

        @Override
        public void onCodeError(int errorCode, String errorMessage) {
            MobclickAgent.reportError(getView().getContext(), errorMessage);
        }

        @Override
        public void onFailure(Throwable e, boolean isNetWorkError) {
            MobclickAgent.reportError(getView().getContext(), e);
        }

        @Override
        public void onRequestStart() {
            getView().showLoadAnim();
        }

        @Override
        public void onRequestEnd() {
            getView().closeLoadAnim();
        }
    };
    /**
     * 获取首页文章列表的监听器
     */
    private ISendRequest.RequestListener mArticleListener = new ISendRequest.RequestListener() {
        @Override
        public void onSuccess(String data) {
            BeanRespArticleList beanRespArticleList = JsonUtil.parseObject(data, BeanRespArticleList.class);
            getView().initArticleList(beanRespArticleList);
        }

        @Override
        public void onCodeError(int errorCode, String errorMessage) {

        }

        @Override
        public void onFailure(Throwable e, boolean isNetWorkError) {

        }

        @Override
        public void onRequestStart() {
            getView().showLoadAnim();
        }

        @Override
        public void onRequestEnd() {
            getView().closeLoadAnim();
        }
    };
}
