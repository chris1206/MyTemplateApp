package com.yto.template.api;

import com.yto.template.module.BasicResponse;
import com.yto.template.module.bean.InitBean;
import com.yto.template.module.bean.LoginBean;
import com.yto.template.module.bean.MeiZi;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Chris on 2017/11/30.
 */

public interface IdeaApiService {
    /**
     * 网络请求超时时间毫秒
     */
    int DEFAULT_TIMEOUT = 15000;

    String HOST = "http://gank.io/";
    String API_SERVER_URL = HOST + "api/data/";

    String APP_SERVER_ADDR = "https://preview.yonmen.com:87/";

    String LOCAL_HOST = "http://192.168.3.81:10000/";
    /**
     * 此接口服务器响应数据BasicResponse的泛型T应该是List<MeiZi>
     * 即BasicResponse<List<MeiZi>>
     * @return BasicResponse<List<MeiZi>>
     */
    @Headers("Cache-Control: public, max-age=86400") //  设置缓存
    @GET("福利/10/1")
    Observable<BasicResponse<List<MeiZi>>> getMezi();

    /**
     * 登录 接口为假接口 并不能返回数据
     * @return
     */
//    @POST("login.do")
//    Observable<BasicResponse<LoginResponse>> login(@Body LoginRequest request);

    /**
     * 刷新token 接口为假接口 并不能返回数据
     * @return
     */
//    @POST("refresh_token.do")
//    Observable<BasicResponse<RefreshTokenResponseBean>> refreshToken(@Body RefreshTokenRequest request);

//    @Headers("Cache-Control: public, max-age=86400")//86400秒：24小时
//    @GET("v2/params")
//    Observable<BasicResponse<InitBean>> getInitRequest(@Body InitRequest request);

    @GET("v2/params")
    Observable<BasicResponse<InitBean>> getInitRequest(@Query("username") String username, @Query("device_type") String device_type,
                                              @Query("app_ver") String app_ver);

    @FormUrlEncoded
    @POST("v2/auth/app")
    Observable<BasicResponse<LoginBean>> getLoginRequest(@Field("username") String username, @Field("password") String password,
                                                         @Field("device_id") String device_id);

    @POST("v2/auth/app")
    Observable<BasicResponse<LoginBean>> getLogin(@Body RequestBody requestBody);

}
