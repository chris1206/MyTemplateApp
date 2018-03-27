package com.yto.template.ui;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yto.template.R;
import com.yto.template.base.BaseActivity;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoticeBarActivity extends BaseActivity {

    MyHandle handle;
    @BindView(R.id.tv_common_notify)
    TextView tv_common_notify;
    @BindView(R.id.ll_has_cancle_notify)
    LinearLayout ll_has_cancle_notify;
    @BindView(R.id.iv_cancle)
    ImageView iv_cancle;





    @Override
    protected int getLayoutId() {
        return R.layout.activity_norice_bar;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        handle = new MyHandle();
        cancleNotif();
    }

    @OnClick({R.id.iv_cancle,R.id.tv_notice,R.id.tv_other_take})
    void onClick(View view){
        switch (view.getId()) {
            case R.id.iv_cancle:
                ll_has_cancle_notify.setVisibility(View.INVISIBLE);
                break;
            case R.id.tv_other_take:
                showToast("做操作");
                break;
            case R.id.tv_notice:
                showToast("跳转");
                break;
        }
    }

    public void cancleNotif(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handle.sendEmptyMessage(1);
            }
        });
        thread.start();
    }
    class MyHandle extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    tv_common_notify.setVisibility(View.INVISIBLE);
                    break;
            }
        }
    }
}
