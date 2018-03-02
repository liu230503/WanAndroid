package org.lmy.open.database.search;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.lmy.open.database.DataBaseConstant;

/**********************************************************************
 *
 *
 * @类名 DtoSearchRecord
 * @包名 org.lmy.open.database.search
 * @author lmy
 * @创建日期 2018/3/2
 ***********************************************************************/
@DatabaseTable(tableName = DataBaseConstant.SearchRecordTable.TABLE_NAME)
public final class DtoSearchRecord {
    /**
     * 表id
     */
    @DatabaseField(columnName = DataBaseConstant.SearchRecordTable.COLUMN_ID, generatedId = true, canBeNull = false)
    private int mId;
    /**
     * 搜索关键字
     */
    @DatabaseField(columnName = DataBaseConstant.SearchRecordTable.COLUMN_KEYWORD, canBeNull = false)
    private String mKeyword;

    /**
     * 搜索次数
     */
    @DatabaseField(columnName = DataBaseConstant.SearchRecordTable.COLUMN_SEARCHNUMBER, canBeNull = false)
    private long mSearchNumber;

    /**
     * 最后一次搜索时间
     */
    @DatabaseField(columnName = DataBaseConstant.SearchRecordTable.COLUMN_LASTTIME, canBeNull = false)
    private long mLastTime;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getKeyword() {
        return mKeyword;
    }

    public void setKeyword(String keyword) {
        mKeyword = keyword;
    }

    public long getSearchNumber() {
        return mSearchNumber;
    }

    public void setSearchNumber(long searchNumber) {
        mSearchNumber = searchNumber;
    }

    public long getLastTime() {
        return mLastTime;
    }

    public void setLastTime(long lastTime) {
        mLastTime = lastTime;
    }
}
