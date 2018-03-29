package com.yto.template.ui;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qbnet.viewutils.XEditText;
import com.yto.template.R;
import com.yto.template.base.BaseActivity;
import com.yto.template.utils.ToastUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class EditActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.xet_common)
    XEditText xet_common;
    @BindView(R.id.xet_common_two)
    XEditText xet_common_two;
    @BindView(R.id.xet_btn)
    XEditText xet_btn;
    @BindView(R.id.xet_scan)
    XEditText xet_scan;
    @BindView(R.id.et_more)
    EditText et_more;
    @BindView(R.id.tv_text_num)
    TextView tv_text_num;
    @BindView(R.id.cb_send)
    CheckBox cb_send;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        title.setText("输入框");
        xet_common.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().trim().length()>0){
                    xet_common.setCompoundDrawables(null,null,getDraw(R.mipmap.close),null);
                }else{
                    xet_common.setCompoundDrawables(null,null,null,null);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        xet_common.setDrawableRightListener(new XEditText.DrawableRightListener() {
            @Override
            public void onDrawableRightClick(View view) {
                xet_common.setText("");
            }
        });
        xet_common_two.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().trim().length()>0){
                    xet_common_two.setCompoundDrawables(null,null,getDraw(R.mipmap.close),null);
                }else{
                    xet_common_two.setCompoundDrawables(null,null,null,null);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        xet_common_two.setDrawableRightListener(new XEditText.DrawableRightListener() {
            @Override
            public void onDrawableRightClick(View view) {
                xet_common_two.setText("");
            }
        });

        xet_btn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().trim().length()>0){
                    xet_btn.setCompoundDrawables(null,null,getDraw(R.mipmap.close),null);
                }else{
                    xet_btn.setCompoundDrawables(null,null,null,null);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        xet_btn.setDrawableRightListener(new XEditText.DrawableRightListener() {
            @Override
            public void onDrawableRightClick(View view) {
                xet_btn.setText("");
            }
        });

        xet_scan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().trim().length()>0){
                    xet_scan.setCompoundDrawables(null,null,getDraw(R.mipmap.close),null);
                }else{
                    xet_scan.setCompoundDrawables(null,null,getDraw(R.mipmap.scan),null);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        xet_scan.setDrawableRightListener(new XEditText.DrawableRightListener() {
            @Override
            public void onDrawableRightClick(View view) {
                if(xet_scan.getText().toString().trim().length()==0){
                    ToastUtils.show("你要扫描吗？" );
                }else{
                    xet_scan.setText("");
                }
            }
        });
        et_more.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tv_text_num.setText(count+"/120");
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


    public Drawable getDraw(int res){
        Drawable drawable = getResources().getDrawable(res);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        return drawable;
    }

    @OnClick({R.id.back,R.id.cb_send})
    void onClick(View view){
        switch (view.getId()){
            case R.id.back:
                onBackPressed();
                break;
            case R.id.cb_send:
                cb_send.setClickable(false);
                break;
        }

    }

}
