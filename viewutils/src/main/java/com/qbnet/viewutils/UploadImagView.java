package com.qbnet.viewutils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Zc on 2018/1/16.
 */

@SuppressLint("AppCompatCustomView")
public class UploadImagView extends ImageView {
    // 淡白色
    private int WHITE_COLOR = Color.parseColor("#00000000");
    // 橙色
    private int ORANGE_COLOR = Color.parseColor("#88000000");
    //最大的进度
    private static final int TOTAL_PROGRESS = 100;
    //整个view的宽高
    private int mTotleWidth,mTotleHeight;
    //透明画笔、半透明画笔
    private Paint mTransPaint, mTransluPaint;
    //透明区域、半透明区域
    private RectF mTransRectF, mTransluRectF;
    //整个view的区域
    private Rect mTotleRect;
    //当前进度
    private int mProgress;
    // 所绘制的进度条部分的宽度
    private int mProgressHeight;


    public UploadImagView(Context context) {
        this(context,null);
    }

    public UploadImagView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mTransPaint = new Paint();
        mTransPaint.setAntiAlias(true);
        mTransPaint.setColor(WHITE_COLOR);

        mTransluPaint = new Paint();
        mTransluPaint.setAntiAlias(true);
        mTransluPaint.setColor(ORANGE_COLOR);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTotleWidth = w;
        mTotleHeight = h;
        mTransRectF = new RectF(0,0,mTotleWidth,mTotleHeight - mProgressHeight);
        mTransluRectF = new RectF(0,mTotleHeight - mProgressHeight,mTotleWidth,mTotleHeight);
        mTotleRect = new Rect(0,0,mTotleWidth,mTotleHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mProgress < TOTAL_PROGRESS){
            drawWAndO(canvas);
            postInvalidate();
        }else{
            canvas.drawRect(mTotleRect,mTransluPaint);
        }
    }
    private void drawWAndO(Canvas canvas) {
        if (mProgress >= TOTAL_PROGRESS) {
            mProgress = 0;
        }
//        canvas.rotate(value,mTotleWidth/2,mTotleHeight/2);
        mProgressHeight = mTotleWidth*mProgress/TOTAL_PROGRESS;
        //绘制透明
        mTransRectF.bottom = mTotleHeight - mProgressHeight;
        canvas.drawRect(mTransRectF,mTransPaint);
        //绘制半透明
        mTransluRectF.top = mTotleHeight - mProgressHeight;
        canvas.drawRect(mTransluRectF,mTransluPaint);
    }


    public void setProgress(int progress){
        mProgress = progress;
        postInvalidate();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
