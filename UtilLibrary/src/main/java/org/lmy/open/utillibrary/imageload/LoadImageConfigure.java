package org.lmy.open.utillibrary.imageload;

import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import org.lmy.open.utillibrary.LoggerUtil;
import org.lmy.open.utillibrary.path.PathUtil;

import java.io.File;

/**********************************************************************
 *
 *
 * @类名 LoadImageConfigure
 * @包名 org.lmy.open.utillibrary.ImageLoad
 * @author lmy
 * @创建日期 2018/3/5
 ***********************************************************************/
public class LoadImageConfigure {
    /**
     * 内存判断条件
     */
    private static final int CONDITION = 256;

    /**
     * 获取缓存路径
     *
     * @param image 图片类型
     * @return 路径
     */
    public static String getCachePath(EnumImage image) {
        switch (image) {
            case BANNER:
                return PathUtil.getInstance().getBannerCachePath();
            default:
                return PathUtil.getInstance().getImageCachePath();
        }
    }

    /**
     * 获取缩略图缓存路径
     *
     * @param image 图片类型
     * @return 路径
     */
    public static String getThumbCachePath(EnumImage image) {
        switch (image) {
            default:
                return "";
        }
    }

    /**
     * 计算图片缓存最大空间
     *
     * @return 缓存空间
     */
    public static int getMaxMemory() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int maxMemoryM = (maxMemory) / 1024 / 1024;
        //判断当前堆内存情况，当小于256M时，imageloader分配最大堆内存的30%；当大于256M时，imageloader分配最大堆内存的65%；
        if (maxMemoryM > CONDITION) {
            maxMemory = (int) (maxMemory * 0.65);
        } else {
            maxMemory = (int) (maxMemory * 0.3);
        }
        return maxMemory;
    }

    public static ImageLoaderConfiguration buildConfig(Context context) {
        ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(context)
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .threadPoolSize(1)
                .threadPriority(Thread.MIN_PRIORITY)
                .memoryCacheExtraOptions(480, 800)
                .diskCacheExtraOptions(480, 800, null)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024)
                .memoryCacheSizePercentage(13)
                .diskCache(new UnlimitedDiskCache(new File(getCachePath(EnumImage.DEFAULT))))
                .diskCacheSize(getMaxMemory())
                .diskCacheFileCount(100)
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
                .imageDownloader(new BaseImageDownloader(context))
                .imageDecoder(new BaseImageDecoder(true))
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .writeDebugLogs();
        ImageLoaderConfiguration config = builder.build();
        return config;
    }
}
