package org.lmy.open.wanandroid.business.login.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.lmy.open.utillibrary.PreferenceUtil;
import org.lmy.open.utillibrary.ToastUtil;
import org.lmy.open.wanandroid.R;
import org.lmy.open.wanandroid.business.login.contract.LoginContract;
import org.lmy.open.wanandroid.business.login.presenter.LoginPresenter;
import org.lmy.open.wanandroid.core.base.BaseMvpFragment;
import org.lmy.open.wanandroid.core.comment.CreatePresenter;
import org.lmy.open.wanandroid.core.fhelp.FragmentPageManager;
import org.lmy.open.wanandroid.core.widget.LoadingDialog;

/**********************************************************************
 *
 *
 * @类名 LoginFragment
 * @包名 org.lmy.open.wanandroid.business.login.fragment
 * @author lmy
 * @创建日期 2018/3/13
 ***********************************************************************/
@CreatePresenter(LoginPresenter.class)
public class LoginFragment extends BaseMvpFragment<LoginFragment, LoginPresenter> implements LoginContract.ILoginView {
    /**
     * 用户名
     */
    public static final String KEY_SPF_USER_NAME = "userName";
    /**
     * 密码
     */
    public static final String KEY_SPF_PASSWORD = "password";
    /**
     * 电子邮箱
     */
    public static final String KEY_SPF_EMAIL = "email";
    /**
     * 用户id
     */
    public static final String KEY_SPF_USER_ID = "userId";
    /**
     * 头像
     */
    public static final String KEY_SPF_ICON = "icon";
    /**
     * 类型
     */
    public static final String KEY_SPF_TYPE = "type";
    /**
     * 是否已登陆
     */
    public static final String KEY_SPF_IS_LOGIN = "isLogin";
    /**
     * 用户名
     */
    private EditText mUserNameView;
    /**
     * 密码
     */
    private EditText mPasswordView;
    /**
     * 登陆
     */
    private Button mLoginBtn;
    /**
     * 返回
     */
    private ImageButton mNextbtn;
    /**
     * 注册按钮
     */
    private TextView mRegisterBtn;
    /**
     * 加载动画
     */
    private LoadingDialog mLoadingDialog;


    public static LoginFragment newInstance(Bundle bundle) {
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initData() {
        mLoadingDialog = LoadingDialog.createDialog(mContext);
    }

    @Override
    protected void getViews() {
        mUserNameView = findView(R.id.et_userName);
        mPasswordView = findView(R.id.et_password);
        mLoginBtn = findView(R.id.btn_login);
        mNextbtn = findView(R.id.ib_back);
        mRegisterBtn = findView(R.id.tv_register);
    }

    @Override
    protected void setViewsValue() {
        mUserNameView.setText(mSpfUtil.getString(KEY_SPF_USER_NAME));
        mPasswordView.setText(mSpfUtil.getString(KEY_SPF_PASSWORD));
    }

    @Override
    protected void setListeners() {
        setClick(mNextbtn);
        setClick(mLoginBtn);
        setClick(mRegisterBtn);
    }

    @Override
    protected void processClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back:
                FragmentPageManager.getInstance().onBack(getArguments(), null);
                break;
            case R.id.btn_login:
                getPresenter().onLogin(mUserNameView.getText().toString(), mPasswordView.getText().toString());
                break;
            case R.id.tv_register:
                break;
            default:
                break;
        }
    }


    @Override
    public void showPrompt(String message) {
        ToastUtil.showToastLong(mContext, message);
    }

    @Override
    public void onLoginSuccess() {
        FragmentPageManager.getInstance().onBack(getArguments(), null);
    }

    @Override
    public void showLoadAnim() {
        mLoadingDialog.show();
    }

    @Override
    public void closeLoadAnim() {
        mLoadingDialog.dismiss();
    }

    public PreferenceUtil getSpfUtil() {
        return mSpfUtil;
    }
}
