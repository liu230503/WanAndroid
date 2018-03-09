package org.lmy.open.wanandroid.business.main.contract;

import org.lmy.open.database.option.DtoOption;
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
         * 跳转到课程列表
         */
        void jump2CourseFragment();
    }

    /**
     * 主页面业务层接口
     */
    public interface IHierarchyPresenter {
        /**
         * 加载选项列表数据
         */
        void onLoadOptionData();
    }
}
