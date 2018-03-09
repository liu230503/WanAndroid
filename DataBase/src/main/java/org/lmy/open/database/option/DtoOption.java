package org.lmy.open.database.option;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.lmy.open.database.DataBaseConstant;

/**********************************************************************
 *
 *
 * @类名 DtoOption
 * @包名 org.lmy.open.database.option
 * @author lmy
 * @创建日期 2018/3/9
 ***********************************************************************/
@DatabaseTable(tableName = DataBaseConstant.OptionTable.TABLE_NAME)
public class DtoOption {
    /**
     * 表id
     */
    @DatabaseField(columnName = DataBaseConstant.OptionTable.COLUMN_ID, generatedId = true, canBeNull = false)
    private int mId;
    /**
     * 课程id
     */
    @DatabaseField(columnName = DataBaseConstant.OptionTable.COLUMN_COURSEID, canBeNull = false)
    private String mCourseId;
    /**
     * 子课程id
     */
    @DatabaseField(columnName = DataBaseConstant.OptionTable.COLUMN_CHILDERID, canBeNull = false)
    private String mChilderId;

    /**
     * 课程名
     */
    @DatabaseField(columnName = DataBaseConstant.OptionTable.COLUMN_NAME, canBeNull = false)
    private String mName;

    /**
     * 父章id
     */
    @DatabaseField(columnName = DataBaseConstant.OptionTable.COLUMN_PARENTCHAPTERID, canBeNull = false)
    private String mParentChapterId;

    /**
     * 是否可见
     */
    @DatabaseField(columnName = DataBaseConstant.OptionTable.COLUMN_VISIBLE, canBeNull = false)
    private String mVisible;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getCourseId() {
        return mCourseId;
    }

    public void setCourseId(String courseId) {
        mCourseId = courseId;
    }

    public String getChilderId() {
        return mChilderId;
    }

    public void setChilderId(String childerId) {
        mChilderId = childerId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getParentChapterId() {
        return mParentChapterId;
    }

    public void setParentChapterId(String parentChapterId) {
        mParentChapterId = parentChapterId;
    }

    public String getVisible() {
        return mVisible;
    }

    public void setVisible(String visible) {
        mVisible = visible;
    }
}
