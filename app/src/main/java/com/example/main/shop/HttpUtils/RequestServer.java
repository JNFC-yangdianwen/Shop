package com.example.main.shop.HttpUtils;

import com.example.main.shop.Constans.CashAccount;
import com.example.main.shop.Constans.Course;
import com.example.main.shop.Constans.CourseInfo;
import com.example.main.shop.Constans.Dynamic;
import com.example.main.shop.Constans.FindFriend;
import com.example.main.shop.Constans.FriendList;
import com.example.main.shop.Constans.LoginResult;
import com.example.main.shop.Constans.MyMsg;
import com.example.main.shop.Constans.MySelf;
import com.example.main.shop.Constans.Order;
import com.example.main.shop.Constans.Picture;
import com.example.main.shop.Constans.PostInfo;
import com.example.main.shop.Constans.ReleaseDynamic;
import com.example.main.shop.Constans.Result;
import com.example.main.shop.Constans.SpreadResult;
import com.example.main.shop.Constans.Travel;
import com.example.main.shop.Constans.TravelInfo;
import com.example.main.shop.Constans.UserInfo;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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
    Call<LoginResult> login(@FieldMap Map<String,String> map);
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
    //多部分上传
    @Multipart
    @POST("add_userinfo")
    Call<Result> addUserInfo(@Part("uid") int uid,
                             @Part("photo") String photo,
                             @Part("like") String like,
                             @Part("sex") String sex,
                             @Part("user_name") String name
                             );
    //6.获取轮播图
    @GET("get_picture")
    Call<Picture> getPicture();
    //7.添加动态  参数，用户id，内容，图片，是否分享有奖，有奖数量，每个钱数
/**
 * Uid	用户id
 Content	内容
 Picture	图片
 is_share	是否分享有奖 有奖为1 无奖为2
 Count	有奖数量
 money_one	每个钱数
 */

    @Multipart
    @POST("add_post")
    Call<Result> addPost(@Part ("uid") int id,
                         @Part("content") String content,
                         @Part("picture") String picture,
                         @Part("is_share") int award,
                         @Part("count") int count,
                         @Part("money_one") double money);
    //8.获取朋友圈信息
    @GET("dynamic_list")
    Call<Dynamic> getDynamic(@Query("uid")int uid);
    //9.我的消息
    @GET("get_message")
    Call<MyMsg> getMymsg();
    //10.动态点赞
    //需要参数动态id
    @GET("post_click")
    Call<Result> clickPraise(@Field("uid") int uid,@Field("pid") int pid);
    //11.动态评论 需要评论者信息  动态id,发送人id，接收人回复id，content 内容
    @FormUrlEncoded
    @POST("post_comment")
    Call<Result> comment(@Field("pid") int pid,@Field("send_id") int sendId,
                         @Field("receive_id") int rereceiveId,@Field("content")String content);
    //12分享回调  需要用户 id ，动态id
    @GET("share_return")
    Call<Result> share(@Query("uid") int uid, @Query("pid") int pid);
    //13.修改手机号 参数 用户id，原手机号,新手机号，验证码
    @FormUrlEncoded
    @POST("edit_mobile" )
    Call<Result> resetMobile(@FieldMap Map<String,String> map);
    //14.动态详情 参数动态id
    @GET("post_info")
    Call<PostInfo> postInfo(@Query("pid") int id);
    //15.朋友列表 参数用户id
    @GET("my_friends")
    Call<FriendList> friendList(@Query("uid") int id);
    //16查找朋友
    @GET("find_user")
    Call<FindFriend> findFriend(@Query("mobile") String mobile);
    //17手机号查找添加好友
    @GET("mobile_add")
    Call<Result> addFriend(@Query("uid")int uid ,@Query("fid") int fid);
    // 18培训课程
    @GET("course_list")
    Call<Course> course();
    //19 课程详情 需要课程id
    @GET("course_detal")
    Call<CourseInfo> courseDetail(@Query("id" ) int id);
    //20.购买课程 参数 用户id 课程 id
    @GET ("buy_course")
    Call<Result> buyCourse(@Query("uid") int uid,@Query("cid") int cid);
    //21 钱包 参数 用户id
    @GET("wallet")
    Call<Result> wallet(@Query("uid") int id);
    // 22 我的
    @GET("myoneself")
    Call<MySelf> mySelf(@Query("uid") int id);
    //23 个人信息
    @GET ("user_info")
    Call<UserInfo> userInfo(@Query("uid" ) int id);
    //24 修改个人信息
    @FormUrlEncoded
    @POST("user_info_edit")
    Call<Result> modifyUserInfo(@Body UserInfo userInfo);
    // 25 提现账号 参数用户id
    @GET ("cash_account")
    Call<CashAccount> cashAccount(@Query("uid") int id);
   // 26 提现账户修改       参数 用户：id,   提现方式：1 支付宝 2，微信 3，银行卡   用户名  账号 ，银行卡开户行
    @FormUrlEncoded
    @POST("edit_account")
    Call<Result> modifyCashAccount(@FieldMap Map<String,String > map);
    //27.修改密码
    @FormUrlEncoded
    @POST("forget_pwd")
    Call<Result> modifyPsw(@FieldMap  Map<String,String> map);
    //28 我的发布 参数 用户id
    @GET("my_send")
    Call<Dynamic.DynamicInfo> myPublish(@Query("uid") int id);
    // 29 我的订单 参数 用户id 课程  1为课程，2为旅游
    @GET("my_order")
    Call<Order> myOrder(@Query("uid") int id, @Query("class") int type);
    //30订单详情
    @GET("course_detal")
    Call<CourseInfo> orderDetail(@Query("id" ) int id);
    //31 意见反馈  参数 用户id  意见反馈内容
    @FormUrlEncoded
    @POST("opinion")
    Call<Result> suggest(@Field("uid") int id,@Field("content") String content);
//    //32 我的推广 用户id
    @GET("my_exten")
    Call<SpreadResult> spread(@Query("uid") int id);
    //33 添加我的上级
    @GET("add_father")
    Call<Result> addHighLevel(@Query("uid")  int id,@Query("code") String code);
    //34 提现 参数用户 id 提现金额， 提现分类
    @GET("cash")
    Call<Result> cash(@Query("uid") int id,@Query("money") double money ,@Query("type") int type);
    // 35 旅游
    @GET("travel")
    Call<Travel> travel();
    //36 旅游详情 参数 旅游id
    @GET("travel_info")
    Call<TravelInfo> travelInfo(@Query("id") int id);
    // 37 购买旅游
    @GET("buy_travel")
     Call<Result> buyTravel(@Query("uid") int uid,@Query("tid") int tid);
}
