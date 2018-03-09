package org.lmy.open.wanandroid.business.course.presenter;

import com.umeng.analytics.MobclickAgent;

import org.lmy.open.netlibrary.internet.api.ISendRequest;
import org.lmy.open.netlibrary.internet.api.JsonUtil;
import org.lmy.open.netlibrary.internet.api.RequestProxy;
import org.lmy.open.wanandroid.R;
import org.lmy.open.wanandroid.business.course.bean.BeanRespClassify;
import org.lmy.open.wanandroid.business.course.contract.CourseContract;
import org.lmy.open.wanandroid.business.course.fragment.CourseFragment;
import org.lmy.open.wanandroid.core.application.WanAndroidApp;
import org.lmy.open.wanandroid.core.base.BasePresenter;

import java.util.List;

/**********************************************************************
 *
 *
 * @类名 CoursePresenter
 * @包名 org.lmy.open.wanandroid.business.course.presenter
 * @author lmy
 * @创建日期 2018/3/9
 ***********************************************************************/
public class CoursePresenter extends BasePresenter<CourseFragment> implements CourseContract.ICoursePresenter {

    @Override
    public void onLoadClass() {
        RequestProxy.getInstance().getClass(mLoadClassTreeListener);
    }

    /**
     * 加载课程列表回调
     */
    private ISendRequest.RequestListener mLoadClassTreeListener = new ISendRequest.RequestListener() {
        @Override
        public void onSuccess(String data) {
            List<BeanRespClassify> classics = JsonUtil.parseArray(data, BeanRespClassify.class);
            if (classics == null || classics.size() <= 0) {
                return;
            }
            getView().initClassTree(classics);
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
