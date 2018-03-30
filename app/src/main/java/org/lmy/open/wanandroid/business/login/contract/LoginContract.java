package org.lmy.open.wanandroid.business.login.contract;

import org.lmy.open.wanandroid.core.base.BaseView;

/**********************************************************************
 *
 *
 * @类名 LoginContract
 * @包名 org.lmy.open.wanandroid.business.login.contract
 * @author lmy
 * @创建日期 2018/3/27
 ***********************************************************************/
public class LoginContract {
    /**
     * 登陆页面视图层接口
     */
    public interface ILoginView extends BaseView {
        /**
         * 弹出提示
         *
         * @param message 内容
         */
        void showPrompt(String message);

        /**
         * 登陆成功
         */
        void onLoginSuccess();

        /**
         * 显示加载动画
         */
        void showLoadAnim();

        /**
         * 结束加载动画
         */
        void closeLoadAnim();
    }

    /**
     * 登陆页面业务层接口
     */
    public interface ILoginPresenter {
        /**
         * 登陆
         *
         * @param userName 用户名
         * @param password 密码
         */
        void onLogin(String userName, String password);
    }
}
