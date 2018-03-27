package com.yto.template.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.yto.template.R;
import com.yto.template.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoadingActivity extends BaseActivity {

    @BindView(R.id.ll_progress)
    LinearLayout ll_progress;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_loading;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);
    }
    @OnClick({R.id.iv_pro_cancle})
    void onClick(View view){
        switch (view.getId()) {
            case R.id.iv_pro_cancle:
                ll_progress.setVisibility(View.INVISIBLE);
                break;
        }
    }
}
