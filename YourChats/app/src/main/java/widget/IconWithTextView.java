package widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;

import com.pingan.yourchats.R;
import com.pingan.yourchats.utils.LogUtils;

/**
 * Author：liupeng on 2016/11/21 16:46
 * Address：liupeng264@pingan.com.cn
 */
public class IconWithTextView extends View {

    private int    mTextColor;
    private String mText;
    private float  mTextSize;
    private Bitmap mIconSrc;
    private Paint  mPaint;
    private Rect   dstRect;//bitmap的矩形区域
    private int    mMeasuredHeight;
    private int    mMeasuredWidth;
    private int    mTextHeight;
    private int    mTextWidth;
    private int    mTextX;
    private int    mTextY;
    private String tag = IconWithTextView.class.getSimpleName();
    private int    mAlpha;
    private Bitmap maskBitmap;

    public IconWithTextView(Context context) {
        this(context, null);
    }

    public IconWithTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IconWithTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.IconWithTextView);
        mTextColor = a.getColor(R.styleable.IconWithTextView_Tab_TextColor, getResources().getColor(R.color.colorTabselect));
        mText = a.getString(R.styleable.IconWithTextView_Tab_Text);
        mTextSize = a.getDimension(R.styleable.IconWithTextView_Tab_TextSize, 16);
        BitmapDrawable d = (BitmapDrawable) a.getDrawable(R.styleable.IconWithTextView_Tab_IconSrc);
        mIconSrc = d.getBitmap();
        a.recycle();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(mTextSize);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mMeasuredHeight = getMeasuredHeight();
        mMeasuredWidth = getMeasuredWidth();
        LogUtils.i("mMeasuredHeight=" + mMeasuredHeight);
        // 想知道 图或文的 尺寸
        int height = mIconSrc.getHeight();
        LogUtils.i("height=" + height);

        Rect rect = new Rect();
        // mText 的宽高复制到了rect中
        mPaint.getTextBounds(mText, 0, mText.length(), rect);
        //mText 宽高
        mTextHeight = rect.height();
        mTextWidth = rect.width();
        int margin = (int) (getResources().getDisplayMetrics().density * 4);
        LogUtils.i("margin=" + margin);
        int left = (mMeasuredWidth - mTextWidth) / 2;
        int top = margin;
        int right = mMeasuredWidth - left;
        int bottom = mMeasuredHeight - mTextHeight - margin;
        dstRect = new Rect(left, top, right, bottom);

        mTextX = left;
        mTextY = mMeasuredHeight - margin;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setAlpha(255);
        canvas.drawBitmap(mIconSrc, null, dstRect, mPaint);


        drawMaskBitmap(mAlpha);
        //将内存中的纯色图片画到控件上
        mPaint.setAlpha(mAlpha);
        canvas.drawBitmap(maskBitmap, 0, 0, mPaint);

        //画文字
        mPaint.setColor(Color.BLACK);
        mPaint.setAlpha(255 - mAlpha);
        canvas.drawText(mText, mTextX, mTextY, mPaint);

        //画纯色的文字
        mPaint.setColor(Color.GREEN);
        mPaint.setAlpha(mAlpha);
        canvas.drawText(mText, mTextX, mTextY, mPaint);
    }

    private void drawMaskBitmap(int alpha) {
        //创建同大小的bitmap
        maskBitmap = Bitmap.createBitmap(mMeasuredWidth, mMeasuredHeight, Bitmap.Config.ARGB_8888);

        //获取该bitmap的画布canvas
        Canvas canvas = new Canvas(maskBitmap);

        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setAlpha(255);

        //在该画布上 画一个纯色的矩形
        canvas.drawRect(dstRect, mPaint);

        //原始icon和纯色区域取交集
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        paint.setAlpha(255);
        canvas.drawBitmap(mIconSrc, null, dstRect, paint);
    }


    public void setIconTextAlpha(float alpha) {
        this.mAlpha = (int) (alpha * 255);
        //重绘界面
        if (Looper.getMainLooper() == Looper.myLooper()) {
            invalidate();
        } else {
            postInvalidate();
        }
    }
}
