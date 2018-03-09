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

    public ISwitchAnimation getDefaultStartMainAnim() {
        return new ISwitchAnimation() {
            @Override
            public int getEnterAnim() {
                return android.R.anim.fade_in;
            }

            @Override
            public int getExitAnim() {
                return android.R.anim.fade_out;
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

    public ISwitchAnimation getBackMainAnim() {
        return new ISwitchAnimation() {
            @Override
            public int getEnterAnim() {
                return R.anim.reg_fade_in;
            }

            @Override
            public int getExitAnim() {
                return R.anim.reg_fade_out;
            }

            @Override
            public int getPopEnterAnim() {
                return R.anim.push_right_in;
            }

            @Override
            public int getPopExitAnim() {
                return R.anim.push_right_out;
            }
        };
    }

    public ISwitchAnimation getMain2CourseAnim() {
        return new ISwitchAnimation() {
            @Override
            public int getEnterAnim() {
                return R.anim.push_left_in;
            }

            @Override
            public int getExitAnim() {
                return R.anim.push_left_out;
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
