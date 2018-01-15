package com.yto.template.module.biz;

import android.content.Context;

/**
 * Created by Chris on 2017/12/19.
 */

public interface IUserBiz {
    void login(Context ctx, String username, String password, String device_id, OnLoginListener loginListener);
}
