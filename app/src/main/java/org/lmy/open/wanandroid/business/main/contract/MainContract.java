package org.lmy.open.wanandroid.business.main.contract;

import android.support.constraint.ConstraintLayout;
import android.widget.LinearLayout;

import org.lmy.open.wanandroid.core.base.BaseView;
import org.lmy.open.wanandroid.core.widget.SplashLogView;
import org.lmy.open.widgetlibrary.banner.BeanBanner;

import java.util.List;

/**********************************************************************
 *
 *
 * @类名 MainContract
 * @包名 org.lmy.open.wanandroid.business.main.contract
 * @author lmy
 * @创建日期 2018/3/5
 ***********************************************************************/
public final class MainContract {
    /**
     * 主页面视图层接口
     */
    public interface MainIView extends BaseView {
        /**
         * 展示主要布局
         */
        void onShowMainLayout();

        /**
         * 获取SplashLogView
         *
         * @return SplashLogView
         */
        SplashLogView getSplashLogView();

        /**
         * 获取根布局
         *
         * @return ConstraintLayout
         */
        ConstraintLayout getRootLayout();

        /**
         * 获取主布局
         *
         * @return LinearLayout
         */
        LinearLayout getMainLayout();

        /**
         * 加载轮播数据
         *
         * @param beanBanners 数据
         */
        void initBanner(List<BeanBanner> beanBanners);

    }

    /**
     * 主页面业务层接口
     */
    public interface MainIPresenter {
        /**
         * 展示logo动画
         */
        void onShowLogoAnim();

        /**
         * 加载banner数据
         */
        void loadBanner();
    }
}
