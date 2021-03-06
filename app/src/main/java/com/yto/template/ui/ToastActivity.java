package com.yto.template.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yto.template.R;
import com.yto.template.base.BaseActivity;
import com.yto.template.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ToastActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView title;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_toast;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        title.setText("吐司提示");
    }

    @OnClick({R.id.back,R.id.tv_normal_toast,R.id.tv_custom_toast})
    void onClick(View view){
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.tv_normal_toast:
                ToastUtils.show("提示内容最多15个字，不能再多了");
                break;
            case R.id.tv_custom_toast:
                ToastUtils.showCustom("注册成功",Toast.LENGTH_SHORT);
                break;
        }
    }
}
