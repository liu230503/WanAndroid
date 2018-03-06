package org.lmy.open.utillibrary.path;

/**********************************************************************
 *
 *
 * @类名 IPath
 * @包名 org.lmy.open.utillibrary
 * @author lmy
 * @创建日期 2018/3/5
 ***********************************************************************/
interface IPath {
    /**
     * sdk路径
     *
     * @return 路径
     */
    String getSDRootPath();

    /**
     * 根路径
     *
     * @return 路径
     */
    String getRootPath();

    /**
     * 缓存路径
     *
     * @return 路径
     */
    String getCachePath();

    /**
     * 系统路径
     *
     * @return 路径
     */
    String getSystemPath();

    /**
     * 日志路径
     *
     * @return 路径
     */
    String getLogPath();

    /**
     * 下载路径
     *
     * @return 路径
     */
    String getDownPath();


    /**
     * 图片缓存路径
     *
     * @return 路径
     */
    String getImageCachePath();

    /**
     * 缩略图缓存路径
     *
     * @return 路径
     */
    String getThumbImageCachePath();

    /**
     * 轮播图片缓存路径
     *
     * @return 路径
     */
    String getBannerCachePath();
}
