package com.example.main.shop.HttpUtils;

import com.example.main.shop.Constans.Dynamic;
import com.example.main.shop.Constans.MyMsg;
import com.example.main.shop.Constans.Picture;
import com.example.main.shop.Constans.User;
import com.example.main.shop.Constans.UserInfo;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

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
    Call<RequestBody> getCode(User user);
    //注册接口,手机号，验证码，邀请码（选填），密码
    @FormUrlEncoded
    @POST("register")
    Call<RequestBody>register(User user);
    //忘记密码
    @FormUrlEncoded
    @POST("forget_pwd")
    Call<RequestBody> forgetPsw(@FieldMap  Map<String,String> map);
    //添加个人信息，兴趣，用户名，性别。。。
    @FormUrlEncoded
    @POST("add_userinfo")
    Call<UserInfo> addUserInfo(UserInfo userInfo);
    //获取轮播图
    @GET("get_picture")
    Call<Picture.PictureInfo> getPicture();
    //添加动态
    @FormUrlEncoded
    @POST("add_post")
    Call<Dynamic.DynamicInfo> addPost(Dynamic.DynamicInfo dynamicInfo);
    //获取朋友圈信息
    @GET("dynamic_list")
    Call<Dynamic> getDynamic(@Query("uid")String uid);
    //我的消息
    @GET("get_message")
    Call<MyMsg> getMymsg();
}
