package com.yto.template.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yto.template.R;
import com.yto.template.base.BaseActivity;

/**
 * Created by Chris on 2018/1/31.
 * 自定义标签栏界面
 */

public class ListActivity extends BaseActivity {
    private TextView title;
    private ImageView back;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_list;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        back = findViewById(R.id.back);
        title = findViewById(R.id.title);
        title.setText("列表");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}
