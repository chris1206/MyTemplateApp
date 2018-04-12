package com.yto.template.ui;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yto.template.R;
import com.yto.template.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowLoadingActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.ll_other_loading)
    LinearLayout ll_other_loading;
    @BindView(R.id.tv_loading_title)
    TextView tv_loading_title;
    @BindView(R.id.tv_loading_present)
    TextView tv_loading_present;
    @BindView(R.id.ll_loading)
    LinearLayout ll_loading;
    @BindView(R.id.tv_loading_msg)
    TextView tv_loading_msg;
    @BindView(R.id.iv_pro_cancle)
    ImageView iv_pro_cancle;

    String from;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_show_loading;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        if(getIntent()!=null){
            from = getIntent().getStringExtra("from");
        }
        initView();
    }

    private void initView() {
        if(from.equals("normal")){
            title.setText("常规加载");
            ll_other_loading.setVisibility(View.VISIBLE);
        } else if(from.equals("cancle")){
            title.setText("带结束加载");
            iv_pro_cancle.setVisibility(View.VISIBLE);
            tv_loading_present.setText("②带结束加载");
        } else if(from.equals("up")){
            title.setText("局部上传加载");
            tv_loading_title.setText("局部加载");
            tv_loading_present.setText("①局部上传加载");
            ll_loading.setBackgroundColor(Color.parseColor("#00000000"));
            tv_loading_msg.setText("正在上传");
        } else if(from.equals("more")){
            title.setText("局部上传加载");
            tv_loading_title.setText("局部加载");
            tv_loading_present.setText("①上拉加载更多");
            ll_loading.setBackgroundColor(Color.parseColor("#00000000"));
            tv_loading_msg.setText("加载中");
        }
    }

    @OnClick({R.id.back,R.id.iv_pro_cancle})
    void onClick(View view){
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.iv_pro_cancle:
                ll_loading.setVisibility(View.GONE);
                break;
        }
    }
}
