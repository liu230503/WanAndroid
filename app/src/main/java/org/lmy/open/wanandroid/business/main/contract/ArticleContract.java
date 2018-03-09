package org.lmy.open.wanandroid.business.main.contract;

import org.lmy.open.wanandroid.business.main.bean.BeanRespArticleList;
import org.lmy.open.wanandroid.core.base.BaseView;
import org.lmy.open.widgetlibrary.banner.BeanBanner;

import java.util.List;

/**********************************************************************
 *
 *
 * @类名 ArticleContract
 * @包名 org.lmy.open.wanandroid.business.main.contract
 * @author lmy
 * @创建日期 2018/3/5
 ***********************************************************************/
public final class ArticleContract {
    /**
     * 主页面视图层接口
     */
    public interface IArticleView extends BaseView {

        /**
         * 加载轮播数据
         *
         * @param beanBanners 数据
         */
        void initBanner(List<BeanBanner> beanBanners);

        /**
         * 显示加载动画
         */
        void showLoadAnim();

        /**
         * 结束加载动画
         */
        void closeLoadAnim();

        /**
         * 加载文章列表
         *
         * @param beanRespArticleList 数据
         */
        void initArticleList(BeanRespArticleList beanRespArticleList);

        /**
         * 弹出一个提示
         *
         * @param message 提示内容
         */
        void onPrompt(String message);

    }

    /**
     * 主页面业务层接口
     */
    public interface IArticlePresenter {
        /**
         * 加载banner数据
         */
        void loadBanner();

        /**
         * 加载文章
         *
         * @param page 页码
         */
        void loadArticle(int page);
    }
}
