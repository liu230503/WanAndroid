package org.lmy.open.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import org.lmy.open.database.search.DtoSearchRecord;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**********************************************************************
 *
 *
 * @类名 DataBaseHelper
 * @包名 org.lmy.open.database
 * @author lmy
 * @创建日期 2018/3/2
 ***********************************************************************/
public final class DataBaseHelper extends OrmLiteSqliteOpenHelper {
    /**
     * 单例对象
     */
    private static DataBaseHelper sDataBaseHelper = null;

    /**
     * 存放Dao的集合
     */
    private Map<String, Dao> mDaoMap = new HashMap<>();

    private DataBaseHelper(Context context) {
        super(context, DataBaseConstant.DB_NAME, null, DataBaseConstant.DB_VERSION);
    }

    /**
     * 单例方法
     *
     * @param context 上下文
     * @return DataBaseHelper
     */
    public static DataBaseHelper getInstance(Context context) {
        if (sDataBaseHelper == null) {
            synchronized (DataBaseHelper.class) {
                if (sDataBaseHelper == null) {
                    sDataBaseHelper = new DataBaseHelper(context);
                }
            }
        }
        return sDataBaseHelper;
    }

    /**
     * 获得数据库的访问对象
     *
     * @param cls 类对象
     * @return 对应的Dao
     * @throws SQLException
     */
    @Override
    public synchronized Dao getDao(Class cls) throws SQLException {
        Dao dao;
        String clsName = cls.getSimpleName();
        if (mDaoMap.containsKey(clsName)) {
            dao = mDaoMap.get(clsName);
        } else {
            dao = super.getDao(cls);
            mDaoMap.put(clsName, dao);
        }
        return dao;
    }

    /**
     * 这是在首次创建数据库时调用的。通常你应该叫createtableifnotexists语句来创建表来存储你的数据。
     *
     * @param sqLiteDatabase
     * @param connectionSource
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, DtoSearchRecord.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource arg1, int oldVersion, int newVersion) {
    }

    @Override
    public void close() {
        super.close();
        for (String key : mDaoMap.keySet()) {
            Dao dao = mDaoMap.get(key);
            dao = null;
        }
    }


}
