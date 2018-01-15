package com.yto.template.module.bean;

/**
 * Created by Chris on 16/7/4.
 */
public class LoginBean {

    public String fullname;//姓名
    public String jpush_alias;//别名，单点登录用到
    public String mobile;
    public String photo_url;
    public String token;
    public String old_driver;//是否是老司机0：不是 1：是

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getJpush_alias() {
        return jpush_alias;
    }

    public void setJpush_alias(String jpush_alias) {
        this.jpush_alias = jpush_alias;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOld_driver() {
        return old_driver;
    }

    public void setOld_driver(String old_driver) {
        this.old_driver = old_driver;
    }


}
