package org.lmy.open.database;

/**********************************************************************
 *
 *
 * @类名 DataBaseConstant
 * @包名 org.lmy.open.database
 * @author lmy
 * @创建日期 2018/3/2
 ***********************************************************************/
public final class DataBaseConstant {
    /**
     * 数据库文件名
     */
    public static final String DB_NAME = "wanandroid.db";
    /**
     * 数据库版本号
     */
    public static final int DB_VERSION = 1;

    /**
     * 搜索记录表
     */
    public static class SearchRecordTable {
        /**
         * 表名
         */
        public static final String TABLE_NAME = "SearchRecord";
        /**
         * 自增长id
         */
        public static final String COLUMN_ID = "ID";
        /**
         * 搜索关键字
         */
        public static final String COLUMN_KEYWORD = "Keyword";
        /**
         * 搜索次数
         */
        public static final String COLUMN_SEARCHNUMBER = "SearchNumber";
        /**
         * 上次搜索时间
         */
        public static final String COLUMN_LASTTIME = "LastTime";
    }
}