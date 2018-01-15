package com.yto.template.presenter;

import android.os.Handler;

import com.yto.template.module.BasicResponse;
import com.yto.template.module.bean.LoginBean;
import com.yto.template.module.biz.IUserBiz;
import com.yto.template.module.biz.OnLoginListener;
import com.yto.template.module.biz.UserBiz;
import com.yto.template.view.IUserLoginView;

/**
 * Created by Chris on 2017/12/19.
 */

public class UserLoginPresenter {

    private IUserBiz userBiz;
    private IUserLoginView userLoginView;
    private Handler mHandler = new Handler();

    public UserLoginPresenter(IUserLoginView userLoginView) {
        this.userLoginView = userLoginView;
        this.userBiz = new UserBiz();
    }

    public void login() {
        userBiz.login(userLoginView.getContext(),userLoginView.getUserName(), userLoginView.getPassword(),
                userLoginView.getDeviceId(), new OnLoginListener() {
            @Override
            public void loginSuccess(BasicResponse<LoginBean> resp) {
                userLoginView.toMainActivity(resp);
            }

            @Override
            public void loginFailed() {

            }
        });
    }

    public void clear(){
        userLoginView.clearUserName();
        userLoginView.clearPassword();
    }
}
