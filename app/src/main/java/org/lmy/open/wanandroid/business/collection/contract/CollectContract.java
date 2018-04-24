package org.lmy.open.wanandroid.business.collection.contract;

import org.lmy.open.database.collect.DtoCollect;
import org.lmy.open.wanandroid.core.base.BaseView;

import java.util.List;

/**********************************************************************
 *
 *
 * @类名 CollectContract
 * @包名 org.lmy.open.wanandroid.business.collection.contract
 * @author lmy
 * @创建日期 2018/3/28
 ***********************************************************************/
public class CollectContract {
    /**
     * 收藏页面视图层接口
     */
    public interface ICollectView extends BaseView {
        /**
         * 将收藏数据加载到列表中
         *
         * @param collects 数据源
         */
        void initCollectList(List<DtoCollect> collects);

        /**
         * 弹出提示
         *
         * @param message 内容
         */
        void showPrompt(String message);
    }

    /**
     * 收藏页面业务层接口
     */
    public interface ICollectPresenter {
        /**
         * 加载用户收藏列表
         *
         * @param userId 用户id
         * @param page   页码
         */
        void loadCollectList(int userId, int page);

        /**
         * 搜索
         *
         * @param key 关键字
         */
        void onSearch(String key);
    }
}
