package org.lmy.open.database.option;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import org.lmy.open.database.DataBaseApplication;
import org.lmy.open.database.DataBaseConstant;
import org.lmy.open.database.DataBaseHelper;
import org.lmy.open.database.search.DtoSearchRecord;

import java.sql.SQLException;
import java.util.List;

/**********************************************************************
 *
 *
 * @类名 DaoOption
 * @包名 org.lmy.open.database.option
 * @author lmy
 * @创建日期 2018/3/9
 ***********************************************************************/
public final class DaoOption {
    /**
     * tag
     */
    private static final String TAG = DaoOption.class.getSimpleName();

    /**
     * 数据
     */
    private Dao<DtoOption, Integer> mOptionDao = null;
    /**
     * 单例对象
     */
    private static DaoOption sDaoOption = null;

    private DaoOption() {
        try {
            mOptionDao = DataBaseHelper.getInstance(DataBaseApplication.getInstance().getBaseContext()).getDao(DtoOption.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 单例方法
     *
     * @return DaoOption
     */
    public static DaoOption getInstance() {
        if (sDaoOption == null) {
            synchronized (DaoOption.class) {
                if (sDaoOption == null) {
                    sDaoOption = new DaoOption();
                }
            }
        }
        return sDaoOption;
    }

    /**
     * 添加一条记录
     *
     * @param option bean
     */
    public void addOption(DtoOption option) {
        if (option == null) {
            return;
        }
        try {
            mOptionDao.createIfNotExists(option);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除一条记录
     *
     * @param option bean
     */
    public void deleteOption(DtoOption option) {
        if (option == null) {
            return;
        }
        try {
            mOptionDao.delete(option);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除一条记录
     *
     * @param childerId 子课程id
     */
    public void deleteOption(int childerId) {
        DeleteBuilder<DtoOption, Integer> delete = mOptionDao
                .deleteBuilder();
        try {
            delete.where().eq(DataBaseConstant.OptionTable.COLUMN_CHILDERID, childerId);
            delete.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有选项
     *
     * @return 结果
     */
    public List<DtoOption> selectOption() {
        QueryBuilder<DtoOption, Integer> query = mOptionDao
                .queryBuilder();
        try {
            return query.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
