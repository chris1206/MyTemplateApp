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

public class ExceptionActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_exception;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        title.setText("异常页");
    }
    @OnClick({R.id.back,R.id.tv_net_exc,R.id.tv_not_net})
    void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.back:
                onBackPressed();
                break;
            case R.id.tv_net_exc:
                intent.setClass(this, ResultFinallyActivity.class);
                intent.putExtra("from","net_exc");
                startActivity(intent);
                break;
            case R.id.tv_not_net:
                intent.setClass(this, ResultFinallyActivity.class);
                intent.putExtra("from","not_net");
                startActivity(intent);
                break;
        }
    }
}
