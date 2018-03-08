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
    SPLASH(1, "启动页面"),
    MAIN(2, "主页面"),
    ARTICLE_LIST(3, "文章列表页面"),
    LOGIN(4, "登陆页面"),
    PERSONAL(5,"个人中心页面"),
    COLLECTION(6,"我的收藏页面"),
    LETTER(7,"私信页面"),
    COMMON(8,"常用网站页面"),
    SETTING(9,"设置页面"),
    ABOUT(10,"关于页面");

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
