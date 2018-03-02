package org.lmy.open.wanandroid.core.enums;

/**********************************************************************
 *
 *
 * @类名 EnumFragmentIndex
 * @包名 org.lmy.open.wanandroid.core.enums
 * @author lmy
 * @创建日期 2018/3/2
 ***********************************************************************/
public enum EnumFragmentIndex {
    SPLASH(1, "启动画面"),
    MAIN(2, "主页面");

    /**
     * 索引
     */
    private int mIndex;
    /**
     * 描述
     */
    private String mDes;

    EnumFragmentIndex(int index, String des) {
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
