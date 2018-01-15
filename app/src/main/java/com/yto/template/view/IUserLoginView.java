package com.yto.template.view;

import android.content.Context;

import com.yto.template.module.BasicResponse;
import com.yto.template.module.bean.LoginBean;

/**
 * Created by Chris on 2017/12/19.
 */

public interface IUserLoginView {
    String getUserName();

    String getPassword();

    String getDeviceId();

    void clearUserName();

    void clearPassword();

    void showLoading();

    void hideLoading();

    void toMainActivity(BasicResponse<LoginBean> resp);

    void showFailedError();

    Context getContext();


}
