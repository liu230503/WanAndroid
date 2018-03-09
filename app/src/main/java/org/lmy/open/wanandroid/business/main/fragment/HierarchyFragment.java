package org.lmy.open.wanandroid.business.main.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import org.lmy.open.database.option.DtoOption;
import org.lmy.open.utillibrary.LogHelper;
import org.lmy.open.wanandroid.R;
import org.lmy.open.wanandroid.business.main.adapter.OptionAdapter;
import org.lmy.open.wanandroid.business.main.contract.HierarchyConeract;
import org.lmy.open.wanandroid.business.main.presenter.HierarchyPresenter;
import org.lmy.open.wanandroid.core.base.BaseMvpFragment;
import org.lmy.open.wanandroid.core.base.OnItemClickListener;
import org.lmy.open.wanandroid.core.comment.CreatePresenter;
import org.lmy.open.wanandroid.core.fhelp.AnimationsFactory;
import org.lmy.open.wanandroid.core.fhelp.FragmentPageManager;
import org.lmy.open.wanandroid.core.widget.ArticleList;

import java.util.List;

/**********************************************************************
 *
 *
 * @类名 HierarchyFragment
 * @包名 org.lmy.open.wanandroid.business.main.fragment
 * @author lmy
 * @创建日期 2018/3/9
 ***********************************************************************/
@CreatePresenter(HierarchyPresenter.class)
public final class HierarchyFragment extends BaseMvpFragment<HierarchyFragment, HierarchyPresenter> implements HierarchyConeract.IHierarchyView
        , ArticleList.OnRecyclerViewListener, MainFragment.PageSelectedListener {
    /**
     * 选项列表
     */
    private RecyclerView mOptionRv;
    /**
     * 文章列表
     */
    private ArticleList mArticleRv;
    /**
     * 添加按钮
     */
    private ImageButton mAddButton;
    /**
     * 选项适配器
     */
    private OptionAdapter mOptionAdapter;

    /**
     * 创建自身实例
     *
     * @param bundle 参数列表
     * @return HierarchyFragment
     */
    public static HierarchyFragment newInstance(Bundle bundle) {
        Bundle args = new Bundle();
        HierarchyFragment fragment = new HierarchyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hierarchy;
    }

    @Override
    protected void initData() {
        MainFragment.setPageSelectedListener(this);
        mOptionAdapter = new OptionAdapter(mContext, new OnItemClickListener() {
            @Override
            public void onItemClick(View view) {

            }

            @Override
            public void onItemLongClick(View view) {

            }
        });
    }

    @Override
    protected void getViews() {
        mOptionRv = findView(R.id.rcv_option);
        mArticleRv = findView(R.id.arl_article);
        mAddButton = findView(R.id.ib_add);
    }

    @Override
    protected void setViewsValue() {
        mOptionRv.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        mOptionRv.setAdapter(mOptionAdapter);
    }

    @Override
    protected void setListeners() {
        mArticleRv.registerListener(this);
    }

    @Override
    protected void processClick(View v) {

    }

    @Override
    public void initOptionList(List<DtoOption> options) {

    }

    @Override
    public void jump2CourseFragment() {
        FragmentPageManager.getInstance().onStartCourseFragment(getArguments(), AnimationsFactory.getInstance().getMain2CourseAnim());
    }

    @Override
    public void onPrompt(String message) {

    }

    @Override
    public void loadMore(int page) {

    }

    @Override
    public void onShowToolButton() {

    }

    @Override
    public void onHideToolButton() {

    }

    @Override
    public void PageSelected(int position) {
        if (position == 1) {
            getPresenter().onLoadOptionData();
        }
    }
}
