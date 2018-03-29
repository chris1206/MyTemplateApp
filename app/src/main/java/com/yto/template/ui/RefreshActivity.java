package com.yto.template.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yto.template.R;
import com.yto.template.base.BaseActivity;
import com.yto.template.customview.ResultFinallyActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RefreshActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_refresh;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        title.setText("刷新");
    }

    @OnClick({R.id.back,R.id.tv_refresh_all,R.id.tv_custom_refresh,R.id.tv_refresh_click})
    void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.tv_refresh_all:
                intent.setClass(this, ResultFinallyActivity.class);
                intent.putExtra("from","nure");
                startActivity(intent);
                break;
            case R.id.tv_custom_refresh:
                break;
            case R.id.tv_refresh_click:
                intent.setClass(this, ResultFinallyActivity.class);
                intent.putExtra("from","net_exc");
                startActivity(intent);
                break;
        }
    }

}
