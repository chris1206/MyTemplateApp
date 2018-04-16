package com.yto.template.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yto.template.R;
import com.yto.template.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScanEntryActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_scan_entry;

    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        title.setText("扫描");
    }

    @OnClick({R.id.back,R.id.tv_scan_entry})
    void onClick(View view){
        switch (view.getId()){
            case R.id.back:
                onBackPressed();
                break;
            case R.id.tv_scan_entry:
                startActivity(new Intent(this, ScanActivity.class));
                break;
        }
    }

}
