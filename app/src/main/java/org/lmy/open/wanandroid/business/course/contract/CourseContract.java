package org.lmy.open.wanandroid.business.course.contract;

import org.lmy.open.wanandroid.business.course.bean.BeanRespClassify;
import org.lmy.open.wanandroid.core.base.BaseView;

import java.util.List;

/**********************************************************************
 *
 *
 * @类名 CourseContract
 * @包名 org.lmy.open.wanandroid.business.course.contract
 * @author lmy
 * @创建日期 2018/3/9
 ***********************************************************************/
public class CourseContract {

    public interface ICourseView extends BaseView {
        /**
         * 加载课程树
         *
         * @param classifys 数据
         */
        void initClassTree(List<BeanRespClassify> classifys);

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
    }

    public interface ICoursePresenter {
        /**
         * 加载课程列表
         */
        void onLoadClass();
    }
}
