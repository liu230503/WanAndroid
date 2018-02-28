package org.lmy.open.wanandroid.core.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import org.lmy.open.utillibrary.PreferenceUtil;

/**********************************************************************
 *
 *
 * @类名 BaseActivity
 * @包名 org.lmy.open.wanandroid.core.base
 * @author lmy
 * @创建日期 2018/2/27
 ***********************************************************************/
public abstract class BaseActivity extends Activity {
    /**
     * 标志
     */
    protected static final String TAG = BaseActivity.class.getName();
    /**
     * 数据存储器
     */
    protected PreferenceUtil mSpfUtil = null;
    /**
     * 上下文
     */
    protected Context mContext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mSpfUtil = PreferenceUtil.getInstance(BaseActivity.this);
        mContext = BaseActivity.this;
        setContentView();
        initData();
        getViews();
        setViewsValue();
        setListeners();
    }

    /**
     * 设置布局文件
     */
    protected abstract void setContentView();

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
}