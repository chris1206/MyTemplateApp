package com.qbnet.viewutils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by Zc on 2018/1/25.
 */

@SuppressLint("AppCompatCustomView")
public class XEditText extends EditText implements View.OnKeyListener{
    public static final String TAG = "XEditText";
    private DrawableLeftListener mLeftListener ;
    private DrawableRightListener mRightListener ;

    final int DRAWABLE_LEFT = 0;
    final int DRAWABLE_TOP = 1;
    final int DRAWABLE_RIGHT = 2;
    final int DRAWABLE_BOTTOM = 3;
    /**
     * 软键盘搜索键监听
     */
    private OnSearchClickListener listener;

    public void setOnSearchClickListener(OnSearchClickListener listener) {
        this.listener = listener;
    }

    /**
     * 是否是默认图标再左边的样式
     */
    private boolean isLeft = false;

    @SuppressLint("NewApi")
    public XEditText(Context context, AttributeSet attrs, int defStyleAttr,
                     int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public XEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public XEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public XEditText(Context context) {
        super(context);
        init();
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

    public void setDrawableLeftListener(DrawableLeftListener listener) {
        this.mLeftListener = listener;
    }

    public void setDrawableRightListener(DrawableRightListener listener) {
        this.mRightListener = listener;
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
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

    public interface DrawableLeftListener {
        public void onDrawableLeftClick(View view) ;
    }

    public interface DrawableRightListener {
        public void onDrawableRightClick(View view) ;
    }

    private void init() {
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

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if (mRightListener != null) {
                    Drawable drawableRight = getCompoundDrawables()[DRAWABLE_RIGHT] ;
                    if (drawableRight != null && event.getRawX() >= (getRight() - drawableRight.getBounds().width()-getPaddingRight())) {
                        mRightListener.onDrawableRightClick(this) ;
                        return true ;
                    }
                }

                if (mLeftListener != null) {
                    Drawable drawableLeft = getCompoundDrawables()[DRAWABLE_LEFT] ;
                    if (drawableLeft != null && event.getRawX() <= (getLeft() + drawableLeft.getBounds().width()+getPaddingLeft())) {
                        mLeftListener.onDrawableLeftClick(this) ;
                        return true ;
                    }
                }
                break;
        }

        return super.onTouchEvent(event);
    }

    public void setLeft(boolean left) {
        isLeft = left;
    }

    public interface OnSearchClickListener {
        void onSearchClick(View view);
    }
}
