package com.yto.template.utils;

import android.content.Context;

import com.yto.template.R;
import com.yto.template.customview.CustomProgressDialog;

/**
 * Created by Chris on 2017/11/30.
 */

public class CommonDialogUtils {

    //  加载进度的dialog
    private CustomProgressDialog mProgressDialog;

    /**
     * 显示ProgressDialog
     */
    public void showProgress(Context context, String msg) {
       /* if (context == null || context.isFinishing()) {
            return;
        }*/
        if(mProgressDialog==null){
            mProgressDialog= new CustomProgressDialog.Builder(context)
                    .setTheme(R.style.ProgressDialogStyle)
                    .setMessage(msg)
                    .build();
        }
        if(mProgressDialog!=null&&!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    /**
     * 显示ProgressDialog
     */
    public void showProgress(Context context) {
        /*if (activity == null || activity.isFinishing()) {
            return;
        }*/
        if(mProgressDialog==null){
            mProgressDialog= new CustomProgressDialog.Builder(context)
                    .setTheme(R.style.ProgressDialogStyle)
                    .build();
        }
        if(mProgressDialog!=null&&!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    /**
     * 取消ProgressDialog
     */
    public void dismissProgress() {
        if (mProgressDialog != null&&mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

}
