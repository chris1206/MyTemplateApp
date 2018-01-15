package com.yto.template.test;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yto.template.R;
import com.yto.template.base.BaseActivity;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by Chris on 2017/12/7.
 */

@RuntimePermissions
public class PermissionDispatcherActivity extends BaseActivity {
    Button callPhone;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        callPhone = (Button) findViewById(R.id.btn_login);
        callPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Activity名字+PermissionsDispatcher.
                //需要调用那个方法就xxxWithCheack()
//                PermissionDispatcherActivityPermissionsDispatcher.showToastWithCheck(PermissionDispatcherActivity.this);
                PermissionDispatcherActivityPermissionsDispatcher.testTwoPermisitionWithCheck(PermissionDispatcherActivity.this);
            }
        });
    }

    //这里主要讲单个权限使用permissitiondispatcher的方法
    //注意：被注解的方法不能是私有方法。
//    @NeedsPermission(Manifest.permission.CALL_PHONE)//在需要获取权限的方法上注解(如打开照相机，打电话，这个动作)
//    void showToast() {
//
//        Toast.makeText(this, "获取存储卡权限", Toast.LENGTH_SHORT).show();
//
//    }
//    @OnShowRationale(Manifest.permission.CALL_PHONE)//用户拒绝了一次之后弹出来，解释为了要弹这一次的对话框，用于解释为什么需要这个权限
//    void showWhy(final PermissionRequest request) {
//        new AlertDialog.Builder(this)
//                .setMessage("必要权限请通过")
//                .setPositiveButton("知道了", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        request.proceed();//再次执行请求
//                    }
//                })
//                .show();
//    }
//    @OnPermissionDenied(Manifest.permission.CALL_PHONE)//用户拒绝后回调，同意后就执行这里的showToast
//    void denied() {
//        Toast.makeText(this, "真的不给权限吗", Toast.LENGTH_SHORT).show();
//    }
//    @OnNeverAskAgain(Manifest.permission.CALL_PHONE)//用户选择不再询问后回调
//    void notAsk() {
//        Toast.makeText(this, "好的不问了", Toast.LENGTH_SHORT).show();
//    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionDispatcherActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    //----------------------------------------同时请求多个权限-----------------------------------------------------------------------------------
    //OnPermissionDenied注解里有什么参数，那么其他的注解也必须有这些参数,少一个都不能回调成功---重要
    //多个权限同时请求时,用户只要拒绝了一个就会回调@OnPermissionDenied注解的方法---重要
    //无法回调单个的请求失败的回调方法---重要
    //第一次拒绝联系人-再同意打电话,第二次同意联系人,拒绝拍照。仍可以回调请求权限成功的方法(实际上第一次同意打电话是生效了的。但只要有一个不同意就回调请求权限失败的方法)---重要
    @NeedsPermission({Manifest.permission.READ_CONTACTS, Manifest.permission.CALL_PHONE})
    void testTwoPermisition() {
        Toast.makeText(this, "同时获得多个权限成功", Toast.LENGTH_SHORT).show();
    }


    @OnPermissionDenied({Manifest.permission.READ_CONTACTS, Manifest.permission.CALL_PHONE})
//用户拒绝后回调，同意后就执行这里的testTwoPermisition
    void testTwodenied1() {
        Toast.makeText(this, "刚刚拒绝了2个权限", Toast.LENGTH_SHORT).show();
    }

    //这个方法不会被回调
    //拒绝联系人同意打电话时，不回掉这个方法，也不回调请求权限成功的方法。-只回调2个权限同时请求失败的方法
    @OnPermissionDenied(Manifest.permission.READ_CONTACTS)
//用户拒绝后回调，testTwoPermisition
    void testTwodenied2() {
        Toast.makeText(this, "刚刚拒绝了联系人的权限", Toast.LENGTH_SHORT).show();
    }

    //这个方法不会被回调
    @OnPermissionDenied(Manifest.permission.CALL_PHONE)
//用户拒绝后回调，testTwoPermisition
    void testTwodenied3() {
        Toast.makeText(this, "刚刚拒绝了打电话的权限", Toast.LENGTH_SHORT).show();
    }

    @OnShowRationale(Manifest.permission.CALL_PHONE)
//用户拒绝了一次之后弹出来，解释为了要弹这一次的对话框，用于解释为什么需要这个权限
    void showWhy(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setMessage("必要权限请通过")
                .setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();//再次执行请求
                    }
                })
                .show();
    }

    @OnShowRationale({Manifest.permission.READ_CONTACTS, Manifest.permission.CALL_PHONE})
//用户拒绝了一次之后弹出来，解释为了要弹这一次的对话框，用于解释为什么需要这个权限
    void showWhy2(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setMessage("必要权限请通过")
                .setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();//再次执行权限请求
                    }
                })
                .show();
    }
}
