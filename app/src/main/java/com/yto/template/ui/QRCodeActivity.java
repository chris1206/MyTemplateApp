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
 * Created by Chris on 2018/3/23.
 */

public class QRCodeActivity extends BaseActivity {
    private TextView title;
    private ImageView back;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_qrcode;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        back = findViewById(R.id.back);
        title = findViewById(R.id.title);
        title.setText("条形码");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void previewQRCode(View v) {
        Intent i = new Intent(QRCodeActivity.this, FullScreenBarView.class);
        i.putExtra("cq_number", "C Q 0 0 0 1 2 0 0 9 8 1");
        startActivity(i);
    }
}