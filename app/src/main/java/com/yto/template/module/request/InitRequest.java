package com.yto.template.module.request;

import com.yto.template.base.BaseRequest;

/**
 * Created by Chris on 2017/11/30.
 */

public class InitRequest extends BaseRequest {
    private String device_type;
    private String app_ver;
    private String username;
//    private Context ctx;
//
//    public InitRequest(Context context) {
//        ctx = context;
//    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getApp_ver() {
        return app_ver;
    }

    public void setApp_ver(String app_ver) {
        this.app_ver = app_ver;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
