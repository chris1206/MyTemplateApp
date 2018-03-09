package com.yto.template.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yto.template.R;
import com.yto.template.base.BaseActivity;
import com.yto.template.customview.BadgeView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 2018/1/31.
 * 自定义标签栏界面
 */

public class TabActivity extends BaseActivity {
    private Toolbar mToolBar;
    private TextView tv_title;
    private TabLayout tabLayout1;
    private TabLayout tabLayout2;
    private TabLayout tabLayout3;
    private TabLayout tabLayout4;

    TabLayout.Tab itemTab;

    private String[] tabIndicators = {"功能一", "功能二", "功能三", "功能四"};
    private int[] tabDrawables = {R.drawable.item1_selector, R.drawable.item2_selector,
            R.drawable.item3_selector, R.drawable.item4_selector};
    private BadgeView badgeView;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_tab;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initTab(tabLayout1,"1");
        initTab(tabLayout2,"12");
        initTab(tabLayout3,"...");
        initTab(tabLayout4, " ");
    }

    private void initView() {
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText("标签栏");
        mToolBar = findViewById(R.id.toolbar);
        tabLayout1 = findViewById(R.id.tabLayout1);
        tabLayout2 = findViewById(R.id.tabLayout2);
        tabLayout3 = findViewById(R.id.tabLayout3);
        tabLayout4 = findViewById(R.id.tabLayout4);
        mToolBar.setNavigationIcon(getResources().getDrawable(R.mipmap.arrow_white));
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    TextView itemTv;
    ImageView itemIv;
    RelativeLayout rela;
    View view_stub;
    View select_view;

    private void initTab(TabLayout tabLayout1, String badgeStr){

        tabLayout1.setSelectedTabIndicatorHeight(0);
        tabLayout1.setTabMode(TabLayout.MODE_FIXED);
        for (int i = 0; i < tabIndicators.length; i++) {
            itemTab = tabLayout1.newTab();
            itemTab.setCustomView(R.layout.item_tab_layout_custom);
            itemTv = itemTab.getCustomView().findViewById(R.id.tv_menu_item);
            itemIv = itemTab.getCustomView().findViewById(R.id.iv_menu_item);
            view_stub = itemTab.getCustomView().findViewById(R.id.view_stub);
//            RelativeLayout rela = itemTab.getCustomView().findViewById(R.id.rela);
            if( i== 2 ){
                badgeView = new BadgeView(this, view_stub);
                badgeView.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
                badgeView.setBadgeMargin(10);
                badgeView.setText(badgeStr);
                badgeView.setTextSize(8);
                badgeView.show();
            }
            itemIv.setImageDrawable(getResources().getDrawable(tabDrawables[i]));
            itemTv.setText(tabIndicators[i]);
            tabLayout1.addTab(itemTab);
        }

        tabLayout1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                select_view =  tab.getCustomView();
                itemTv = select_view.findViewById(R.id.tv_menu_item);
                itemTv.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.btn_normal));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                select_view =  tab.getCustomView();
                itemTv = select_view.findViewById(R.id.tv_menu_item);
                itemTv.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.btn_selected));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        select_view =  tabLayout1.getTabAt(0).getCustomView();
        itemTv = select_view.findViewById(R.id.tv_menu_item);
        itemTv.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.btn_normal));
    }


}
