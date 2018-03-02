package org.lmy.open.wanandroid.core.fhelp;

import org.lmy.open.wanandroid.R;

/**********************************************************************
 *
 *
 * @类名 AnimationsFactory
 * @包名 org.lmy.open.wanandroid.core.fhelp
 * @author lmy
 * @创建日期 2018/3/2
 ***********************************************************************/
public final class AnimationsFactory {
    /**
     * 单例对象
     */
    private static AnimationsFactory sAnimationsFactory = null;

    private AnimationsFactory() {

    }

    /**
     * 单例方法
     *
     * @return AnimationsFactory
     */
    public static AnimationsFactory getInstance() {
        if (sAnimationsFactory == null) {
            synchronized (AnimationsFactory.class) {
                if (sAnimationsFactory == null) {
                    sAnimationsFactory = new AnimationsFactory();
                }
            }
        }
        return sAnimationsFactory;
    }

    public ISwitchAnimation getDelayStartMainAnim() {
        return new ISwitchAnimation() {
            @Override
            public int getEnterAnim() {
                return R.anim.narrow;
            }

            @Override
            public int getExitAnim() {
                return 0;
            }

            @Override
            public int getPopEnterAnim() {
                return 0;
            }

            @Override
            public int getPopExitAnim() {
                return 0;
            }
        };
    }
}
