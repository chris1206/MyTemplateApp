package com.yto.template.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yto.template.R;
import com.yto.template.base.BaseActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Chris on 2018/3/15.
 *                  Camera API     Preview View
 * 4.0-4.4  14-20    Camera1        TextureView
 * >=5.0    >=21     Camera2        TextureView
 * 兼容方案：项目一般保证兼容4.0以上版本，所以需要适配两套相机拍摄方案
 */

public class PhotoActivity extends BaseActivity{
    private static final String TAG = PhotoActivity.class.getSimpleName();

    private Toolbar mToolBar;
    private TextView tv_title;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_photo;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        tv_title = findViewById(R.id.tv_title);
        tv_title.setText("拍照");
        mToolBar = findViewById(R.id.toolbar);
        mToolBar.setNavigationIcon(getResources().getDrawable(R.mipmap.arrow_white));
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    public void singleCapture(View view) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //  大于等于23即为6.0及以上执行内容
            RxPermissions rxPermissions = new RxPermissions(this);
            rxPermissions
                    .request(Manifest.permission.CAMERA)
                    .subscribe(granted -> {
                        if (granted) { // Always true pre-M
                            startActivity(new Intent(this, CameraActivity.class));
                            // I can control the camera now
                        } else {
                            // Oups permission denied
                            Toast.makeText(this, "请开启摄像头权限", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            //  低于23即为6.0以下执行内容
            startActivity(new Intent(this, CameraActivity.class));
        }

//        Toast.makeText(this, "单拍", Toast.LENGTH_SHORT).show();

    }

    public void multiCapture(View view){
        startActivity(new Intent(this, Camera2Activity.class));
//        Toast.makeText(this, "连拍", Toast.LENGTH_SHORT).show();
    }



}
