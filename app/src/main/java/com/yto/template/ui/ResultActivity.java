package com.yto.template.ui;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
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

public class ResultActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_result;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        title.setText("结果页");
    }
    @OnClick({R.id.tv_null_result,R.id.tv_not_result})
    void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.tv_null_result:
                intent.setClass(this, ResultFinallyActivity.class);
                intent.putExtra("from","nure");
                startActivity(intent);
                break;
            case R.id.tv_not_result:
                intent.setClass(this, ResultFinallyActivity.class);
                intent.putExtra("from","not");
                startActivity(intent);
                break;
        }
    }

}
