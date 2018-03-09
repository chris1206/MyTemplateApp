package com.yto.template.ui.adapter;

import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Zc on 2018/1/29.
 */

public class MyItemDecoration extends RecyclerView.ItemDecoration{
    private int mColor;
    private int type;
    protected Drawable mDivider;
    public MyItemDecoration(){
        mColor = Color.parseColor("#dddddd");
        if (mColor != 0) {
            mDivider = new ColorDrawable(mColor);
        }
    }
    public MyItemDecoration(int type){
        this.type = type;
        mColor = Color.parseColor("#dddddd");
        if (mColor != 0) {
            mDivider = new ColorDrawable(mColor);
        }

    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //设定底部边距为1px
        if(type == 0){
            outRect.set(0, 0, 10, 10);
        }else{
            outRect.set(0, 0, 0, 1);
        }
    }

}
