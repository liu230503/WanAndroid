package org.lmy.open.wanandroid.business.collection.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.InputType;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.lmy.open.database.collect.DtoCollect;
import org.lmy.open.utillibrary.ToastUtil;
import org.lmy.open.wanandroid.R;
import org.lmy.open.wanandroid.business.collection.adapter.CollectAdapter;
import org.lmy.open.wanandroid.business.collection.contract.CollectContract;
import org.lmy.open.wanandroid.business.collection.presenter.CollectPresenter;
import org.lmy.open.wanandroid.core.base.BaseMvpFragment;
import org.lmy.open.wanandroid.core.base.OnItemClickListener;
import org.lmy.open.wanandroid.core.comment.CreatePresenter;
import org.lmy.open.wanandroid.core.fhelp.FragmentPageManager;

import java.util.List;

import static org.lmy.open.wanandroid.business.login.fragment.LoginFragment.KEY_SPF_USER_ID;

/**********************************************************************
 *
 *
 * @类名 CollectionFragment
 * @包名 org.lmy.open.wanandroid.business.collection.fragment
 * @author lmy
 * @创建日期 2018/3/27
 ***********************************************************************/
@CreatePresenter(CollectPresenter.class)
public class CollectionFragment extends BaseMvpFragment<CollectionFragment, CollectPresenter> implements OnItemClickListener, CollectContract.ICollectView, CollectAdapter.OnSideslipButtonListener {
    /**
     * 返回按钮
     */
    private ImageButton mBackBtn;
    /**
     * 搜索布局
     */
    private SearchView mSearchLayout;
    /**
     * 收藏列表
     */
    private RecyclerView mCollectListView;

    private CollectAdapter mCollectAdapter;

    public static CollectionFragment newInstance(Bundle bundle) {
        CollectionFragment fragment = new CollectionFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collect;
    }

    @Override
    protected void initData() {
        mCollectAdapter = new CollectAdapter(mContext, this);
    }

    @Override
    protected void getViews() {
        mBackBtn = findView(R.id.ib_back);
        mSearchLayout = findView(R.id.sv_search);
        mCollectListView = findView(R.id.rv_collect);
    }

    @Override
    protected void setViewsValue() {
        mSearchLayout.setSubmitButtonEnabled(true);
        mSearchLayout.setIconifiedByDefault(false);
        mSearchLayout.setInputType(InputType.TYPE_CLASS_TEXT);
        SearchView.SearchAutoComplete textView = mSearchLayout.findViewById(R.id.search_src_text);
        textView.setTextColor(mContext.getResources().getColor(R.color.theme_color));
        textView.setHintTextColor(mContext.getResources().getColor(R.color.darker_gray));
        mCollectListView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mCollectListView.setAdapter(mCollectAdapter);
        getPresenter().loadCollectList(mSpfUtil.getInt(KEY_SPF_USER_ID), 0);
    }

    @Override
    protected void setListeners() {
        setClick(mBackBtn);
        mCollectAdapter.setSideslipButtonListener(this);
        mSearchLayout.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                getPresenter().onSearch(newText);
                return false;
            }
        });
    }

    @Override
    protected void processClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back:
                FragmentPageManager.getInstance().onBack(getArguments(), null);
                break;
            case R.id.rll_search:
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(View view) {

    }

    @Override
    public void onItemLongClick(View view) {

    }

    @Override
    public void initCollectList(final List<DtoCollect> collects) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mCollectAdapter.clear();
                mCollectAdapter.addFooterItem(collects);
            }
        });
    }

    @Override
    public void showPrompt(String message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        ToastUtil.showToastLong(mContext, message);
    }

    @Override
    public void onLoyaltyClick(int position) {
        ToastUtil.showToastShort(mContext, "该功能暂未开放");
    }

    @Override
    public void onDeleteClick(int position) {
        getPresenter().onDeleteCollect(mCollectAdapter.getItem(position));
    }
}
