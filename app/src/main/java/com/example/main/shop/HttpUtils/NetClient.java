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
import com.example.main.shop.Constans.RegistResult;
import com.example.main.shop.Constans.ReleaseDynamic;
import com.example.main.shop.Constans.Result;
import com.example.main.shop.Constans.SpreadResult;
import com.example.main.shop.Constans.Travel;
import com.example.main.shop.Constans.TravelInfo;
import com.example.main.shop.Constans.User;
import com.example.main.shop.Constans.UserInfo;

import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2016/10/8 0008.
 * http://renrenshang.tongyi100.cn/index.php/Api/get_code
 */

public class NetClient implements RequestServer {
    public static String BASE_URL="http://renrenshang.tongyi100.cn/index.php/Api/";
    private static NetClient mNetClient;
    private final RequestServer mGetApi;

    //单例模式
    public static NetClient getInstance() {
        if (mNetClient == null) {
            mNetClient = new NetClient();
        }
        return mNetClient;
    }
    public NetClient() {
        //初始化retrofit
        OkHttpClient okHttpClient=new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        mGetApi = retrofit.create(RequestServer.class);
    }

    //call模型数据,获取手机验证码
    public Call<Result> getCode(@QueryMap Map<String,String> map){
        return mGetApi.getCode(map);
    }
    //注册
    public Call<RegistResult> register(@FieldMap Map<String,String> map){
        return mGetApi.register(map);
    }
    //登陆
    @Override
    public Call<LoginResult> login(@FieldMap Map<String,String> map) {
        return mGetApi.login(map);
    }
    //忘记密码
    public Call<Result> forgetPsw(@FieldMap Map<String,String> map){
        return mGetApi.forgetPsw(map);
    }
    //添加用户信息
    @Override
    public Call<Result> addUserInfo(
            @Part("uid") int uid,
            @Part("photo") String photo, @Part("user_name") String name,
            @Part("like") String like, @Part("sex") String sex){
        return mGetApi.addUserInfo(uid, photo, name, like, sex);
    }



    //获取轮播图
    public Call<Picture> getPicture(){
        return  mGetApi.getPicture();
    }
   //发表动态
    @Override
    public Call<Result> addPost(@Part("uid") int id,
                                @Part("content") String content,
                                @Part("picture") String picture,
                                @Part("is_share") int award,
                                @Part("count") int count,
                                @Part("money_one") double money) {
        return mGetApi.addPost(id, content, picture, award, count, money);
    }
    //添加动态

    //朋友圈消息
    @Override
    public Call<Dynamic> getDynamic(@Query("uid") int uid) {
        return mGetApi.getDynamic(uid);
    }
    //我的消息
    @Override
    public Call<MyMsg> getMymsg() {
        return mGetApi.getMymsg();
    }
    //动态点赞
    @Override
    public Call<Result> clickPraise(@Field("uid") int uid,@Field("pid") int pid) {
        return mGetApi.clickPraise(uid, pid);
    }

    //动态评论
    @Override
    public Call<Result> comment(@Field("pid") int pid,@Field("send_id") int sendId,
                                @Field("receive_id") int rereceiveId,@Field("content")String content) {
        return mGetApi.comment(pid, sendId, rereceiveId, content);
    }

    //分享回调
    @Override
    public Call<Result> share(@Query("uid") int uid, @Query("pid") int pid) {
        return mGetApi.share(uid, pid);
    }
    //修改手机号
    @Override
    public Call<Result> resetMobile(@FieldMap Map<String, String> map) {
        return mGetApi.resetMobile(map);
    }
    //动态详情
    @Override
    public Call<PostInfo> postInfo(@Query("pid") int id) {
        return mGetApi.postInfo(id);
    }
     //朋友列表
    @Override
    public Call<FriendList> friendList(@Query("id") int id) {
        return mGetApi.friendList(id);
    }
    //查找朋友
    @Override
    public Call<FindFriend> findFriend(@Query("mobile") String mobile) {
        return mGetApi.findFriend(mobile);
    }
     //手机号查找添加好友
    @Override
    public Call<Result> addFriend(@Query("uid")int uid ,@Query("fid") int fid) {
        return mGetApi.addFriend(uid, fid);
    }
    //培训课程
    @Override
    public Call<Course> course() {
        return mGetApi.course();
    }
   //课程详情
    @Override
    public Call courseDetail(@Query("id") int id) {
        return mGetApi.courseDetail(id);
    }
     //购买课程
    @Override
    public Call<Result> buyCourse(@Query("uid") int uid,@Query("cid") int cid) {
        return mGetApi.buyCourse(uid, cid);
    }
    //用户钱包
    @Override
    public Call<Result> wallet(@Query("id") int id) {
        return mGetApi.wallet(id);
    }
    //我的
    @Override
    public Call<MySelf> mySelf(@Query("id") int id) {
        return mGetApi.mySelf(id);
    }
    //个人信息
    @Override
    public Call<UserInfo> userInfo(@Query("id") int id) {
        return mGetApi.userInfo(id);
    }
    //修改个人信息
    @Override
    public Call<Result> modifyUserInfo(@Body UserInfo userInfo) {
        return mGetApi.modifyUserInfo(userInfo);
    }
   // 提现账户
    @Override
    public Call<CashAccount> cashAccount(@Query("id") int id) {
        return mGetApi.cashAccount(id);
    }
    //修改提现账户
    @Override
    public Call<Result> modifyCashAccount(@FieldMap Map<String, String> map) {
        return mGetApi.modifyCashAccount(map);
    }
    //修改密码
    @Override
    public Call<Result> modifyPsw(@FieldMap Map<String, String> map) {
        return mGetApi.modifyPsw(map);
    }
   //我的发布
    @Override
    public Call<Dynamic.DynamicInfo> myPublish(@Query("uid") int uid) {
        return mGetApi.myPublish(uid);
    }
    //订单
    @Override
    public Call<Order> myOrder(@Query("id") int id, @Query("class") int type) {
        return mGetApi.myOrder(id, type);
    }
    //订单详情
    @Override
    public Call<CourseInfo> orderDetail(@Query("id") int id) {
        return mGetApi.orderDetail(id);
    }
    // 意见反馈
    @Override
    public Call<Result> suggest(@Field("uid") int id, @Field("content") String content) {
        return mGetApi.suggest(id, content);
    }
    //我的推广
    @Override
    public Call<SpreadResult> spread(@Query("uid") int id) {
        return mGetApi.spread(id);
    }

    //添加我的上级
    @Override
    public Call<Result> addHighLevel(@Query("uid") int id, @Query("code") String code) {
        return mGetApi.addHighLevel(id,code);
    }
      //提现
    @Override
    public Call<Result> cash(@Query("uid") int id, @Query("money") double money, @Query("type") int type) {
        return mGetApi.cash(id, money, type);
    }
    //旅游
    @Override
    public Call<Travel> travel() {
        return mGetApi.travel();
    }
   // 旅游详情
    @Override
    public Call<TravelInfo> travelInfo(@Query("id") int id) {
        return mGetApi.travelInfo(id);
    }
    //购买旅游
    @Override
    public Call<Result> buyTravel(@Query("uid") int uid, @Query("tid") int tid) {
        return mGetApi.buyTravel(uid,tid);
    }
}
