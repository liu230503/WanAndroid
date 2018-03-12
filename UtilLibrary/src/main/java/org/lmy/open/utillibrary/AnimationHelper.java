package org.lmy.open.utillibrary;

import android.graphics.Rect;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/**********************************************************************
 *
 *
 * @类名 org.lmy.open.utillibrary.AnimationHelper
 * @包名 PACKAGE_NAME
 * @author lmy
 * @创建日期 2018/3/12
 ***********************************************************************/
public class AnimationHelper {
    /**
     * 默认动画时长
     */
    public static final int DEFAULT_DURATION = 350;

    /**
     * 单例
     */
    private static AnimationHelper sAnimationHelper;

    /**
     * 获取单例
     *
     * @return 单例
     */
    public static synchronized AnimationHelper getInstance() {
        if (sAnimationHelper == null) {
            sAnimationHelper = new AnimationHelper();
        }
        return sAnimationHelper;
    }

    /**
     * 私有构造方法
     */
    private AnimationHelper() {
    }

    /**
     * 位移动画
     *
     * @param view     做动画的view
     * @param viewRect 做动画的view的位置
     * @param target   目标位置
     * @param listener 动画监听
     */
    public void doTranslateAnimation(View view, Rect viewRect, Rect target, final Animation.AnimationListener listener) {
        doTranslateAnimation(view, viewRect, null, target, listener);
    }

    /**
     * 位移动画
     *
     * @param view     做动画的view
     * @param viewRect 做动画的view的位置
     * @param source   开始位置
     * @param target   目标位置
     * @param listener 动画监听
     */
    public void doTranslateAnimation(View view, Rect viewRect, Rect source, Rect target, final Animation.AnimationListener listener) {
        if (view == null) {
            return;
        }

        if (source == null) {
            source = viewRect;
        }

        //TranslateAnimation translateAnimation = new TranslateAnimation(target.left - rect.left, 0, target.top - rect.top, 0);
        TranslateAnimation translateAnimation = new TranslateAnimation(source.left - viewRect.left, target.left - viewRect.left
                , source.top - viewRect.top, target.top - viewRect.top);
        translateAnimation.setDuration(DEFAULT_DURATION);
        translateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        if (listener != null) {
            translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    listener.onAnimationStart(animation);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    listener.onAnimationEnd(animation);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    listener.onAnimationRepeat(animation);
                }
            });
        }

        translateAnimation.setFillAfter(true);
        view.startAnimation(translateAnimation);
    }
}
