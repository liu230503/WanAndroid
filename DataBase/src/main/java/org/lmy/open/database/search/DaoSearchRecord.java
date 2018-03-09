package org.lmy.open.database.search;

import android.text.TextUtils;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import org.lmy.open.database.DataBaseApplication;
import org.lmy.open.database.DataBaseConstant;
import org.lmy.open.database.DataBaseHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**********************************************************************
 *
 *
 * @类名 DaoSearchRecord
 * @包名 org.lmy.open.database.search
 * @author lmy
 * @创建日期 2018/3/2
 ***********************************************************************/
public final class DaoSearchRecord {
    /**
     * tag
     */
    private static final String TAG = DaoSearchRecord.class.getSimpleName();

    /**
     * 数据
     */
    private Dao<DtoSearchRecord, Integer> mSearchRecordDao = null;
    /**
     * 单例对象
     */
    private static DaoSearchRecord sDaoSearchRecord = null;

    private DaoSearchRecord() {
        try {
            mSearchRecordDao = DataBaseHelper.getInstance(DataBaseApplication.getInstance().getBaseContext()).getDao(DtoSearchRecord.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 单例方法
     *
     * @return DaoSearchRecord
     */
    public static DaoSearchRecord getInstance() {
        if (sDaoSearchRecord == null) {
            synchronized (DaoSearchRecord.class) {
                if (sDaoSearchRecord == null) {
                    sDaoSearchRecord = new DaoSearchRecord();
                }
            }
        }
        return sDaoSearchRecord;
    }

    /**
     * 添加或修改记录
     *
     * @param keyword 关键字
     */
    public void addOrUpdateRecord(String keyword) {
        if (TextUtils.isEmpty(keyword)) {
            return;
        }
        DtoSearchRecord searchRecord = new DtoSearchRecord();
        searchRecord.setKeyword(keyword);
        searchRecord.setLastTime(System.currentTimeMillis());
        if (isisExists(keyword)) {
            List<DtoSearchRecord> searchRecords = getDataFromKeyword(keyword);
            DtoSearchRecord record = searchRecords.get(searchRecords.size() - 1);
            if (record == null) {
                return;
            }
            searchRecord.setSearchNumber(record.getSearchNumber() + 1);
            updateRecord(searchRecord);
        } else {
            searchRecord.setSearchNumber(1);
            addRecord(searchRecord);
        }
    }

    /**
     * 模糊查询
     *
     * @param str 模糊源
     * @return 结果集
     */
    public List<String> getKeywordLikeStr(String str) {
        List<String> keywords = new ArrayList<>();
        if (TextUtils.isEmpty(str)) {
            return keywords;
        }
        QueryBuilder<DtoSearchRecord, Integer> query = mSearchRecordDao
                .queryBuilder();
        try {
            query.where().like(DataBaseConstant.SearchRecordTable.COLUMN_KEYWORD,
                    str);
            query.distinct();
            query.orderBy(DataBaseConstant.SearchRecordTable.COLUMN_SEARCHNUMBER, false);
            List<DtoSearchRecord> list = query.query();
            if (list == null || list.size() <= 0) {
                return keywords;
            }
            for (DtoSearchRecord record : list) {
                if (record == null) {
                    continue;
                }
                keywords.add(record.getKeyword());
            }
            return keywords;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return keywords;
    }

    /**
     * 添加一跳记录
     *
     * @param searchRecord DtoSearchRecord
     */
    private void addRecord(DtoSearchRecord searchRecord) {
        if (searchRecord == null) {
            return;
        }
        try {
            mSearchRecordDao.createIfNotExists(searchRecord);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改一条记录
     *
     * @param searchRecord DtoSearchRecord
     */
    private void updateRecord(DtoSearchRecord searchRecord) {
        if (searchRecord == null) {
            return;
        }
        try {
            mSearchRecordDao.update(searchRecord);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 通过关键字查询记录
     *
     * @param keyword 关键字
     * @return 结果集
     */
    private List<DtoSearchRecord> getDataFromKeyword(String keyword) {
        QueryBuilder<DtoSearchRecord, Integer> query = mSearchRecordDao
                .queryBuilder();
        try {
            query.where().eq(DataBaseConstant.SearchRecordTable.COLUMN_KEYWORD,
                    keyword);
            List<DtoSearchRecord> list = query.query();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断是否存在
     *
     * @param keyword 关键字
     * @return 结果
     */
    private boolean isisExists(String keyword) {
        List<DtoSearchRecord> list = getDataFromKeyword(keyword);
        if (list != null) {
            return list.size() > 0;
        }
        return false;
    }


}
