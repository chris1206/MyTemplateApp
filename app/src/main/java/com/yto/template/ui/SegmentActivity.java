package com.yto.template.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yto.template.R;
import com.yto.template.base.BaseActivity;
import com.yto.template.utils.Utils;

import java.lang.reflect.Field;

/**
 * Created by Chris on 2018/2/1.
 */

public class SegmentActivity extends BaseActivity{
    private Toolbar mToolBar;
    private TextView tv_title;
    private TabLayout mTabLayout1;
    private TabLayout mTabLayout2;

    private TabLayout mTabLayout3;
    private TabLayout mTabLayout4;
    private TabLayout mTabLayout5;
    private TabLayout mTabLayout6;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_segment;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initTabs();
    }

    private void initView() {
        mTabLayout1 = findViewById(R.id.tabLayout1);
        mTabLayout2 = findViewById(R.id.tabLayout2);
        mTabLayout3 = findViewById(R.id.tabLayout3);
        mTabLayout4 = findViewById(R.id.tabLayout4);
        mTabLayout5 = findViewById(R.id.tabLayout5);
        mTabLayout6 = findViewById(R.id.tabLayout6);
        tv_title = findViewById(R.id.tv_title);
        mTabLayout3 = findViewById(R.id.tabLayout3);
        tv_title.setText("分段控件");
        mToolBar = findViewById(R.id.toolbar);
        mToolBar.setNavigationIcon(getResources().getDrawable(R.mipmap.arrow_white));
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initTabs() {
        mTabLayout1.addTab(mTabLayout1.newTab().setText("选项一"));
        mTabLayout1.addTab(mTabLayout1.newTab().setText("选项二"));

        mTabLayout2.addTab(mTabLayout2.newTab().setText("选项一"));
        mTabLayout2.addTab(mTabLayout2.newTab().setText("选项二"));
        mTabLayout2.addTab(mTabLayout2.newTab().setText("选项三"));

        mTabLayout3.addTab(mTabLayout3.newTab().setText("选项一"));
        mTabLayout3.addTab(mTabLayout3.newTab().setText("选项二"));

        mTabLayout4.addTab(mTabLayout4.newTab().setText("选项一"));
        mTabLayout4.addTab(mTabLayout4.newTab().setText("选项二"));
        mTabLayout4.addTab(mTabLayout4.newTab().setText("选项三"));
        reflex(mTabLayout4,30);

        mTabLayout5.addTab(mTabLayout5.newTab().setText("选项一"));
        mTabLayout5.addTab(mTabLayout5.newTab().setText("选项二"));
        mTabLayout5.addTab(mTabLayout5.newTab().setText("选项三"));
        mTabLayout5.addTab(mTabLayout5.newTab().setText("选项四"));

        mTabLayout6.addTab(mTabLayout6.newTab().setText("选项一"));
        mTabLayout6.addTab(mTabLayout6.newTab().setText("选项二"));
        mTabLayout6.addTab(mTabLayout6.newTab().setText("选项三"));
        mTabLayout6.addTab(mTabLayout6.newTab().setText("选项四"));
        mTabLayout6.addTab(mTabLayout6.newTab().setText("选项五"));
        mTabLayout6.addTab(mTabLayout6.newTab().setText("选项六"));
        mTabLayout6.addTab(mTabLayout6.newTab().setText("选项七"));
        mTabLayout6.addTab(mTabLayout6.newTab().setText("选项八"));
        mTabLayout6.addTab(mTabLayout6.newTab().setText("选项九"));
        mTabLayout6.addTab(mTabLayout6.newTab().setText("选项十"));

        ViewCompat.setElevation(mTabLayout6, 10);//设置海拔高度-Z值
        mTabLayout6.setTabMode(TabLayout.MODE_SCROLLABLE);

        mTabLayout1.setSelectedTabIndicatorColor(Color.TRANSPARENT);
        mTabLayout2.setSelectedTabIndicatorColor(Color.TRANSPARENT);
        mTabLayout3.setSelectedTabIndicatorColor(getResources().getColor(R.color.indicator_color));
        mTabLayout4.setSelectedTabIndicatorColor(getResources().getColor(R.color.indicator_color));
        mTabLayout5.setSelectedTabIndicatorColor(getResources().getColor(R.color.indicator_color));
        mTabLayout6.setTabTextColors(ContextCompat.getColor(this, R.color.btn_selected),
                ContextCompat.getColor(this, R.color.btn_normal));
        mTabLayout6.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.indicator_color));

    }

    /**
     * 通过反射改变滑动条的长度-与内容同宽
     * @param tabLayout
     */
    public void reflex(final TabLayout tabLayout, int dividerWidth){
        //通过源码得知 线的宽度是根据 tabView的宽度来设置的,post会执行handler线程
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //拿到tabLayout的SlidingTabStrip（移动导航条）mTabStrip 属性
                    LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);

                    int dp10 = Utils.dip2px(tabLayout.getContext(), dividerWidth);

                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);

                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);

                        TextView mTextView = (TextView) mTextViewField.get(tabView);

                        tabView.setPadding(0, 0, 0, 0);

                        //效果是 字多宽线就多宽，所以测量mTextView的宽度
                        int width;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }

                        //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width ;
                        params.leftMargin = dp10;
                        params.rightMargin = dp10;
                        tabView.setLayoutParams(params);

                        tabView.invalidate();
                    }

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

    }


}
