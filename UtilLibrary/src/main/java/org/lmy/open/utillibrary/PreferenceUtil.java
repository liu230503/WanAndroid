package org.lmy.open.utillibrary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

/**********************************************************************
 *
 *
 * @类名 PreferenceUtil
 * @包名 org.lmy.open.utillibrary
 * @author lmy
 * @创建日期 2018/2/27
 ***********************************************************************/
public final class PreferenceUtil {
    /**
     * 配置存储
     */
    private static SharedPreferences sPreference;
    /**
     * 编辑器
     */
    private static Editor sEditor;
    /**
     * 单例对象
     */
    private static PreferenceUtil sInstance = null;

    @SuppressLint("CommitPrefEdits")
    private PreferenceUtil(Context context) {
        sPreference = PreferenceManager.getDefaultSharedPreferences(context);
        sEditor = sPreference.edit();
    }

    /**
     * 单例方法
     *
     * @param context 上下文
     * @return 单例对象
     */
    public static PreferenceUtil getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PreferenceUtil(context);
        }
        return sInstance;
    }

    /**
     * 取得存储器
     *
     * @return 存储器
     */
    public static SharedPreferences getPreference() {
        return sPreference;
    }

    /**
     * 保存String类型
     *
     * @param key   键
     * @param value 键
     */
    public void putString(String key, String value) {
        sEditor.putString(key, value);
        sEditor.commit();
    }

    /**
     * 保存布尔类型键值对
     *
     * @param key   键
     * @param value 键
     */
    public void putBoolean(String key, boolean value) {
        sEditor.putBoolean(key, value);
        sEditor.commit();
    }

    /**
     * 获取String类型值
     *
     * @param key 键
     * @return 值
     */
    public String getString(String key) {
        return sPreference.getString(key, "");
    }

    /**
     * 获取布尔类型值
     *
     * @param key 键
     * @return 值
     */
    public boolean getBoolean(String key) {
        return sPreference.getBoolean(key, false);
    }

    /**
     * 获取带默认值的布尔类型值
     *
     * @param key   键
     * @param value 默认值
     * @return 值
     */
    public boolean getBoolean(String key, boolean value) {
        return sPreference.getBoolean(key, value);
    }

    /**
     * 保存Long型数据
     *
     * @param key   键
     * @param value 值
     */
    public void putLong(String key, long value) {
        sEditor.putLong(key, value);
        sEditor.commit();
    }

    /**
     * 获取Long型数据
     *
     * @param key 键
     * @return 值
     */
    public long getLong(String key) {
        return sPreference.getLong(key, 0);
    }

    /**
     * 保存整数类型数据
     *
     * @param key   键
     * @param value 值
     */
    public void putInt(String key, int value) {
        sEditor.putInt(key, value);
        sEditor.commit();
    }

    /**
     * 获取整数类型数据
     *
     * @param key 键
     * @return 值
     */
    public int getInt(String key) {
        return sPreference.getInt(key, 0);
    }
}
