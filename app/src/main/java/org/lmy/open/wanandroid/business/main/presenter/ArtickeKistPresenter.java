package org.lmy.open.wanandroid.business.main.presenter;

import android.util.Log;

import com.umeng.analytics.MobclickAgent;

import org.lmy.open.database.collect.DaoCollect;
import org.lmy.open.netlibrary.internet.api.ISendRequest;
import org.lmy.open.netlibrary.internet.api.JsonUtil;
import org.lmy.open.netlibrary.internet.api.RequestProxy;
import org.lmy.open.utillibrary.LogHelper;
import org.lmy.open.wanandroid.R;
import org.lmy.open.wanandroid.business.main.bean.BeanRespArticle;
import org.lmy.open.wanandroid.business.main.bean.BeanRespArticleList;
import org.lmy.open.wanandroid.business.main.bean.BeanRespBanner;
import org.lmy.open.wanandroid.business.main.contract.ArticleContract;
import org.lmy.open.wanandroid.business.main.fragment.ArticleListFragment;
import org.lmy.open.wanandroid.core.application.WanAndroidApp;
import org.lmy.open.wanandroid.core.base.BasePresenter;
import org.lmy.open.widgetlibrary.banner.BeanBanner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**********************************************************************
 *
 *
 * @类名 ArtickeKistPresenter
 * @包名 org.lmy.open.wanandroid.business.main.presenter
 * @author lmy
 * @创建日期 2018/3/5
 ***********************************************************************/
public class ArtickeKistPresenter extends BasePresenter<ArticleListFragment> implements ArticleContract.IArticlePresenter {


    @Override
    public void loadBanner() {
        RequestProxy.getInstance().getBanner(mBannerListener);
    }

    @Override
    public void loadArticle(int page) {
        RequestProxy.getInstance().getArticle(page, mArticleListener);
    }

    @Override
    public void onLike(int chapterId) {
        RequestProxy.getInstance().onLike(chapterId, mLikeListener);
    }

    @Override
    public void onUnLike(int chapterId) {
        RequestProxy.getInstance().onUnLike(chapterId, mUnLikeListener);
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
                if (bean == null || bean.getIsVisible() != 1) {
                    continue;
                }
                list.add(new BeanBanner(bean.getTitle(), bean.getImagePath(), bean.getUrl()));
            }
            getView().initBanner(list);
        }

        @Override
        public void onCodeError(int errorCode, String errorMessage) {
            MobclickAgent.reportError(getView().getContext(), errorMessage);
            getView().onPrompt(errorMessage);
        }

        @Override
        public void onFailure(Throwable e, boolean isNetWorkError) {
            MobclickAgent.reportError(getView().getContext(), e);
            String message;
            if (isNetWorkError) {
                message = WanAndroidApp.getInstance().getContext().getResources().getString(R.string.networ_error);
            } else {
                message = WanAndroidApp.getInstance().getContext().getResources().getString(R.string.local_error);
            }
            getView().onPrompt(message);
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
            List<BeanRespArticle> invalidArticles = new ArrayList<>();
            for (BeanRespArticle article : beanRespArticleList.getDatas()) {
                if (article == null || article.getVisible() != 1) {
                    invalidArticles.add(article);
                }
            }
            beanRespArticleList.getDatas().removeAll(invalidArticles);
            getView().initArticleList(beanRespArticleList);
        }

        @Override
        public void onCodeError(int errorCode, String errorMessage) {
            MobclickAgent.reportError(getView().getContext(), errorMessage);
            getView().onPrompt(errorMessage);
        }

        @Override
        public void onFailure(Throwable e, boolean isNetWorkError) {
            MobclickAgent.reportError(getView().getContext(), e);
            String message;
            if (isNetWorkError) {
                message = WanAndroidApp.getInstance().getContext().getResources().getString(R.string.networ_error);
            } else {
                message = WanAndroidApp.getInstance().getContext().getResources().getString(R.string.local_error);
            }
            getView().onPrompt(message);
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
     * 点赞收藏的监听器
     */
    private ISendRequest.RequestListener mLikeListener = new ISendRequest.RequestListener() {
        @Override
        public void onSuccess(String data) {

        }

        @Override
        public void onCodeError(int errorCode, String errorMessage) {

        }

        @Override
        public void onFailure(Throwable e, boolean isNetWorkError) {

        }

        @Override
        public void onRequestStart() {

        }

        @Override
        public void onRequestEnd() {

        }
    };


    /**
     * 取消点赞收藏的监听器
     */
    private ISendRequest.RequestListener mUnLikeListener = new ISendRequest.RequestListener() {
        @Override
        public void onSuccess(String data) {
            LogHelper.d("liumy=== data:" + data);
        }

        @Override
        public void onCodeError(int errorCode, String errorMessage) {

        }

        @Override
        public void onFailure(Throwable e, boolean isNetWorkError) {

        }

        @Override
        public void onRequestStart() {

        }

        @Override
        public void onRequestEnd() {

        }
    };
}
