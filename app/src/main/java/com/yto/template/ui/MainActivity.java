package com.yto.template.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yto.template.R;
import com.yto.template.base.BaseActivity;
import com.yto.template.customview.QMUITabSegment;
import com.yto.template.fragment.FragBasicComponet;
import com.yto.template.fragment.FragComposite;
import com.yto.template.fragment.FragInfoFeedback;
import com.yto.template.fragment.FragInfoInput;
import com.yto.template.fragment.FragInfoOutput;
import com.yto.template.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Chris on 2018/1/24.
 */

public class MainActivity extends BaseActivity {

    @BindView(R.id.tabSegment) QMUITabSegment mTabSegment;
    @BindView(R.id.contentViewPager) ViewPager mContentViewPager;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    //注意butterknife只能对view进行注解，toolbar不支持
//    private Toolbar mTopBar;
    private List<Fragment> fragmentList = new ArrayList<>();
    private MyPagerAdapter mPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        initTopBar();
        initAdapter();
        initTabAndPager();
    }

    private void initTopBar() {
//        mTopBar = findViewById(R.id.toolbar);
        back.setVisibility(View.GONE);
        title.setText("移动APP组件标准化常用控件库");
    }

    private void initAdapter() {
        fragmentList.add(new FragBasicComponet());
        fragmentList.add(new FragInfoInput());
        fragmentList.add(new FragInfoOutput());
        fragmentList.add(new FragInfoFeedback());
        fragmentList.add(new FragComposite());
        mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), fragmentList);
    }

    private void initTabAndPager() {
        mContentViewPager.setAdapter(mPagerAdapter);
        mContentViewPager.setCurrentItem(0, false);
        mTabSegment.setHasIndicator(true);
        mTabSegment.setIndicatorWidthAdjustContent(true);
        mTabSegment.setDefaultNormalColor(getResources().getColor(R.color.btn_selected));
        mTabSegment.setDefaultSelectedColor(getResources().getColor(R.color.btn_normal));
        mTabSegment.setDefaultIndicatorColor(getResources().getColor(R.color.indicator_color));

        QMUITabSegment.Tab tab1 = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(getApplicationContext(), R.mipmap.icon_nav1_unfocus),
                ContextCompat.getDrawable(getApplicationContext(), R.mipmap.icon_nav1_focus),
                "常用组件", false
        );
        tab1.setTextSize(Utils.dip2px(MainActivity.this, 12));
        QMUITabSegment.Tab tab2 = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(getApplicationContext(), R.mipmap.icon_nav2_unfocus),
                ContextCompat.getDrawable(getApplicationContext(), R.mipmap.icon_nav2_focus),
                "信息输入", false
        );
        tab2.setTextSize(Utils.dip2px(MainActivity.this, 12));
        QMUITabSegment.Tab tab3 = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(getApplicationContext(), R.mipmap.icon_nav3_unfocus),
                ContextCompat.getDrawable(getApplicationContext(), R.mipmap.icon_nav3_focus),
                "信息输出", false
        );
        tab3.setTextSize(Utils.dip2px(MainActivity.this, 12));
        QMUITabSegment.Tab tab4 = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(getApplicationContext(), R.mipmap.icon_nav4_unfocus),
                ContextCompat.getDrawable(getApplicationContext(), R.mipmap.icon_nav4_focus),
                "信息反馈", false
        );
        tab4.setTextSize(Utils.dip2px(MainActivity.this, 12));
        QMUITabSegment.Tab tab5 = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(getApplicationContext(), R.mipmap.icon_nav5_unfocus),
                ContextCompat.getDrawable(getApplicationContext(), R.mipmap.icon_nav5_focus),
                "综合系列", false
        );
        tab5.setTextSize(Utils.dip2px(MainActivity.this, 12));
        mTabSegment.setDefaultTabIconPosition(QMUITabSegment.ICON_POSITION_TOP);
        mTabSegment.addTab(tab1).addTab(tab2).addTab(tab3).addTab(tab4).addTab(tab5);
        mTabSegment.setupWithViewPager(mContentViewPager, false);
        mTabSegment.setMode(QMUITabSegment.MODE_FIXED);

        mTabSegment.addOnTabSelectedListener(new QMUITabSegment.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int index) {
                mTabSegment.hideSignCountView(index);
            }

            @Override
            public void onTabUnselected(int index) {

            }

            @Override
            public void onTabReselected(int index) {
                mTabSegment.hideSignCountView(index);
            }

            @Override
            public void onDoubleTap(int index) {

            }
        });
    }

//    private View getPageView(ContentPage page) {
//        View view = mPageMap.get(page);
//        if (view == null) {
//            TextView textView = new TextView(this);
//            textView.setGravity(Gravity.CENTER);
//            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
//            textView.setTextColor(ContextCompat.getColor(this, R.color.red));
//
//            if (page == ContentPage.Item1) {
//                textView.setText("item1");
//            } else if (page == ContentPage.Item2) {
//                textView.setText("item2");
//            } else if (page == ContentPage.Item3) {
//                textView.setText("item3");
//            } else if (page == ContentPage.Item4) {
//                textView.setText("item4");
//            } else if (page == ContentPage.Item5) {
//                textView.setText("item5");
//            }
//
//            view = textView;
//            mPageMap.put(page, view);
//        }
//        return view;
//    }

    private class MyPagerAdapter extends FragmentPagerAdapter{
        private List<Fragment> fragmentList;

        public MyPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragmentList = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return (fragmentList == null || fragmentList.size() == 0)? null : fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }
    }

//    public enum ContentPage {
//        Item1(0),
//        Item2(1),
//        Item3(2),
//        Item4(3),
//        Item5(4);
//
//        public static final int SIZE = 5;
//        private final int position;
//
//        ContentPage(int pos) {
//            position = pos;
//        }
//
//        public static ContentPage getPage(int position) {
//            switch (position) {
//                case 0:
//                    return Item1;
//                case 1:
//                    return Item2;
//                case 2:
//                    return Item3;
//                case 3:
//                    return Item4;
//                case 4:
//                    return Item5;
//                default:
//                    return Item1;
//            }
//        }
//
//        public int getPosition() {
//            return position;
//        }
//    }

//    在Activity里面注册个 ContentObserver
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//    }




}
