package com.yto.template.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.yto.template.R;
import com.yto.template.base.BaseActivity;


/**
 * Created by Chris on 2018/3/22.
 */

public class TimeLineActivity extends BaseActivity {
    private Toolbar mToolBar;
    private TextView tv_title;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_timeline;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText("时间轴");
        mToolBar = findViewById(R.id.toolbar);
        mToolBar.setNavigationIcon(getResources().getDrawable(R.mipmap.arrow_white));
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

//    private class MAsyncTask extends AsyncTask<Params, Progress, Result>{
//        @Override
//        protected Object doInBackground(Object[] objects) {
//            return null;
//        }
//    }
}