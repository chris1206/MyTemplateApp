package com.yto.template;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yto.template.api.DefaultObserver;
import com.yto.template.api.IdeaApi;
import com.yto.template.base.BaseActivity;
import com.yto.template.module.BasicResponse;
import com.yto.template.module.bean.InitBean;
import com.yto.template.module.bean.LoginBean;
import com.yto.template.module.bean.UniqueKey;
import com.yto.template.presenter.UserLoginPresenter;
import com.yto.template.test.WaveActivity;
import com.yto.template.test.touch_event.MyTextView;
import com.yto.template.ui.MainActivity;
import com.yto.template.utils.ToastUtils;
import com.yto.template.utils.Utils;
import com.yto.template.view.IUserLoginView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends BaseActivity
        implements IUserLoginView, NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener, View.OnTouchListener{
    public static final String TAG = "LoginActivity";
    private Button btn_login,btn_clear;
    private EditText et_username,et_password;
    private MyTextView tv;

    private UserLoginPresenter mUserLoginPresenter = new UserLoginPresenter(this);

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        initView();

        initParams();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserLoginPresenter.login();
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserLoginPresenter.clear();
            }
        });

        tv.setOnClickListener(this);
        tv.setOnTouchListener(this);

    }

    /**
     * retrofit+okhttp+rxjava2方式获取初始化参数
     * 暂未使用mvp方式
     */

    private void initParams() {
//        InitRequest initRequest = new InitRequest();
//        initRequest.setDevice_type("1");
//        initRequest.setApp_ver("0.6.10");
//        initRequest.setUsername("");
        IdeaApi.getApiService()
//                .getInitRequest(initRequest)
                .getInitRequest("","1","0.6.11")
                .subscribeOn(Schedulers.io())
                .compose(this.<BasicResponse<InitBean>>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<BasicResponse<InitBean>>(this) {
                    @Override
                    public void onSuccess(BasicResponse<InitBean> response) {
                        ToastUtils.show("初始化成功~");

                        InitBean bean = response.getResults();

                        mCache.put(UniqueKey.PHOTO_SIZE.toString(), bean.getCompression_ratio());
                        mCache.put(UniqueKey.MSG_COUNT.toString(), bean.getPage_size());//一页加载消息最大值
                        mCache.put(UniqueKey.FENCE_STATUS_LOCK.toString(), bean.getFence_status_lock());
                        mCache.put(UniqueKey.GATHER_INTERVAL.toString(), bean.getCollect_interval());
                        mCache.put(UniqueKey.PACK_INTERVAL.toString(), bean.getPack_interval());
                        mCache.put(UniqueKey.SPEED_GATHER_INTERVAL.toString(), bean.getSpeed_collect_interval());
                        mCache.put(UniqueKey.ABNORMAL_OFF_SPEED_GATHER_NUMBER.toString(), bean.getReport_off_speed_gather_number());
                        mCache.put(UniqueKey.AVERAGE_SPEED.toString(), bean.getAverage_speed());
                        mCache.put(UniqueKey.DRAFT_CHECK_CYCLE.toString(), bean.getDraft_check_interval());
                        mCache.put(UniqueKey.MESSAGE_EXPIRED_TIME.toString(), bean.getMsg_expired_time());
                        mCache.put(UniqueKey.COMMENTS_URL.toString(), bean.getComments_url());
                        mCache.put(UniqueKey.QQ_GROUP_NUM.toString(), bean.getQq_group_num());
                        mCache.put(UniqueKey.QQ_GROUP_ANDROID_KEY.toString(), bean.getQq_group_android_key());
                        mCache.put(UniqueKey.REPORT_SPEED_2000.toString(), bean.getReport_speed_2000());
                        mCache.put(UniqueKey.REPORT_SPEED_2100.toString(), bean.getReport_speed_2100());
                        mCache.put(UniqueKey.REPORT_SPEED_2800.toString(), bean.getReport_speed_2800());

                    }
                });
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tv = findViewById(R.id.tv);
        btn_login = findViewById(R.id.btn_login);
        btn_clear = findViewById(R.id.btn_clear);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public String getUserName() {
        return et_username.getText().toString();
    }

    @Override
    public String getPassword() {
        return et_password.getText().toString();
    }

    @Override
    public String getDeviceId() {
        return Utils.getDeviceId(this);
    }

    @Override
    public void clearUserName() {
        et_username.setText("");
    }

    @Override
    public void clearPassword() {
        et_password.setText("");
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void toMainActivity(BasicResponse<LoginBean> resp) {

        startActivity(new Intent(this, WaveActivity.class));

//        ToastUtils.show("可以登录了。。");
    }

    @Override
    public void showFailedError() {

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv:
                startActivity(new Intent(this, MainActivity.class));
                Log.e(TAG, "MyTextView Onclick");
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.tv:
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e(TAG, "mytextview onTouch ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.e(TAG, "mytextview onTouch ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e(TAG, "mytextview onTouch ACTION_UP");
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        Log.e(TAG, "mytextview onTouch ACTION_CANCEL");
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        return false;//返回false不拦截事件，事件向内层继续传
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "onTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "onTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "onTouchEvent ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e(TAG, "onTouchEvent ACTION_CANCEL");
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "dispatchTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "dispatchTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "dispatchTouchEvent ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e(TAG, "dispatchTouchEvent ACTION_CANCEL");
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
//        return false;
    }

}
