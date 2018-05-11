package com.yto.template.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


public class IconCenterEditText extends android.support.v7.widget.AppCompatEditText implements View.OnKeyListener {
    private static final String TAG = IconCenterEditText.class.getSimpleName();

    public void setLeft(boolean left) {
        isLeft = left;
    }

    /**
     * 是否是默认图标再左边的样式
     */
    private boolean isLeft = false;
    /**
     * 是否点击软键盘搜索
     */
//    private boolean pressSearch = false;
    /**
     * 软键盘搜索键监听
     */
    private OnSearchClickListener listener;

    public void setOnSearchClickListener(OnSearchClickListener listener) {
        this.listener = listener;
    }

    public IconCenterEditText(Context context) {
        this(context, null);
        init();
    }

    public IconCenterEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
        init();
    }

    public IconCenterEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
//        setOnFocusChangeListener(this);
        setOnKeyListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isLeft) { // 如果是默认样式，则直接绘制
            super.onDraw(canvas);
        } else { // 如果不是默认样式，需要将左侧图标绘制在中间，左上右下顺序

            translate_to_center(canvas);

            super.onDraw(canvas);
        }

    }

    private void translate_to_center(Canvas canvas) {
        Drawable[] drawables = getCompoundDrawables();
        Drawable drawableLeft = drawables[0];
//        drawables[2].setVisible(false, false);
        translate(drawableLeft, canvas);
    }

    public void translate(Drawable drawable, Canvas canvas) {
        if (drawable != null) {
            float textWidth = getPaint().measureText(getHint().toString());
            int drawablePadding = getCompoundDrawablePadding();
            int drawableWidth = drawable.getIntrinsicWidth();
            float bodyWidth = textWidth + drawableWidth + drawablePadding;
            if (drawable == getCompoundDrawables()[0]) {
                canvas.translate((getWidth() - bodyWidth - getPaddingLeft() - getPaddingRight()) / 2, 0);
            } else {
                setPadding(getPaddingLeft(), getPaddingTop(), (int)(getWidth() - bodyWidth - getPaddingLeft()), getPaddingBottom());
                canvas.translate((getWidth() - bodyWidth - getPaddingLeft()) / 2, 0);
            }
        }
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        if(focused){
            isLeft = true;
        }else{
            if(this.getText().toString().trim().length()<=0){
                isLeft = false;
            }
        }
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
//        pressSearch = (keyCode == KeyEvent.KEYCODE_ENTER);
        if (listener != null) {
            /*隐藏软键盘*/
            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm.isActive()) {
                imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
            }
            if (event.getAction() == KeyEvent.ACTION_UP) {
                listener.onSearchClick(v);
            }
        }
        return false;
    }

    public interface OnSearchClickListener {
        void onSearchClick(View view);
    }

    //触摸事件
    //判断DrawableLeft/DrawableRight是否被点击
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //触摸状态
        if (event.getAction() == MotionEvent.ACTION_UP) {
            //监听DrawableLeft
            if (onDrawableLeftListener != null) {
                //判断DrawableLeft是否被点击
                Drawable drawableLeft = getCompoundDrawables()[0];
                //当按下的位置<在EditText的到左边间距+图标的宽度+Padding
                if (drawableLeft != null && event.getRawX() <= (getLeft() + getTotalPaddingLeft() + drawableLeft.getBounds().width())) {
                    //执行DrawableLeft点击事件
                    onDrawableLeftListener.onDrawableLeftClick();
                }
            }
            //监听DrawableRight
            if (onDrawableRightListener != null) {
                Drawable drawableRight = getCompoundDrawables()[2];
                //当按下的位置>在EditText的到右边间距-图标的宽度-Padding
                if (drawableRight != null && event.getRawX() >= (getRight() - getTotalPaddingRight() - drawableRight.getBounds().width())) {
                    //执行DrawableRight点击事件
                    onDrawableRightListener.onDrawableRightClick();
                }
            }
        }
        return super.onTouchEvent(event);
    }

    //定义一个DrawableLeft点击事件接口
    public interface OnDrawableLeftListener {
        void onDrawableLeftClick();
    }

    private OnDrawableLeftListener onDrawableLeftListener;

    public void setOnDrawableLeftListener(OnDrawableLeftListener onDrawableLeftListener) {
        this.onDrawableLeftListener = onDrawableLeftListener;
    }

    //定义一个DrawableRight点击事件接口
    public interface OnDrawableRightListener {
        void onDrawableRightClick();
    }

    private OnDrawableRightListener onDrawableRightListener;

    public void setOnDrawableRightListener(OnDrawableRightListener onDrawableRightListener) {
        this.onDrawableRightListener = onDrawableRightListener;
    }



}