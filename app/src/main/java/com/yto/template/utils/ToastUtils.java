package com.yto.template.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yto.template.App;
import com.yto.template.R;

/**
 * Created by Chris on 2017/11/30.
 */

public class ToastUtils {
    private static Context context = App.getAppContext();
    private static Toast toast;
    private static Toast sutToast;

    public static void show(int resId) {
        show(context.getResources().getText(resId), Toast.LENGTH_SHORT);
    }

    public static void show(int resId, int duration) {
        show(context.getResources().getText(resId), duration);
    }

    public static void show(CharSequence text) {
        show(text, Toast.LENGTH_SHORT);
    }

    /*public static void showDebug(CharSequence text) {
        if (BuildConfig.DEBUG) {
            show(text, Toast.LENGTH_SHORT);
        }
    }*/

    public static void show(CharSequence text, int duration) {
        text = TextUtils.isEmpty(text == null ? "" : text.toString()) ? "请检查您的网络！"
                : text;
        if (toast == null) {
            toast = Toast.makeText(context, text, duration);
        } else {
            toast.setText(text);
        }
        toast.show();
    }

    public static void show(int resId, Object... args) {
        show(String.format(context.getResources().getString(resId), args),
                Toast.LENGTH_SHORT);
    }

    public static void show(String format, Object... args) {
        show(String.format(format, args), Toast.LENGTH_SHORT);
    }

    public static void show(int resId, int duration, Object... args) {
        show(String.format(context.getResources().getString(resId), args),
                duration);
    }

    public static void show(String format, int duration, Object... args) {
        show(String.format(format, args), duration);
    }

    public static void showCustom(CharSequence text,int duration){
        text = TextUtils.isEmpty(text)?"注册成功":text;
        TextView textView = null;
        if(sutToast == null){
            sutToast = Toast.makeText(context, text, duration);
            View v = View.inflate(context, R.layout.toast_layout,null);
            textView = (TextView) v.findViewById(R.id.tv_message);
            ImageView iv_toast = (ImageView) v.findViewById(R.id.iv_toast);
            textView.setText(text);
            sutToast.setView(v);
        }else{
            if(textView!=null){
                textView.setText(text);
            }
        }
        sutToast.show();
    }
}
