package org.lmy.open.wanandroid.business.course.bean;

/**********************************************************************
 *
 *
 * @类名 BeanRespClassifyChildren
 * @包名 org.lmy.open.wanandroid.business.main.bean
 * @author lmy
 * @创建日期 2018/3/9
 ***********************************************************************/
public class BeanRespClassifyChildren {
    /**
     * 课程id
     */
    private int mCourseId;
    /**
     * mId
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
    private String mChildren;

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

    public String getChildren() {
        return mChildren;
    }

    public void setChildren(String children) {
        this.mChildren = children;
    }
}
