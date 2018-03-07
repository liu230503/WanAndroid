package org.lmy.open.wanandroid.business.main.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**********************************************************************
 *
 *
 * @类名 BeanRespArticleList
 * @包名 org.lmy.open.wanandroid.business.main.bean
 * @author lmy
 * @创建日期 2018/3/6
 ***********************************************************************/
public class BeanRespArticleList {
    /**
     * 当前页码
     */
    private int mCurPage;
    /**
     * 数量
     */
    private int mOffset;
    /**
     * 结束？没有了？
     */
    private boolean mOver;
    /**
     * 页码总数
     */
    private int mPageCount;
    /**
     * 每夜大小
     */
    private int mSize;
    /**
     * 总条数
     */
    private int mTotal;
    /**
     * 文章内容
     */
    @JSONField(name = "datas")
    private List<BeanRespArticle> mDatas;

    public int getCurPage() {
        return mCurPage;
    }

    public void setCurPage(int curPage) {
        this.mCurPage = curPage;
    }

    public int getOffset() {
        return mOffset;
    }

    public void setOffset(int offset) {
        this.mOffset = offset;
    }

    public boolean isOver() {
        return mOver;
    }

    public void setOver(boolean over) {
        this.mOver = over;
    }

    public int getPageCount() {
        return mPageCount;
    }

    public void setPageCount(int pageCount) {
        this.mPageCount = pageCount;
    }

    public int getSize() {
        return mSize;
    }

    public void setSize(int size) {
        this.mSize = size;
    }

    public int getTotal() {
        return mTotal;
    }

    public void setTotal(int total) {
        this.mTotal = total;
    }

    public List<BeanRespArticle> getDatas() {
        return mDatas;
    }

    public void setDatas(List<BeanRespArticle> datas) {
        this.mDatas = datas;
    }
}
