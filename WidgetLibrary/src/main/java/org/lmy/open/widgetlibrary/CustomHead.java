package org.lmy.open.widgetlibrary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**********************************************************************
 *
 *
 * @类名 CustomHead
 * @包名 org.lmy.open.widgetlibrary
 * @author lmy
 * @创建日期 2018/3/8
 ***********************************************************************/
@SuppressLint("AppCompatCustomView")
public class CustomHead extends ImageView {
    /**
     * 默认圆角度数
     */
    private static final int DEFAULT_RADIUS = 100;
    /**
     * 图片着色器
     */
    private BitmapShader mBitmapShader;
    /**
     * 图片画笔
     */
    private Paint mBitmapPaint = new Paint();
    /**
     * 图片矩阵
     */
    private RectF mImageRect = new RectF();
    /**
     * 角度
     */
    private int mXRadius;
    /**
     * 角度
     */
    private int mYRadius;
    /**
     * 是否显示边框
     */
    private boolean isShowFrame = false;


    public CustomHead(Context context) {
        super(context);
        init(context);
    }

    public CustomHead(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init(context);
    }

    public CustomHead(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化
     *
     * @param context 上下文
     */
    private void init(Context context) {
        mBitmapPaint.setColor(Color.WHITE);
        mBitmapPaint.setAntiAlias(true);
        mXRadius = DEFAULT_RADIUS;
        mYRadius = DEFAULT_RADIUS;
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        createBitmapShader();
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
//      createBitmapShader();
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        createBitmapShader();
    }

    public void showFrame(boolean isShow) {
        isShowFrame = isShow;
    }

    /**
     * 创建图片着色器
     */
    private void createBitmapShader() {
        mBitmapShader = null;
        Drawable mDrawable = getDrawable();
        if (null == mDrawable) {
            return;
        }

        if (mDrawable instanceof BitmapDrawable) {
            BitmapDrawable bd = (BitmapDrawable) mDrawable;
            Bitmap bitmap = bd.getBitmap();
            if (bitmap == null) {
                return;
            }
            mBitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        } else {
            int w = mDrawable.getIntrinsicWidth();
            int h = mDrawable.getIntrinsicHeight();

            Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            mDrawable.setBounds(0, 0, w, h);
            mDrawable.draw(canvas);
            mBitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mImageRect.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Drawable mDrawable = getDrawable();
        if (null == mDrawable || null == mBitmapShader) {
            return;
        }
        Matrix mDrawMatrix = getImageMatrix();
        if (null == mDrawMatrix) {
            mDrawMatrix = new Matrix();
        }
        mBitmapShader.setLocalMatrix(mDrawMatrix);
        mBitmapPaint.setShader(mBitmapShader);
        canvas.drawRoundRect(mImageRect, mXRadius, mYRadius, mBitmapPaint);
        if (isShowFrame) {
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(1);
            canvas.drawRoundRect(mImageRect, mXRadius, mYRadius, paint);
        }
    }
}
