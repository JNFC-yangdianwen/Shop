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
    //1.登陆
    @FormUrlEncoded
    @POST("login")
    Call<RequestBody> login(@FieldMap Map<String ,String> filed);
    //2.获取手机验证码传入参数手机号,类型
    @GET("get_code")
    Call<RequestBody> getCode(User user);
    //3.注册接口,手机号，验证码，邀请码（选填），密码
    @FormUrlEncoded
    @POST("register")
    Call<RequestBody>register(User user);
    //4.忘记密码
    @FormUrlEncoded
    @POST("forget_pwd")
    Call<RequestBody> forgetPsw(@FieldMap  Map<String,String> map);
    //5.添加个人信息，兴趣，用户名，性别。。。
    @FormUrlEncoded
    @POST("add_userinfo")
    Call<UserInfo> addUserInfo(UserInfo userInfo);
    //6.获取轮播图
    @GET("get_picture")
    Call<Picture.PictureInfo> getPicture();
    //7.添加动态
    @FormUrlEncoded
    @POST("add_post")
    Call<Dynamic.DynamicInfo> addPost(Dynamic.DynamicInfo dynamicInfo);
    //8.获取朋友圈信息
    @GET("dynamic_list")
    Call<Dynamic> getDynamic(@Query("uid")String uid);
    //9.我的消息
    @GET("get_message")
    Call<MyMsg> getMymsg();
    //10.动态点赞
    //需要参数动态id
    @GET("post_click")
    Call<RequestBody> clickPraise();
    //11.动态评论 需要评论者信息
    @FormUrlEncoded
    @POST("post_comment")
    Call<RequestBody> comment();
    //12分享回调  需要用户 id ，动态id
    @GET("share_return")
    Call<RequestBody> share();
}
