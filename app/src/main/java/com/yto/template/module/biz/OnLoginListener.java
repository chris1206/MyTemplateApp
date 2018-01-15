package com.yto.template.module.biz;

import com.yto.template.module.BasicResponse;
import com.yto.template.module.bean.LoginBean;

/**
 * Created by Chris on 2017/12/19.
 */

public interface OnLoginListener {
    void loginSuccess(BasicResponse<LoginBean> resp);
    void loginFailed();
}
