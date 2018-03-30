package org.lmy.open.database.collect;

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
 * @类名 DaoCollect
 * @包名 org.lmy.open.database.collect
 * @author lmy
 * @创建日期 2018/3/28
 ***********************************************************************/
public final class DaoCollect {
    /**
     * tag
     */
    private static final String TAG = DaoCollect.class.getSimpleName();

    /**
     * 数据
     */
    private Dao<DtoCollect, Integer> mCollectDao = null;
    /**
     * 单例对象
     */
    private static DaoCollect sDaoCollect = null;

    private DaoCollect() {
        try {
            mCollectDao = DataBaseHelper.getInstance(DataBaseApplication.getInstance().getBaseContext()).getDao(DtoCollect.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 单例方法
     *
     * @return DaoOption
     */
    public static DaoCollect getInstance() {
        if (sDaoCollect == null) {
            synchronized (DaoCollect.class) {
                if (sDaoCollect == null) {
                    sDaoCollect = new DaoCollect();
                }
            }
        }
        return sDaoCollect;
    }

    /**
     * 添加一条
     *
     * @param dto 数据
     */
    public void addCollect(DtoCollect dto) {
        try {
            mCollectDao.createOrUpdate(dto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加多条
     *
     * @param dtos 数据
     */
    public void addCollect(List<DtoCollect> dtos) {
        if (dtos == null) {
            return;
        }
        for (DtoCollect dto : dtos) {
            addCollect(dto);
        }
    }

    /**
     * 查询一个用户的所有收藏
     *
     * @param userId 用户id
     * @return 结果
     */
    public List<DtoCollect> getAllCollect(int userId) {
        List<DtoCollect> dtoCollects = new ArrayList<>();
        QueryBuilder<DtoCollect, Integer> query = mCollectDao
                .queryBuilder();
        try {
            query.where().eq(DataBaseConstant.CollectTable.COLUMN_USERID, userId);
            dtoCollects = query.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dtoCollects;
    }

    /**
     * 通过关键字模糊搜索用户收藏列表
     *
     * @param userId  用户id
     * @param keyword 关键字
     * @return 结果
     */
    public List<DtoCollect> getCollectLikeKeyword(int userId, String keyword) {
        List<DtoCollect> dtoCollects = new ArrayList<>();
        if (TextUtils.isEmpty(keyword)) {
            return dtoCollects;
        }
        QueryBuilder<DtoCollect, Integer> query = mCollectDao
                .queryBuilder();
        try {
            query.where().eq(DataBaseConstant.CollectTable.COLUMN_USERID, userId)
                    .and()
                    .like(DataBaseConstant.CollectTable.COLUMN_CHAPTERNAME, keyword)
                    .or()
                    .like(DataBaseConstant.CollectTable.COLUMN_TITLE, keyword);
            query.distinct();
            dtoCollects = query.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dtoCollects;
    }

    public void delete(DtoCollect dtoCollect) {
        try {
            mCollectDao.delete(dtoCollect);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(List<DtoCollect> dtoCollects) {
        if (dtoCollects == null || dtoCollects.size() <= 0) {
            return;
        }
        for (DtoCollect dtoCollect : dtoCollects) {
            delete(dtoCollect);
        }
    }


}
