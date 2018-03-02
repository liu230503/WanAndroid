package org.lmy.open.wanandroid.core.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import org.lmy.open.wanandroid.R;

/**********************************************************************
 *
 *
 * @类名 SplashLogView
 * @包名 org.lmy.open.wanandroid.core.widget
 * @author lmy
 * @创建日期 2018/3/2
 ***********************************************************************/
public class SplashLogView extends LinearLayout {
    /**
     * 根布局
     */
    private View mContentView;
    /**
     * 先缩小后放大动画
     */
    private Animation mEnlargeAnim;
    /**
     * 淡出动画
     */
    private Animation mFadeOutAnim;

    public SplashLogView(Context context) {
        super(context);
        init(context);
    }

    public SplashLogView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SplashLogView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContentView = View.inflate(getContext(), R.layout.layout_view_splashlog, this);
        mEnlargeAnim = AnimationUtils.loadAnimation(context, R.anim.enlarge);
        mEnlargeAnim.setFillAfter(true);
        mFadeOutAnim = AnimationUtils.loadAnimation(context, R.anim.fade_out);
        mFadeOutAnim.setFillAfter(true);
    }

    public void startAnim() {
        mContentView.findViewById(R.id.logo).startAnimation(mEnlargeAnim);
        mContentView.startAnimation(mFadeOutAnim);
    }
}
