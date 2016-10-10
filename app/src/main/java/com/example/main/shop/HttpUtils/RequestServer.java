package com.example.main.shop.HttpUtils;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2016/10/8 0008.
 */

public interface RequestServer {
    //获取手机验证码
    @GET("get_code")
    Call<RequestBody> getCode(@QueryMap Map<String, String> param);
    @POST("register")
    Call<RequestBody>register();
}
