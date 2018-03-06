package org.lmy.open.utillibrary.imageload;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import org.lmy.open.utillibrary.LogHelper;
import org.lmy.open.utillibrary.MyResource;
import org.lmy.open.utillibrary.R;
import org.lmy.open.utillibrary.UtilApplication;

import java.util.Map;

/**********************************************************************
 *
 *
 * @类名 ImageLoaderHelper
 * @包名 org.lmy.open.utillibrary.ImageLoad
 * @author lmy
 * @创建日期 2018/3/5
 ***********************************************************************/
final class ImageLoaderHelper implements ILoadImage, ImageLoadingListener {
    /**
     * imageloader
     */
    private ImageLoader mImageLoader;
    /**
     * 选项集合
     */
    private Map<EnumImage, DisplayImageOptions> mOptionsMap;
    /**
     * 监听器
     */
    private Listener mListener;

    public ImageLoaderHelper() {
        mImageLoader = ImageLoader.getInstance();
        mOptionsMap = new ArrayMap<>();
    }

    /**
     * 初始化
     *
     * @param context 上下文
     */
    public void init(Context context) {
        LogHelper.d("初始化ImageLoaderHelper");
        for (EnumImage image : EnumImage.values()) {
            LoadImageConfigure.getCachePath(image);
            LoadImageConfigure.getThumbCachePath(image);
        }
        mImageLoader.init(LoadImageConfigure.buildConfig(context));
    }

    @Override
    public void loadImage(ImageView imageView, String url, EnumImage image, Listener listener) {
        if (TextUtils.isEmpty(url)) {
            LogHelper.dFullPath("loadImage Received a null url !!!");
            return;
        }
        mListener = listener;
        mImageLoader.displayImage(url, imageView, getOrCreateOptions(image), this);
    }

    /**
     * 生成选项
     *
     * @param image 图片类型
     * @return 选项
     */
    private DisplayImageOptions getOrCreateOptions(EnumImage image) {
        DisplayImageOptions options = mOptionsMap.get(image);
        if (options != null) {
            return options;
        }
        switch (image) {
            case BANNER:
                options = new DisplayImageOptions.Builder()
                        .showImageForEmptyUri(MyResource.getIdByName(UtilApplication.getInstance().getContext(), "mipmap", "picture_error"))
                        .showImageOnFail(MyResource.getIdByName(UtilApplication.getInstance().getContext(), "mipmap", "picture_error"))
                        .showImageOnLoading(MyResource.getIdByName(UtilApplication.getInstance().getContext(), "mipmap", "picture_error"))
                        .cacheInMemory(true)
                        .cacheOnDisk(true)
                        .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                        .bitmapConfig(Bitmap.Config.ARGB_8888)
                        .build();

                break;
            default:
                options = new DisplayImageOptions.Builder()
                        .build();
                break;
        }
        mOptionsMap.put(image, options);
        return options;
    }

    @Override
    public void onLoadingStarted(String imageUri, View view) {
        if (mListener != null) {
            mListener.onLoadingStarted(imageUri, view);
        }
    }

    @Override
    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
        if (mListener != null) {
            mListener.onLoadingFailed(imageUri, view);
        }
    }

    @Override
    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
        if (mListener != null) {
            mListener.onLoadingComplete(imageUri, view, loadedImage);
        }
    }

    @Override
    public void onLoadingCancelled(String imageUri, View view) {
        if (mListener != null) {
            mListener.onLoadingCancelled(imageUri, view);
        }
    }
}
