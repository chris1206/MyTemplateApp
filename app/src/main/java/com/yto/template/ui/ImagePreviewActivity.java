package com.yto.template.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yto.template.R;
import com.yto.template.base.BaseActivity;

/**
 * Created by Chris on 2018/3/26.
 */

public class ImagePreviewActivity extends BaseActivity {
    private TextView title;
    private ImageView back;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_image_preview;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        back = findViewById(R.id.back);
        title = findViewById(R.id.title);
        title.setText("图片预览");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    public void normalPreview(View v) {
        startActivity(new Intent(this, NormalPreviewActivity.class));
    }

    public void infoPreview(View v) {
        startActivity(new Intent(this, InfoPreviewActivity.class));
    }
}