package org.lmy.open.wanandroid.business.main.bean;

/**********************************************************************
 *
 *
 * @类名 BeanRespBanner
 * @包名 org.lmy.open.wanandroid.business.main.bean
 * @author lmy
 * @创建日期 2018/3/6
 ***********************************************************************/
public class BeanRespBanner {
    /**
     * 描述
     */
    private String mDesc;
    /**
     * mId
     */
    private int mId;
    /**
     * 图片地址
     */
    private String mImagePath;
    /**
     * 是否可见
     */
    private int mIsVisible;
    /**
     * 序列号
     */
    private int mOrder;
    /**
     * mTitle
     */
    private String mTitle;
    /**
     * 类型
     */
    private int mType;
    /**
     * 详情链接
     */
    private String mUrl;

    public String getDesc() {
        return mDesc;
    }

    public void setDesc(String desc) {
        this.mDesc = desc;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getImagePath() {
        return mImagePath;
    }

    public void setImagePath(String imagePath) {
        this.mImagePath = imagePath;
    }

    public int getIsVisible() {
        return mIsVisible;
    }

    public void setIsVisible(int isVisible) {
        this.mIsVisible = isVisible;
    }

    public int getOrder() {
        return mOrder;
    }

    public void setOrder(int order) {
        this.mOrder = order;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        this.mType = type;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

}
