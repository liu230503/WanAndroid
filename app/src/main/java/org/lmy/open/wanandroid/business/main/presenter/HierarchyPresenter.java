package org.lmy.open.wanandroid.business.main.presenter;

import com.nostra13.universalimageloader.utils.L;
import com.umeng.analytics.MobclickAgent;

import org.lmy.open.database.option.DaoOption;
import org.lmy.open.database.option.DtoOption;
import org.lmy.open.netlibrary.internet.api.ISendRequest;
import org.lmy.open.netlibrary.internet.api.JsonUtil;
import org.lmy.open.netlibrary.internet.api.RequestProxy;
import org.lmy.open.utillibrary.LogHelper;
import org.lmy.open.wanandroid.R;
import org.lmy.open.wanandroid.business.main.bean.BeanRespArticle;
import org.lmy.open.wanandroid.business.main.bean.BeanRespArticleList;
import org.lmy.open.wanandroid.business.main.bean.BeanRespClassify;
import org.lmy.open.wanandroid.business.main.bean.BeanRespClassifyChildren;
import org.lmy.open.wanandroid.business.main.contract.HierarchyConeract;
import org.lmy.open.wanandroid.business.main.fragment.HierarchyFragment;
import org.lmy.open.wanandroid.core.application.WanAndroidApp;
import org.lmy.open.wanandroid.core.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**********************************************************************
 *
 *
 * @类名 HierarchyPresenter
 * @包名 org.lmy.open.wanandroid.business.main.presenter
 * @author lmy
 * @创建日期 2018/3/9
 ***********************************************************************/
public class HierarchyPresenter extends BasePresenter<HierarchyFragment> implements HierarchyConeract.IHierarchyPresenter {
    /**
     * 缓存课程数据
     */
    private List<BeanRespClassify> mClassifies = null;

    @Override
    public void onLoadOptionData() {
        List<DtoOption> options = DaoOption.getInstance().selectOption();
        if (options == null || options.size() <= 0) {
            getView().openDrawer();
        } else {
            getView().initOptionList(options);
        }
    }

    @Override
    public void onLoadClass() {
        if (mClassifies == null || mClassifies.size() <= 0) {
            RequestProxy.getInstance().getClass(mLoadClassTreeListener);
        } else {
            getView().initClassTree(mClassifies);
        }
    }

    @Override
    public void onSaveOption(BeanRespClassifyChildren bean) {
        DtoOption dtoOption = new DtoOption();
        dtoOption.setName(bean.getName());
        dtoOption.setChilderId(bean.getId());
        dtoOption.setCourseId(bean.getCourseId());
        dtoOption.setParentChapterId(bean.getParentChapterId());
        dtoOption.setVisible(bean.getVisible());
        DaoOption.getInstance().addOption(dtoOption);
    }

    @Override
    public void onLoadClassArticle(int cid, int page) {
        RequestProxy.getInstance().getClassArticle(cid, page, mLoadClassArticleListener);
    }

    @Override
    public void onDeleteOption(DtoOption option) {
        DaoOption.getInstance().deleteOption(option);
    }

    /**
     * 加载课程列表回调
     */
    private ISendRequest.RequestListener mLoadClassTreeListener = new ISendRequest.RequestListener() {
        @Override
        public void onSuccess(String data) {
            mClassifies = JsonUtil.parseArray(data, BeanRespClassify.class);
            if (mClassifies == null || mClassifies.size() <= 0) {
                return;
            }
            getView().initClassTree(mClassifies);
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

    private ISendRequest.RequestListener mLoadClassArticleListener = new ISendRequest.RequestListener() {
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
            getView().initClassArticle(beanRespArticleList);
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
}
