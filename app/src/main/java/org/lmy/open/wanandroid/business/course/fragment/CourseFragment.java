package org.lmy.open.wanandroid.business.course.fragment;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;

import org.lmy.open.wanandroid.R;
import org.lmy.open.wanandroid.business.course.bean.BeanRespClassify;
import org.lmy.open.wanandroid.business.course.contract.CourseContract;
import org.lmy.open.wanandroid.business.course.presenter.CoursePresenter;
import org.lmy.open.wanandroid.business.main.adapter.ClassAAdapter;
import org.lmy.open.wanandroid.core.base.BaseMvpFragment;
import org.lmy.open.wanandroid.core.base.OnItemClickListener;
import org.lmy.open.wanandroid.core.comment.CreatePresenter;
import org.lmy.open.wanandroid.core.fhelp.AnimationsFactory;
import org.lmy.open.wanandroid.core.fhelp.FragmentPageManager;
import org.lmy.open.wanandroid.core.widget.LoadingDialog;

import java.util.List;

import static org.lmy.open.wanandroid.business.main.fragment.MainFragment.KEY_BUNDLE_PAGE_NUM;

/**********************************************************************
 *
 *
 * @类名 CourseFragment
 * @包名 org.lmy.open.wanandroid.business.course
 * @author lmy
 * @创建日期 2018/3/9
 ***********************************************************************/
@CreatePresenter(CoursePresenter.class)
public class CourseFragment extends BaseMvpFragment<CourseFragment, CoursePresenter> implements CourseContract.ICourseView, OnItemClickListener {
    /**
     * 返回按钮
     */
    private ImageButton mBackButton;
    /**
     * 一级列表
     */
    private RecyclerView mClassAView;
    /**
     * 一级列表适配器
     */
    private ClassAAdapter mClassAdapter;

    private LoadingDialog mDialog;

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    /**
     * 创建自身实例
     *
     * @param bundle 参数列表
     * @return CourseFragment
     */
    public static CourseFragment newInstance(Bundle bundle) {
        CourseFragment fragment = new CourseFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_course;
    }

    @Override
    protected void initData() {
        mClassAdapter = new ClassAAdapter(mContext, this);
        mDialog = LoadingDialog.createDialog(mContext);
    }

    @Override
    protected void getViews() {
        mBackButton = findView(R.id.ib_back);
        mClassAView = findView(R.id.rcv_classA);
        mDrawerLayout = findView(R.id.dl_course_root);
        mNavigationView = findView(R.id.nv_navigation);
        mNavigationView.getHeaderView(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.closeDrawer(GravityCompat.END);
            }
        });
    }

    @Override
    protected void setViewsValue() {
        mClassAView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mClassAView.setAdapter(mClassAdapter);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        mNavigationView.setEnabled(false);
        getPresenter().onLoadClass();
    }

    @Override
    protected void setListeners() {
        setClick(mBackButton);
    }

    @Override
    protected void processClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back:
                Bundle bundle = getArguments();
                bundle.putInt(KEY_BUNDLE_PAGE_NUM, 1);
                FragmentPageManager.getInstance().onStartMainFragment(bundle, AnimationsFactory.getInstance().getBackMainAnim());
                break;
            default:
                break;
        }
    }

    @Override
    public void initClassTree(List<BeanRespClassify> classify) {
        mClassAdapter.addFooterItem(classify);
        mDrawerLayout.openDrawer(GravityCompat.END);
    }

    @Override
    public void onPrompt(String message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        Snackbar.make(mClassAView, message, Snackbar.LENGTH_LONG).setAction("点我重试", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().onLoadClass();
            }
        }).setActionTextColor(mContext.getResources().getColor(R.color.theme_color)).show();
    }

    @Override
    public void showLoadAnim() {
        mDialog.show();
    }

    @Override
    public void closeLoadAnim() {
        mDialog.dismiss();
    }

    @Override
    public void onItemClick(View view) {

    }

    @Override
    public void onItemLongClick(View view) {

    }
}
