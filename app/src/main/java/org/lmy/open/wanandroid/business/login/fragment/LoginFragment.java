package org.lmy.open.wanandroid.business.login.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dd.morphingbutton.MorphingAnimation;
import com.dd.morphingbutton.MorphingButton;

import org.lmy.open.utillibrary.DensityUtils;
import org.lmy.open.wanandroid.R;
import org.lmy.open.wanandroid.core.base.BaseFragment;
import org.lmy.open.wanandroid.core.fhelp.FragmentPageManager;

/**********************************************************************
 *
 *
 * @类名 LoginFragment
 * @包名 org.lmy.open.wanandroid.business.login.fragment
 * @author lmy
 * @创建日期 2018/3/13
 ***********************************************************************/
public class LoginFragment extends BaseFragment implements MorphingAnimation.Listener {
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
    private MorphingButton mLoginBtn;
    /**
     * 返回
     */
    private ImageButton mNextbtn;


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

    }

    @Override
    protected void getViews() {
        mUserNameView = findView(R.id.et_userName);
        mPasswordView = findView(R.id.et_password);
        mLoginBtn = findView(R.id.btn_login);
        mNextView = findView(R.id.tv_next);
    }

    @Override
    protected void setViewsValue() {
    }

    @Override
    protected void setListeners() {
        setClick(mNextView);
        setClick(mLoginBtn);
    }

    @Override
    protected void processClick(View v) {
        switch (v.getId()) {
            case R.id.tv_next:
                FragmentPageManager.getInstance().onBack(getArguments(), null);
                break;
            case R.id.btn_login:
                MorphingButton.Params circle = MorphingButton.Params.create()
                        .duration(2000)
                        .cornerRadius(DensityUtils.dip2px(mContext, 0))
                        .width(DensityUtils.dip2px(mContext, 100))
                        .height(DensityUtils.dip2px(mContext, 35))
                        .animationListener(this)
                        .color(mContext.getResources().getColor(R.color.white))
                        .colorPressed(mContext.getResources().getColor(R.color.gray333))
                        .icon(R.mipmap.add_white_36x36);
                mLoginBtn.morph(circle);
                break;
            default:
                break;
        }
    }

    @Override
    public void onAnimationEnd() {

    }
}
