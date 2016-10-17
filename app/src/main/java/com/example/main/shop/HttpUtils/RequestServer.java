package com.example.main.shop.HttpUtils;

import com.example.main.shop.Constans.Dynamic;
import com.example.main.shop.Constans.MyMsg;
import com.example.main.shop.Constans.Picture;
import com.example.main.shop.Constans.Result;
import com.example.main.shop.Constans.UserInfo;
import com.squareup.okhttp.ResponseBody;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2016/10/8 0008.
 * Api接口
 */

public interface RequestServer {
    //1.登陆，手机号，密码
    @FormUrlEncoded
    @POST("login")
    Call<Result> login(@FieldMap Map<String,String> map);
    //2.获取手机验证码传入参数手机号,类型
    @GET("get_code")
    Call<Result> getCode(@QueryMap Map<String,String> map);
    //3.注册接口,手机号，验证码，邀请码（选填），密码
    @FormUrlEncoded
    @POST("register")
    Call<Result>register(@FieldMap Map<String,String> map);
    //4.忘记密码
    @FormUrlEncoded
    @POST("forget_pwd")
    Call<Result> forgetPsw(@FieldMap  Map<String,String> map);
    //5.添加个人信息，兴趣，用户名，性别。。。
    @FormUrlEncoded
    @POST("add_userinfo")
    Call<UserInfo> addUserInfo(@FieldMap Map<String,String> map);
    //6.获取轮播图
    @GET("get_picture")
    Call<Picture.PictureInfo> getPicture();
    //7.添加动态
    @FormUrlEncoded
    @POST("add_post")
    Call<Dynamic.DynamicInfo> addPost(@FieldMap Map<String,String> map);
    //8.获取朋友圈信息
    @GET("dynamic_list")
    Call<Dynamic> getDynamic(@Query("uid")String uid);
    //9.我的消息
    @GET("get_message")
    Call<MyMsg> getMymsg();
    //10.动态点赞
    //需要参数动态id
    @GET("post_click")
    Call<ResponseBody> clickPraise();
    //11.动态评论 需要评论者信息
    @FormUrlEncoded
    @POST("post_comment")
    Call<ResponseBody> comment();
    //12分享回调  需要用户 id ，动态id
    @GET("share_return")
    Call<ResponseBody> share();
    //13.修改手机号 参数 用户id，原手机号,新手机号，验证码
    @FormUrlEncoded
    @POST("edit_mobile" )
    Call<Result> resetMobile(@FieldMap Map<String,String> map);
}
