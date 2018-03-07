package org.lmy.open.utillibrary.imageload;

/**********************************************************************
 *
 *
 * @类名 EnumImage
 * @包名 org.lmy.open.utillibrary.ImageLoad
 * @author lmy
 * @创建日期 2018/3/5
 ***********************************************************************/
public enum EnumImage {
    DEFAULT(0, "默认"),
    BANNER(1, "轮播图片"),
    ARTICLE_HEADER(2, "文章头像");


    /**
     * 索引
     */
    private int mIndex;
    /**
     * 描述
     */
    private String mDes;

    EnumImage(int index, String des) {
        mIndex = index;
        mDes = des;
    }

    private int getIndex() {
        return mIndex;
    }

    public String getDes() {
        return mDes;
    }
}
