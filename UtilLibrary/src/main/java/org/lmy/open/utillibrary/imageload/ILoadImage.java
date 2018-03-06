package org.lmy.open.utillibrary.imageload;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

/**********************************************************************
 *
 *
 * @类名 ILoadImage
 * @包名 org.lmy.open.utillibrary.ImageLoad
 * @author lmy
 * @创建日期 2018/3/5
 ***********************************************************************/
interface ILoadImage {
    /**
     * 加载图片
     *
     * @param imageView 组件
     * @param url       图片地址
     * @param image     图片类型
     * @param listener  监听器
     */
    void loadImage(ImageView imageView, String url, EnumImage image, Listener listener);

    interface Listener {
        /**
         * 开始加载
         *
         * @param imageUri 图片地址
         * @param view     view
         */
        void onLoadingStarted(String imageUri, View view);

        /**
         * 加载失败
         *
         * @param imageUri 图片地址
         * @param view     view
         */
        void onLoadingFailed(String imageUri, View view);

        /**
         * 加载成功
         *
         * @param imageUri    图片地址
         * @param view        view
         * @param loadedImage bitmap
         */
        void onLoadingComplete(String imageUri, View view, Bitmap loadedImage);

        /**
         * 加载完成
         *
         * @param imageUri 图片地址
         * @param view     view
         */
        void onLoadingCancelled(String imageUri, View view);
    }
}
