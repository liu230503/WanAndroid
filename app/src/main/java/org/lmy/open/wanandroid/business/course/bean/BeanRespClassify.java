package org.lmy.open.wanandroid.business.course.bean;

import java.util.List;

/**********************************************************************
 *
 *
 * @类名 BeanRespClassify
 * @包名 org.lmy.open.wanandroid.business.main.bean
 * @author lmy
 * @创建日期 2018/3/9
 ***********************************************************************/
public class BeanRespClassify {
    /**
     * 课程id
     */
    private int mCourseId;
    /**
     * id
     */
    private int mId;
    /**
     * 课程名称
     */
    private String mName;
    /**
     * 序列号
     */
    private int mOrder;
    /**
     * 父章id
     */
    private int mParentChapterId;
    /**
     * 是否可见
     */
    private int mVisible;
    /**
     * 子分类
     */
    private List<BeanRespClassifyChildren> mChildren;

    public int getCourseId() {
        return mCourseId;
    }

    public void setCourseId(int courseId) {
        this.mCourseId = courseId;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public int getOrder() {
        return mOrder;
    }

    public void setOrder(int order) {
        this.mOrder = order;
    }

    public int getParentChapterId() {
        return mParentChapterId;
    }

    public void setParentChapterId(int parentChapterId) {
        this.mParentChapterId = parentChapterId;
    }

    public int getVisible() {
        return mVisible;
    }

    public void setVisible(int visible) {
        this.mVisible = visible;
    }

    public List<BeanRespClassifyChildren> getChildren() {
        return mChildren;
    }

    public void setChildren(List<BeanRespClassifyChildren> children) {
        this.mChildren = children;
    }
}
