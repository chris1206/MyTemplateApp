package com.yto.template.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yto.template.R;
import com.yto.template.base.BaseActivity;

/**
 * Created by Chris on 2018/3/21.
 */

public class CardActivity extends BaseActivity{
    private Toolbar mToolBar;
    private TextView title;
    private ImageView back;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_card;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        back = findViewById(R.id.back);
        title = findViewById(R.id.title);
        title.setText("卡片");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
