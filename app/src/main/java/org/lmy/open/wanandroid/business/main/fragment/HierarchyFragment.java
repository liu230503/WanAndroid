package org.lmy.open.wanandroid.business.main.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.lmy.open.database.option.DtoOption;
import org.lmy.open.wanandroid.R;
import org.lmy.open.wanandroid.business.main.adapter.OptionAdapter;
import org.lmy.open.wanandroid.business.main.bean.BeanRespArticleList;
import org.lmy.open.wanandroid.business.main.bean.BeanRespClassify;
import org.lmy.open.wanandroid.business.main.bean.BeanRespClassifyChildren;
import org.lmy.open.wanandroid.business.main.contract.HierarchyConeract;
import org.lmy.open.wanandroid.business.main.presenter.HierarchyPresenter;
import org.lmy.open.wanandroid.core.base.BaseMvpFragment;
import org.lmy.open.wanandroid.core.base.OnItemClickListener;
import org.lmy.open.wanandroid.core.comment.CreatePresenter;
import org.lmy.open.wanandroid.core.widget.ArticleList;
import org.lmy.open.wanandroid.core.widget.CustomClassDialog;
import org.lmy.open.wanandroid.core.widget.LoadingDialog;

import java.util.ArrayList;
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
        , ArticleList.OnRecyclerViewListener, MainFragment.PageSelectedListener, CustomClassDialog.OnNavigationListener, MainFragment.ToolListener {
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
     * 右侧侧滑菜单
     */
    private CustomClassDialog mRightMenuLayout;
    /**
     * loading 动画
     */
    private LoadingDialog mDialog;

    /**
     * 创建自身实例
     *
     * @param bundle 参数列表
     * @return HierarchyFragment
     */
    public static HierarchyFragment newInstance(Bundle bundle) {
        HierarchyFragment fragment = new HierarchyFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hierarchy;
    }

    @Override
    protected void initData() {
        MainFragment.setPageSelectedListener(this);
        MainFragment.setToolListener(this);
        mDialog = LoadingDialog.createDialog(mContext);
        mRightMenuLayout = new CustomClassDialog(mContext);
        mRightMenuLayout.dismiss();
        mOptionAdapter = new OptionAdapter(mContext, new OnItemClickListener() {
            @Override
            public void onItemClick(View view) {
                TextView nameView = view.findViewById(R.id.tv_name);
                int position = (Integer) nameView.getTag();
                mOptionAdapter.setSelectedItem(position);
                DtoOption dtoOption = mOptionAdapter.getItem(position);
                getPresenter().onLoadClassArticle((int) dtoOption.getChilderId(), 0);
            }

            @Override
            public void onItemLongClick(View view) {
                TextView nameView = view.findViewById(R.id.tv_name);
                int position = (Integer) nameView.getTag();
                DtoOption dtoOption = mOptionAdapter.getItem(position);
                showDeleteDialog(dtoOption);
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
        getPresenter().onLoadOptionData();
    }

    @Override
    protected void setListeners() {
        setClick(mAddButton);
        mArticleRv.registerListener(this);
        mRightMenuLayout.setOnNavigationListener(this);
    }

    @Override
    protected void processClick(View v) {
        switch (v.getId()) {
            case R.id.ib_add:
                mRightMenuLayout.show();
                break;
            default:
                break;
        }
    }

    @Override
    public void initOptionList(final List<DtoOption> options) {
        mOptionAdapter.setData(options);
        if (options.size() <= mOptionAdapter.getSelectedItem()) {
            mOptionAdapter.setSelectedItem(options.size() - 1);
        }
        DtoOption dtoOption = mOptionAdapter.getItem(mOptionAdapter.getSelectedItem());
        if (dtoOption != null) {
            getPresenter().onLoadClassArticle((int) dtoOption.getChilderId(), 0);
        }
    }

    @Override
    public void initClassTree(List<BeanRespClassify> classifies) {
        if (mOptionAdapter.getItemCount() <= 0 && classifies != null && classifies.size() > 0) {
            getPresenter().onSaveOption(classifies.get(0).getChildren().get(0));
            getPresenter().onLoadOptionData();
        }
        mRightMenuLayout.setData(classifies);
    }

    @Override
    public void initClassArticle(BeanRespArticleList articleList) {
        if (articleList.getCurPage() != 1) {
            mArticleRv.addItem(articleList, true);
        } else {
            mArticleRv.addItem(articleList, true, true);
        }
    }

    @Override
    public void openDrawer() {
        getPresenter().onLoadClass();
    }

    @Override
    public void onPrompt(String message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        Snackbar.make(mOptionRv, message, Snackbar.LENGTH_LONG).show();
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
    public void loadMore(int page) {
        DtoOption dtoOption = mOptionAdapter.getItem(mOptionAdapter.getSelectedItem());
        if (dtoOption == null) {
            return;
        }
        getPresenter().onLoadClassArticle((int) dtoOption.getChilderId(), page);
    }

    @Override
    public void onShowToolButton() {
        if (isVisible()) {
            MainFragment.onShowToolButton();
        }
    }

    @Override
    public void onHideToolButton() {
        if (isVisible()) {
            MainFragment.onHideToolButton();
        }
    }

    @Override
    public void onLike(int chapterId) {
        getPresenter().onLike(chapterId);
    }

    @Override
    public void onUnLike(int chapterId) {
        
    }

    @Override
    public void onPageSelected(int position) {
        if (position == 1) {
            getPresenter().onLoadOptionData();
        }
    }

    @Override
    public void onShow() {
        getPresenter().onLoadClass();
    }

    @Override
    public void onHide() {
        getPresenter().onLoadOptionData();
    }

    @Override
    public void onSelect(BeanRespClassifyChildren children) {
        if (children != null) {
            getPresenter().onSaveOption(children);
            mOptionAdapter.setSelectedItem(mOptionAdapter.getItemCount());
        }
        mRightMenuLayout.dismiss();
    }

    @Override
    public void onScrollTop() {
        if (isVisible()) {
            mArticleRv.onScrollTop();
        }
    }

    /**
     * @param option 需要删除的分类
     */
    private void showDeleteDialog(final DtoOption option) {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(mContext);
        normalDialog.setIcon(R.mipmap.logo);
        normalDialog.setTitle("删除课程分类");
        normalDialog.setMessage("你确定要删除" + option.getName() + "课程分类么？");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getPresenter().onDeleteOption(option);
                        if (mOptionAdapter.getItemCount() == 1) {
                            mRightMenuLayout.show();
                        } else {
                            getPresenter().onLoadOptionData();
                        }
                    }
                });
        normalDialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        normalDialog.create().dismiss();
                    }
                });
        // 显示
        normalDialog.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mArticleRv.unRegisterListener();
    }
}
