package org.lmy.open.wanandroid.business.login.presenter;

import android.text.TextUtils;

import org.lmy.open.netlibrary.internet.api.ISendRequest;
import org.lmy.open.netlibrary.internet.api.JsonUtil;
import org.lmy.open.netlibrary.internet.api.RequestProxy;
import org.lmy.open.wanandroid.R;
import org.lmy.open.wanandroid.business.login.bean.BeanRespLogin;
import org.lmy.open.wanandroid.business.login.contract.LoginContract;
import org.lmy.open.wanandroid.business.login.fragment.LoginFragment;
import org.lmy.open.wanandroid.core.base.BasePresenter;

import static org.lmy.open.wanandroid.business.login.fragment.LoginFragment.KEY_SPF_EMAIL;
import static org.lmy.open.wanandroid.business.login.fragment.LoginFragment.KEY_SPF_ICON;
import static org.lmy.open.wanandroid.business.login.fragment.LoginFragment.KEY_SPF_IS_LOGIN;
import static org.lmy.open.wanandroid.business.login.fragment.LoginFragment.KEY_SPF_PASSWORD;
import static org.lmy.open.wanandroid.business.login.fragment.LoginFragment.KEY_SPF_TYPE;
import static org.lmy.open.wanandroid.business.login.fragment.LoginFragment.KEY_SPF_USER_ID;
import static org.lmy.open.wanandroid.business.login.fragment.LoginFragment.KEY_SPF_USER_NAME;

/**********************************************************************
 *
 *
 * @类名 LoginPresenter
 * @包名 org.lmy.open.wanandroid.business.login.presenter
 * @author lmy
 * @创建日期 2018/3/27
 ***********************************************************************/
public class LoginPresenter extends BasePresenter<LoginFragment> implements LoginContract.ILoginPresenter {
    @Override
    public void onLogin(String userName, String password) {
        if (!onCheckoutContent(userName, password)) {
            return;
        }
        RequestProxy.getInstance().onLogin(userName, password, mLoginListener);
    }

    /**
     * 登陆监听
     */
    private ISendRequest.RequestListener mLoginListener = new ISendRequest.RequestListener() {
        @Override
        public void onSuccess(String data) {
            BeanRespLogin login = JsonUtil.parseObject(data, BeanRespLogin.class);
            if (login == null) {
                getView().showPrompt(getString(R.string.networ_error));
            }
            getView().getSpfUtil().putString(KEY_SPF_USER_NAME, login.getUsername());
            getView().getSpfUtil().putString(KEY_SPF_PASSWORD, login.getPassword());
            getView().getSpfUtil().putString(KEY_SPF_EMAIL, login.getEmail());
            getView().getSpfUtil().putInt(KEY_SPF_USER_ID, login.getId());
            getView().getSpfUtil().putString(KEY_SPF_ICON, login.getIcon());
            getView().getSpfUtil().putInt(KEY_SPF_TYPE, login.getType());
            getView().getSpfUtil().putBoolean(KEY_SPF_IS_LOGIN, true);
            getView().onLoginSuccess();
        }

        @Override
        public void onCodeError(int errorCode, String errorMessage) {
            getView().showPrompt(errorMessage);
        }

        @Override
        public void onFailure(Throwable e, boolean isNetWorkError) {
            getView().showPrompt(getString(R.string.networ_error));
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
     * 校验内容
     *
     * @param userName 用户名
     * @param password 密码
     */
    private boolean onCheckoutContent(String userName, String password) {
        if (TextUtils.isEmpty(userName)) {
            getView().showPrompt(getString(R.string.user_name_error));
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            getView().showPrompt(getString(R.string.password_error));
            return false;
        }
        return true;
    }
}
