package com.yto.template.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yto.template.R;
import com.yto.template.base.BaseActivity;
import com.yto.template.customview.BadgeView;

/**
 * Created by Chris on 2018/1/31.
 * 按钮界面
 */

public class IconActivity extends BaseActivity {

    private TextView title,view_stub1,view_stub2,view_stub3;
    private ImageView back;
    private BadgeView badgeView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_icon;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        back = findViewById(R.id.back);
        title = findViewById(R.id.title);
        view_stub1 = findViewById(R.id.view_stub1);
        view_stub2 = findViewById(R.id.view_stub2);
        view_stub3 = findViewById(R.id.view_stub3);

        badgeView = new BadgeView(this, view_stub1);
        badgeView.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
        badgeView.setBadgeMargin(10);
        badgeView.setText("1");
        badgeView.setTextSize(8);
        badgeView.show();

        badgeView = new BadgeView(this, view_stub2);
        badgeView.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
        badgeView.setBadgeMargin(10);
        badgeView.setText("12");
        badgeView.setTextSize(8);
        badgeView.show();

        badgeView = new BadgeView(this, view_stub3);
        badgeView.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
        badgeView.setBadgeMargin(10);
        badgeView.setText("···");
        badgeView.setTextSize(8);
        badgeView.show();

        title.setText("图标");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}
