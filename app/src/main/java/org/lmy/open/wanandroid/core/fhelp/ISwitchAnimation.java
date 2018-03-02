package org.lmy.open.wanandroid.core.fhelp;

import android.support.annotation.AnimRes;

/**********************************************************************
 *
 *
 * @类名 ISwitchAnimation
 * @包名 org.lmy.open.wanandroid.core.fragmentHelp
 * @author lmy
 * @创建日期 2018/3/2
 ***********************************************************************/
public interface ISwitchAnimation {
    /**
     * 进入动画
     *
     * @return 动画
     */
    @AnimRes
    int getEnterAnim();

    /**
     * 退出动画
     *
     * @return 动画
     */
    @AnimRes
    int getExitAnim();

    /**
     * 弹出页面的进入动画
     *
     * @return 动画
     */
    @AnimRes
    int getPopEnterAnim();

    /**
     * 弹出页面的退出动画
     *
     * @return 动画
     */
    @AnimRes
    int getPopExitAnim();
}
