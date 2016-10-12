package com.example.main.shop.HttpUtils;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2016/10/8 0008.
 * Api接口
 */

public interface RequestServer {
    //登陆
    @FormUrlEncoded
    @POST("login")
    Call<RequestBody> login(@FieldMap Map<String ,String> filed);
    //获取手机验证码传入参数手机号,类型
    @GET("get_code")
    Call<RequestBody> getCode(@QueryMap Map<String ,String> map);
    //注册接口,手机号，验证码，邀请码（选填），密码
    @FormUrlEncoded
    @POST("register")
    Call<RequestBody>register(@Field("mobile") String number,@Field("pwd")String pwd,@Field("code")String code,@Field("invite_code")String in_code);





}
