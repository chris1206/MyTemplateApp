package com.yto.template.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yto.template.R;
import com.yto.template.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoadingActivity extends BaseActivity {


    @BindView(R.id.title)
    TextView title;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_loading;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        title.setText("加载");
    }
    @OnClick({R.id.back,R.id.tv_loading_normal,R.id.tv_loading_cancle,R.id.tv_loading_up,R.id.tv_loading_more})
    void onClick(View view){
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.tv_loading_normal:
                startActivity(new Intent(this,ShowLoadingActivity.class).putExtra("from","normal"));
                break;
            case R.id.tv_loading_cancle:
                startActivity(new Intent(this,ShowLoadingActivity.class).putExtra("from","cancle"));
                break;
            case R.id.tv_loading_up:
                startActivity(new Intent(this,ShowLoadingActivity.class).putExtra("from","up"));
                break;
            case R.id.tv_loading_more:
                startActivity(new Intent(this,ShowLoadingActivity.class).putExtra("from","more"));
                break;
        }
    }
}
