package com.yto.template.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.yto.template.utils.ACache;
import com.yto.template.utils.ToastUtils;

public abstract class BaseActivity extends RxAppCompatActivity {

    protected ACache mCache;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mCache = ACache.get(getApplicationContext());
        init(savedInstanceState);
    }
    protected void showToast(String msg) {
        ToastUtils.show(msg);
    }

    protected abstract @LayoutRes
    int getLayoutId();

    protected abstract void init(Bundle savedInstanceState);
}
