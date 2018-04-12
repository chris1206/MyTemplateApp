package com.yto.template.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.yto.template.ui.EditActivity;

/**
 * Created by Zc on 2018/4/2.
 */

@SuppressLint("AppCompatCustomView")
public class ScrollEditText extends EditText{
    public ScrollEditText(Context context) {
        super(context);
    }

    public ScrollEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public boolean onTouchEvent(MotionEvent evt) {
        switch (evt.getAction()) {
            case MotionEvent.ACTION_MOVE:
                // 通知其父控件，现在进行的是本控件的操作，不允许拦截
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
        }
        return super.onTouchEvent(evt);
    }
}
