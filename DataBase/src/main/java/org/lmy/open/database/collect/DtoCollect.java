package org.lmy.open.database.collect;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.lmy.open.database.DataBaseConstant;

/**********************************************************************
 *
 *
 * @类名 DtoCollect
 * @包名 org.lmy.open.database.collect
 * @author lmy
 * @创建日期 2018/3/28
 ***********************************************************************/
@DatabaseTable(tableName = DataBaseConstant.CollectTable.TABLE_NAME)
public class DtoCollect {
    /**
     * 表id 来自接口
     */
    @DatabaseField(columnName = DataBaseConstant.CollectTable.COLUMN_ID, id = true, canBeNull = false)
    private int mId;

    /**
     * 作者
     */
    @DatabaseField(columnName = DataBaseConstant.CollectTable.COLUMN_AUTHOR, canBeNull = false)
    private String mAuthor;


    /**
     * 用户id
     */
    @DatabaseField(columnName = DataBaseConstant.CollectTable.COLUMN_USERID, canBeNull = false)
    private int mUserId;

    /**
     * 父章id
     */
    @DatabaseField(columnName = DataBaseConstant.CollectTable.COLUMN_CHAPTERID, canBeNull = false)
    private int mChapterId;

    /**
     * 父章名
     */
    @DatabaseField(columnName = DataBaseConstant.CollectTable.COLUMN_CHAPTERNAME, canBeNull = false)
    private String mChapterName;

    /**
     * 课程id
     */
    @DatabaseField(columnName = DataBaseConstant.CollectTable.COLUMN_COURSEID, canBeNull = false)
    private int mCourseId;

    /**
     * 连接
     */
    @DatabaseField(columnName = DataBaseConstant.CollectTable.COLUMN_LINK, canBeNull = false)
    private String mLink;

    /**
     * OriginId
     */
    @DatabaseField(columnName = DataBaseConstant.CollectTable.COLUMN_ORIGINID, canBeNull = false)
    private int mOriginId;


    /**
     * 标题
     */
    @DatabaseField(columnName = DataBaseConstant.CollectTable.COLUMN_TITLE, canBeNull = false)
    private String mTitle;


    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    public int getChapterId() {
        return mChapterId;
    }

    public void setChapterId(int chapterId) {
        mChapterId = chapterId;
    }

    public String getChapterName() {
        return mChapterName;
    }

    public void setChapterName(String chapterName) {
        mChapterName = chapterName;
    }

    public int getCourseId() {
        return mCourseId;
    }

    public void setCourseId(int courseId) {
        mCourseId = courseId;
    }

    public String getLink() {
        return mLink;
    }

    public void setLink(String link) {
        mLink = link;
    }

    public int getOriginId() {
        return mOriginId;
    }

    public void setOriginId(int originId) {
        mOriginId = originId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int userId) {
        mUserId = userId;
    }

}
