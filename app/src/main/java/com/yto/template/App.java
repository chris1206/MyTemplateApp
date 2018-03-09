package com.yto.template;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.facebook.stetho.Stetho;
import com.yto.template.utils.Utils;

/**
 * Created by Chris on 2017/11/30.
 */

public class App extends MultiDexApplication {
    private static App app;
    public static Context getAppContext() {
        return app;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        app=this;
        Stetho.initializeWithDefaults(this);
    }
}
