package org.lmy.open.wanandroid.business.main.contract;

import org.lmy.open.database.option.DtoOption;
import org.lmy.open.wanandroid.business.main.bean.BeanRespArticleList;
import org.lmy.open.wanandroid.business.main.bean.BeanRespClassify;
import org.lmy.open.wanandroid.business.main.bean.BeanRespClassifyChildren;
import org.lmy.open.wanandroid.core.base.BaseView;

import java.util.List;

/**********************************************************************
 *
 *
 * @类名 HierarchyConeract
 * @包名 org.lmy.open.wanandroid.business.main.contract
 * @author lmy
 * @创建日期 2018/3/7
 ***********************************************************************/
public class HierarchyConeract {

    /**
     * 主页面视图层接口
     */
    public interface IHierarchyView extends BaseView {
        /**
         * 加载选项列表数据
         *
         * @param options 数据源
         */
        void initOptionList(List<DtoOption> options);

        /**
         * 加载课程树
         *
         * @param classifies 数据
         */
        void initClassTree(List<BeanRespClassify> classifies);

        /**
         * 加载文章列表
         *
         * @param articleList
         */
        void initClassArticle(BeanRespArticleList articleList);

        /**
         * 弹出一个提示
         *
         * @param message 提示内容
         */
        void onPrompt(String message);

        /**
         * 显示加载动画
         */
        void showLoadAnim();

        /**
         * 结束加载动画
         */
        void closeLoadAnim();

        /**
         * 打开右侧菜单
         */
        void openDrawer();
    }

    /**
     * 主页面业务层接口
     */
    public interface IHierarchyPresenter {
        /**
         * 加载选项列表数据
         */
        void onLoadOptionData();

        /**
         * 加载课程列表
         */
        void onLoadClass();

        /**
         * 保存选项
         *
         * @param bean 数据
         */
        void onSaveOption(BeanRespClassifyChildren bean);

        /**
         * 加载课程文章
         *
         * @param cid  子id
         * @param page 页码
         */
        void onLoadClassArticle(int cid, int page);

        /**
         * 删除一个分类选项
         *
         * @param option 需要删除的
         */
        void onDeleteOption(DtoOption option);

        /**
         * 收藏点赞
         *
         * @param chapterId 文章id
         */
        void onLike(int chapterId);

        /**
         * 取消收藏点赞
         *
         * @param chapterId 文章id
         */
        void onUnLike(int chapterId);
    }
}
