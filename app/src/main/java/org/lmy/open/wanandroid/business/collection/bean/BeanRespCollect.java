package org.lmy.open.wanandroid.business.collection.bean;

import java.util.List;

/**********************************************************************
 *
 *
 * @类名 BeanRespCollect
 * @包名 org.lmy.open.wanandroid.business.collection.bean
 * @author lmy
 * @创建日期 2018/3/28
 ***********************************************************************/
public class BeanRespCollect {
    /**
     * 当前页码
     */
    private int mCurPage;
    /**
     * 偏移量？
     */
    private int mOffset;
    /**
     * 结束？
     */
    private boolean mOver;
    /**
     * 总页数
     */
    private int mPageCount;
    /**
     * 每页数量
     */
    private int mSize;
    /**
     * 总数
     */
    private int mTotal;
    /**
     * 收藏文章
     */
    private List<BeanRespCollectData> mDatas;

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

    public List<BeanRespCollectData> getDatas() {
        return mDatas;
    }

    public void setDatas(List<BeanRespCollectData> datas) {
        this.mDatas = datas;
    }
}
