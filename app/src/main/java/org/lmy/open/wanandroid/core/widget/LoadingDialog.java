package org.lmy.open.wanandroid.core.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.widget.ImageView;

import org.lmy.open.wanandroid.R;

/**********************************************************************
 *
 *
 * @类名 LoadingDialog
 * @包名 org.lmy.open.wanandroid.core.widget
 * @author lmy
 * @创建日期 2018/3/12
 ***********************************************************************/
public final class LoadingDialog extends Dialog {
    /**
     * LoadingDialog
     */
    private static LoadingDialog sLoadingDialog = null;

    private LoadingDialog(Context context) {
        super(context);
    }

    private LoadingDialog(Context context, int them) {
        super(context, them);
    }

    /**
     * 创建dialog
     *
     * @param context 上下文
     * @return LoadingDialog
     */
    public static LoadingDialog createDialog(Context context) {
        sLoadingDialog = new LoadingDialog(context, R.style.CustomProgressDialog);
        sLoadingDialog.setContentView(R.layout.layout_loading_dialog);
        return sLoadingDialog;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        if (sLoadingDialog == null) {
            return;
        }

        ImageView imageView = sLoadingDialog.findViewById(R.id.iv_loading);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
        animationDrawable.start();
    }
}
