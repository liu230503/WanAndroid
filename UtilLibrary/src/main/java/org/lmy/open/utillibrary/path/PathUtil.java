package org.lmy.open.utillibrary.path;

import android.content.Context;

/**********************************************************************
 *
 *
 * @类名 PathUtil
 * @包名 org.lmy.open.utillibrary.path
 * @author lmy
 * @创建日期 2018/3/5
 ***********************************************************************/
public final class PathUtil implements IPath {
    /**
     * 单例对象
     */
    private static PathUtil sInstance;
    /**
     * 被代理类
     */
    private IPath mPathImpl;

    private PathUtil() {
    }

    /**
     * 单例方法
     *
     * @return PathUtil
     */
    public static PathUtil getInstance() {
        if (sInstance == null) {
            sInstance = new PathUtil();
        }
        return sInstance;
    }

    /**
     * 初始化
     *
     * @param pContext 上下文
     */
    public void init(Context pContext) {
        mPathImpl = new PathImpl(pContext);
    }

    @Override
    public String getRootPath() {
        if (mPathImpl == null) {
            return "";
        }
        return mPathImpl.getRootPath();
    }

    @Override
    public String getCachePath() {
        if (mPathImpl == null) {
            return "";
        }
        return mPathImpl.getCachePath();
    }

    @Override
    public String getSystemPath() {
        if (mPathImpl == null) {
            return "";
        }
        return mPathImpl.getSystemPath();
    }

    @Override
    public String getLogPath() {
        if (mPathImpl == null) {
            return "";
        }
        return mPathImpl.getLogPath();
    }

    @Override
    public String getDownPath() {
        if (mPathImpl == null) {
            return "";
        }
        return mPathImpl.getDownPath();
    }

    @Override
    public String getSDRootPath() {
        if (mPathImpl == null) {
            return "";
        }
        return mPathImpl.getSDRootPath();
    }
}
