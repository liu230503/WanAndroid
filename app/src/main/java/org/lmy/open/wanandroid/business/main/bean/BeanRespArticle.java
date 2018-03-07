package org.lmy.open.wanandroid.business.main.bean;

/**********************************************************************
 *
 *
 * @类名 BeanRespArticle
 * @包名 org.lmy.open.wanandroid.business.main.bean
 * @author lmy
 * @创建日期 2018/3/7
 ***********************************************************************/
public class BeanRespArticle {
    /**
     * apk文件连接
     */
    private String mApkLink;
    /**
     * 作者
     */
    private String mAuthor;
    /**
     * 文章id
     */
    private int mChapterId;
    /**
     * 文章名称
     */
    private String mChapterName;
    /**
     * 是否收藏
     */
    private boolean mCollect;
    /**
     * 课程id
     */
    private int mCourseId;
    /**
     * 描述
     */
    private String mDesc;
    /**
     * 包装？
     */
    private String mEnvelopePic;
    /**
     * mId
     */
    private int mId;
    /**
     * 连接
     */
    private String mLink;
    /**
     * 好的日期？点赞日期？
     */
    private String mNiceDate;
    /**
     * 起源
     */
    private String mOrigin;
    /**
     * 课程连接
     */
    private String mProjectLink;
    /**
     * 发表时间
     */
    private long mPublishTime;
    /**
     * 标题
     */
    private String mTitle;
    /**
     * 是否可见
     */
    private int mVisible;
    /**
     * 点赞？
     */
    private int mZan;

    public String getApkLink() {
        return mApkLink;
    }

    public void setApkLink(String apkLink) {
        this.mApkLink = apkLink;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        this.mAuthor = author;
    }

    public int getChapterId() {
        return mChapterId;
    }

    public void setChapterId(int chapterId) {
        this.mChapterId = chapterId;
    }

    public String getChapterName() {
        return mChapterName;
    }

    public void setChapterName(String chapterName) {
        this.mChapterName = chapterName;
    }

    public boolean isCollect() {
        return mCollect;
    }

    public void setCollect(boolean collect) {
        this.mCollect = collect;
    }

    public int getCourseId() {
        return mCourseId;
    }

    public void setCourseId(int courseId) {
        this.mCourseId = courseId;
    }

    public String getDesc() {
        return mDesc;
    }

    public void setDesc(String desc) {
        this.mDesc = desc;
    }

    public String getEnvelopePic() {
        return mEnvelopePic;
    }

    public void setEnvelopePic(String envelopePic) {
        this.mEnvelopePic = envelopePic;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getLink() {
        return mLink;
    }

    public void setLink(String link) {
        this.mLink = link;
    }

    public String getNiceDate() {
        return mNiceDate;
    }

    public void setNiceDate(String niceDate) {
        this.mNiceDate = niceDate;
    }

    public String getOrigin() {
        return mOrigin;
    }

    public void setOrigin(String origin) {
        this.mOrigin = origin;
    }

    public String getProjectLink() {
        return mProjectLink;
    }

    public void setProjectLink(String projectLink) {
        this.mProjectLink = projectLink;
    }

    public long getPublishTime() {
        return mPublishTime;
    }

    public void setPublishTime(long publishTime) {
        this.mPublishTime = publishTime;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public int getVisible() {
        return mVisible;
    }

    public void setVisible(int visible) {
        this.mVisible = visible;
    }

    public int getZan() {
        return mZan;
    }

    public void setZan(int zan) {
        this.mZan = zan;
    }
}
