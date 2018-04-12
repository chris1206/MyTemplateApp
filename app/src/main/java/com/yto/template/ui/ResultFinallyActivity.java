package com.yto.template.ui;

import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yto.template.R;
import com.yto.template.base.BaseActivity;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResultFinallyActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.head_present)
    LinearLayout head_present;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout srl_refresh;
    @BindView(R.id.rl_reslt)
    RelativeLayout rl_reslt;
    @BindView(R.id.tv_result_msg)
    TextView tv_result_msg;
    @BindView(R.id.iv_result_img)
    ImageView iv_result_img;
    @BindView(R.id.tv_result_take)
    TextView tv_result_take;

    MyHanler hanler;
    String from;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_result_finally;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        from = getIntent().getStringExtra("from");
        if(from.equals("not")){
            title.setText("搜索无结果");
        }else if(from.equals("nure")){
            title.setText("内容为空");
        }else if(from.equals("net_exc")){
            title.setText("网络异常");
        }else if(from.equals("not_net")){
            title.setText("无网络");
        }else if(from.equals("jubu")){
            title.setText("局部刷新");
            head_present.setVisibility(View.VISIBLE);
        } else if(from.equals("click_refresh")){
            title.setText("点击刷新");
        } else if(from.equals("all_refresh")){
            title.setText("刷新全局");
        }
        hanler = new MyHanler(this);
        srl_refresh.setRefreshing(true);
        getData();
        srl_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                rl_reslt.setVisibility(View.INVISIBLE);
                getData();
            }
        });

    }

    public void getData(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    if(from.equals("not")){
                        hanler.sendEmptyMessage(0);
                    }else if(from.equals("nure")){
                        hanler.sendEmptyMessage(1);
                    }else if(from.equals("net_exc")){
                        hanler.sendEmptyMessage(2);
                    }else if(from.equals("not_net")){
                        hanler.sendEmptyMessage(3);
                    }else if(from.equals("jubu")){
                        hanler.sendEmptyMessage(4);
                    } else if(from.equals("click_refresh")){
                        hanler.sendEmptyMessage(5);
                    }else if(from.equals("all_refresh")){
                        hanler.sendEmptyMessage(6);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        executorService.execute(runnable);
    }

    @OnClick({R.id.back,R.id.rl_reslt})
    void onClick(View view){
        switch (view.getId()){
            case R.id.back:
                onBackPressed();
                break;
            case R.id.rl_reslt:
                rl_reslt.setVisibility(View.INVISIBLE);
                srl_refresh.setRefreshing(true);
                getData();
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hanler.removeCallbacksAndMessages(null);
    }

    static class MyHanler extends Handler{
        WeakReference<ResultFinallyActivity> mWeakActivity;
        //强调弱引用
        public MyHanler(ResultFinallyActivity activity) {
            mWeakActivity = new WeakReference<ResultFinallyActivity>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ResultFinallyActivity activity = mWeakActivity.get();
            if(activity!=null){
                switch (msg.what){
                    case 0:
                        activity.rl_reslt.setVisibility(View.VISIBLE);
                        activity.srl_refresh.setRefreshing(false);
                        break;
                    case 1:
                    case 6:
                        activity.rl_reslt.setVisibility(View.VISIBLE);
                        activity.tv_result_msg.setText("还没有相关信息哟");
                        activity.iv_result_img.setImageResource(R.mipmap.messager);
                        activity.srl_refresh.setRefreshing(false);
                        break;
                    case 2:
                    case 5:
                        activity.tv_result_take.setVisibility(View.VISIBLE);
                        activity.rl_reslt.setVisibility(View.VISIBLE);
                        activity.tv_result_msg.setText("网络连接失败");
                        activity.iv_result_img.setImageResource(R.mipmap.net_except);
                        activity.srl_refresh.setRefreshing(false);
                        break;
                    case 3:
                        activity.tv_result_take.setVisibility(View.VISIBLE);
                        activity.rl_reslt.setVisibility(View.VISIBLE);
                        activity.tv_result_msg.setText("当前无网络");
                        activity.iv_result_img.setImageResource(R.mipmap.not_net);
                        activity.tv_result_take.setText("请检查网络设置");
                        activity.srl_refresh.setRefreshing(false);
                        break;
                    case 4:
                        activity.rl_reslt.setVisibility(View.VISIBLE);
                        activity.tv_result_msg.setText("还没有相关信息哟");
                        activity.iv_result_img.setImageResource(R.mipmap.messager);
                        activity.srl_refresh.setRefreshing(false);
                        break;

                }
            }

        }
    }
}
