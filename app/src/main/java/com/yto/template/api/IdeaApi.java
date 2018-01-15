package com.yto.template.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.yto.template.utils.LogUtils;
import com.yto.template.utils.NetworkUtils;
import com.yto.template.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Chris on 2017/11/30.
 */

public class IdeaApi {
    private IdeaApiService service;

    private IdeaApi() {
        // HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        //   日志拦截器
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor((message)-> {

            try {
                String text = URLDecoder.decode(message, "utf-8");
                LogUtils.e("OKHttp--d---", text);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                LogUtils.e("OKHttp--e---", message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        File cacheFile = new File(Utils.getContext().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(IdeaApiService.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(IdeaApiService.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(interceptor)
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        Request request = chain.request()
//                                .newBuilder()
//                                .addHeader("Content-Type", "application/json;charset=UTF-8")
//                                .build();
//                        return chain.proceed(request);
//                    }
//                })
                .addNetworkInterceptor(new HttpCacheInterceptor())
                .cache(cache)
                .build();

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .baseUrl(IdeaApiService.LOCAL_HOST)
                .baseUrl(IdeaApiService.APP_SERVER_ADDR)
//                .baseUrl(IdeaApiService.API_SERVER_URL)
                .build();
        service = retrofit.create(IdeaApiService.class);
    }

    //  创建单例
    private static class SingletonHolder {
        private static final IdeaApi INSTANCE = new IdeaApi();
    }

    public static IdeaApiService getApiService() {
        return SingletonHolder.INSTANCE.service;
    }

    class HttpCacheInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetworkUtils.isConnected()) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
                LogUtils.d("Okhttp", "no network");
            }

            Response originalResponse = chain.proceed(request);
            if (NetworkUtils.isConnected()) {
                //有网的时候读接口上的@Headers里的配置，可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
                        .removeHeader("Pragma")
                        .build();
            }
        }
    }
}
