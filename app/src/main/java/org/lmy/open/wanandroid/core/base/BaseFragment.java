package org.lmy.open.wanandroid.core.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.lmy.open.utillibrary.LogHelper;
import org.lmy.open.utillibrary.PreferenceUtil;

/**********************************************************************
 *
 *
 * @类名 BaseFragment
 * @包名 org.lmy.open.wanandroid.core.base
 * @author lmy
 * @创建日期 2018/2/27
 ***********************************************************************/
public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    /**
     * Tag
     */
    protected static  String TAG ;
    /**
     * 根布局
     */
    protected View mView = null;
    /**
     * 所有注册的view
     */
    private SparseArray<View> mViews;
    /**
     * Context 对象
     */
    protected Context mContext = null;

    /**
     * 数据存储器
     */
    protected PreferenceUtil mSpfUtil = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getName();
        LogHelper.d(TAG+" onCreate ");
        mViews = new SparseArray<>();
        mSpfUtil = PreferenceUtil.getInstance(mContext);
        initData();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogHelper.d(TAG+" onCreateView ");
        mView = inflater.inflate(getLayoutId(), container, false);
        getViews();
        setListeners();
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        LogHelper.d(TAG+" onResume ");
        setViewsValue();
    }

    @Override
    public void onAttach(Context context) {
        mContext = context;
        super.onAttach(context);
        LogHelper.d(TAG+" onAttach ");
    }


    @Override
    public void onClick(View view) {
        processClick(view);
    }

    /**
     * 设置布局文件
     *
     * @return 布局资源id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 关联控件
     */
    protected abstract void getViews();

    /**
     * 关联控件值
     */
    protected abstract void setViewsValue();

    /**
     * 关联控件点击事件
     */
    protected abstract void setListeners();

    /**
     * 事件回调 点击事件回调
     *
     * @param v view
     */
    protected abstract void processClick(View v);

    /**
     * 按下返回键
     */
    public void onBackPressed() {
        getActivity().finish();
    }

    /**
     * 根据id查找绑定view
     *
     * @param viewId 控件id
     * @param <E>    类型
     * @return view
     */
    public <E extends View> E findView(int viewId) {
        if (mView != null) {
            E view = (E) mViews.get(viewId);
            if (view == null) {
                view = mView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return view;
        }
        return null;
    }

    /**
     * 绑定事件
     *
     * @param view view
     * @param <E>  类型
     */
    public <E extends View> void setClick(E view) {
        view.setOnClickListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        LogHelper.d(TAG+" onStop ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogHelper.d(TAG+" onDestroy ");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogHelper.d(TAG+" onPause ");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        LogHelper.d(TAG+" onLowMemory ");
    }
}