package com.yto.template.module.biz;

import android.content.Context;

import com.google.gson.JsonObject;
import com.yto.template.LoginActivity;
import com.yto.template.api.DefaultObserver;
import com.yto.template.api.IdeaApi;
import com.yto.template.module.BasicResponse;
import com.yto.template.module.bean.LoginBean;
import com.yto.template.utils.ToastUtils;
import com.yto.template.utils.Utils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Chris on 2017/12/19.
 */

public class UserBiz implements IUserBiz {
    @Override
    public void login(final Context ctx, final String username, final String password,
                      final String device_id, final OnLoginListener loginListener) {

        JsonObject json = new JsonObject();
        json.addProperty("username",username);
        json.addProperty("password", Utils.getMD5(password));
        json.addProperty("device_id", device_id);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),json.toString());

        IdeaApi.getApiService()
                .getLoginRequest(username,Utils.getMD5(password),device_id)//form表单形式提交
//                .getLogin(requestBody)//封装成json字符串并在Body中提交
                .subscribeOn(Schedulers.io())
                //TODO:
                .compose(((LoginActivity)ctx).<BasicResponse<LoginBean>>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<BasicResponse<LoginBean>>(((LoginActivity)ctx)) {
                    @Override
                    public void onSuccess(BasicResponse<LoginBean> response) {
                        ToastUtils.show("恭喜你登录成功~");

                        loginListener.loginSuccess(response);
                        //TODO:存储用户登录信息

//                        LoginBean bean = response.getResults();
//
//                        System.out.println(bean.fullname);

                    }
                });

    }

}
