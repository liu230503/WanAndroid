package org.lmy.open.utillibrary.path;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;

/**********************************************************************
 *
 *
 * @类名 PathImpl
 * @包名 org.lmy.open.utillibrary
 * @author lmy
 * @创建日期 2018/3/5
 ***********************************************************************/
final class PathImpl implements IPath {
    /**
     * 上下文对象.
     */
    private Context mContext = null;
    /**
     * 缓存目录名称.
     */
    private static final String PATH_CACHE = "cache";
    /**
     * 系统目录名称.
     */
    private static final String PATH_SYSTEM = "system";
    /**
     * 日志目录名称.
     */
    private static final String PATH_LOGS = "logs";
    /**
     * 下载目录名称.
     */
    private static final String PATH_DOWNLOAD = "download";
    /**
     * 图片缓存目录名称.
     */
    private static final String PATH_CHACHE_IMAGE = "image";

    /**
     * 缩略图缓存目录名称.
     */
    private static final String PATH_CHACHE_THUMB_IMAGE = "thumb_image";

    /**
     * 轮播图片缓存目录名称.
     */
    private static final String PATH_CHACHE_IMAGE_BANNER = "banner";

    /**
     * 网络请求缓存路径名称.
     */
    private static final String PATH_CHACHE_NETWORK_REQUEST = "network_request";

    /**
     * SD卡根路径.
     */
    private String mSDRootPath = "";
    /**
     * 应用跟路径.
     */
    private String mRootPath = "";
    /**
     * 缓存路径.
     */
    private String mCachePath = "";
    /**
     * 日志路径.
     */
    private String mLogsPath = "";
    /**
     * 应用系统路径.
     */
    private String mSystemPath = "";
    /**
     * 下载路径.
     */
    private String mDownLoadPath = "";

    /**
     * 图片缓存路径.
     */
    private String mImageCachePath = "";

    /**
     * 缩略图缓存路径.
     */
    private String mThumbImageCachePath = "";

    /**
     * 轮播图片缓存路径.
     */
    private String mBannerImageCachePath = "";

    /**
     * 网络请求缓存路径.
     */
    private String mNetworkRequestCachePath = "";

    /**
     * @param pContext 上下文对象
     */
    PathImpl(Context pContext) {
        this.mContext = pContext;
    }

    /**
     * @param path 路径
     */
    private void createPath(String path) {
        if (!TextUtils.isEmpty(path)) {
            File file = new File(path);
            if (!file.isDirectory()) {
                file.mkdirs();
            }
        } else {
            Log.e(PathImpl.class.getName(), "创建文件夹失败！path: " + path);
        }
    }

    @Override
    public String getRootPath() {
        if (!TextUtils.isEmpty(mRootPath)) {
            return mRootPath;
        }
        if (mContext == null) {
            return "";
        }
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
        if (!sdCardExist) {
            mRootPath = mContext.getFilesDir().getAbsolutePath();
            return mRootPath;
        }
        String packageName = mContext.getPackageName();
        mRootPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator + "" + packageName;
        return mRootPath;
    }

    @Override
    public String getCachePath() {
        if (!TextUtils.isEmpty(mCachePath)) {
            return mCachePath;
        }
        mCachePath = getRootPath() + File.separator + PATH_CACHE
                + File.separator;
        createPath(mCachePath);
        return mCachePath;
    }

    @Override
    public String getSystemPath() {
        if (!TextUtils.isEmpty(mSystemPath)) {
            return mSystemPath;
        }
        mSystemPath = getRootPath() + File.separator + PATH_SYSTEM
                + File.separator;
        createPath(mSystemPath);
        return mSystemPath;
    }

    @Override
    public String getLogPath() {
        if (!TextUtils.isEmpty(mLogsPath)) {
            return mLogsPath;
        }
        mLogsPath = getRootPath() + File.separator + PATH_LOGS + File.separator;
        createPath(mLogsPath);
        return mLogsPath;
    }

    @Override
    public String getDownPath() {
        if (!TextUtils.isEmpty(mDownLoadPath)) {
            return mDownLoadPath;
        }
        mDownLoadPath = getRootPath() + File.separator + PATH_DOWNLOAD
                + File.separator;
        createPath(mDownLoadPath);
        return mDownLoadPath;
    }

    @Override
    public String getImageCachePath() {
        if (!TextUtils.isEmpty(mImageCachePath)) {
            return mImageCachePath;
        }
        mImageCachePath = getCachePath() + File.separator + PATH_CHACHE_IMAGE
                + File.separator;
        createPath(mImageCachePath);
        return mImageCachePath;
    }

    @Override
    public String getThumbImageCachePath() {
        if (!TextUtils.isEmpty(mThumbImageCachePath)) {
            return mThumbImageCachePath;
        }
        mThumbImageCachePath = getCachePath() + File.separator + PATH_CHACHE_THUMB_IMAGE
                + File.separator;
        createPath(mThumbImageCachePath);
        return mThumbImageCachePath;
    }

    @Override
    public String getBannerCachePath() {
        if (!TextUtils.isEmpty(mBannerImageCachePath)) {
            return mBannerImageCachePath;
        }
        mBannerImageCachePath = getImageCachePath() + File.separator + PATH_CHACHE_IMAGE_BANNER
                + File.separator;
        createPath(mBannerImageCachePath);
        return mBannerImageCachePath;
    }

    @Override
    public String getNetworkRequestCachePath() {
        if (!TextUtils.isEmpty(mNetworkRequestCachePath)) {
            return mNetworkRequestCachePath;
        }
        mNetworkRequestCachePath = getCachePath() + File.separator + PATH_CHACHE_NETWORK_REQUEST
                + File.separator;
        createPath(mNetworkRequestCachePath);
        return mNetworkRequestCachePath;
    }

    @Override
    public String getSDRootPath() {
        if (!TextUtils.isEmpty(mSDRootPath)) {
            return mSDRootPath;
        }
        if (mContext == null) {
            return "";
        }
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
        if (!sdCardExist) {
            mSDRootPath = mContext.getFilesDir().getAbsolutePath();
            return mSDRootPath;
        }
        mSDRootPath = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + File.separator;
        return mSDRootPath;
    }
}
