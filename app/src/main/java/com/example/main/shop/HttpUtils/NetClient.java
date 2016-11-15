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
import com.example.main.shop.Constans.Publish;
import com.example.main.shop.Constans.RegistResult;
import com.example.main.shop.Constans.Result;
import com.example.main.shop.Constans.SpreadResult;
import com.example.main.shop.Constans.Travel;
import com.example.main.shop.Constans.TravelInfo;
import com.example.main.shop.Constans.UserInfo;
import com.example.main.shop.Constans.UserList;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
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
    //Public/Uploads/3.jpg
    public static String BASE_URL="http://renrenshang.tongyi100.cn/index.php/Api/";
    public static String IMAGE_URL="http://renrenshang.tongyi100.cn/";
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
        Gson gson= new Gson();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
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
            @Part("uid") String uid,
            @Part ("picture")String picture, @Part("user_name") String name,
            @Part("like") String like, @Part("sex") String sex){
        return mGetApi.addUserInfo(uid, picture, name, like, sex);
    }



    //获取轮播图
    public Call<Picture> getPicture(){
        return  mGetApi.getPicture();
    }

    /**
     * Uid	用户id
     * Content	内容
     * Picture	图片
     * is_share	是否分享有奖 有奖为1 无奖为2
     * Count	有奖数量
     * money_one	每个钱数
     *
     * @param uid
     * @param content
     * @param picture
     * @param is_share
     * @param count
     * @param moneyone
     */
    @Override
    public Call<Result> addPost(@Part("uid") String uid, @Part("content") String content, @Part ("picture")List<String> picture, @Part("is_share") String is_share, @Part("count") String count, @Part("money_one") String moneyone) {
        return mGetApi.addPost(uid, content, picture, is_share, count, moneyone);
    }


    //发表动态

    //添加动态

    //朋友圈消息
    @Override
    public Call<Dynamic> getDynamic(@Query("uid") String uid) {
        return mGetApi.getDynamic(uid);
    }
    //我的消息
    @Override
    public Call<MyMsg> getMymsg() {
        return mGetApi.getMymsg();
    }
    //动态点赞
    @Override
    public Call<Result> clickPraise(@Field("uid") String uid,@Field("pid") int pid) {
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
    public Call<Result> share(@Query("uid") String uid, @Query("pid") int pid) {
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
    public Call<FriendList> friendList(@Query("id") String id) {
        return mGetApi.friendList(id);
    }
    //查找朋友
    @Override
    public Call<FindFriend.InfoBean> findFriend(@Query("mobile") String mobile) {
        return mGetApi.findFriend(mobile);
    }
     //手机号查找添加好友
    @Override
    public Call<Result> addFriend(@Query("uid")String uid ,@Query("fid") int fid) {
        return mGetApi.addFriend(uid, fid);
    }
    //培训课程
    @Override
    public Call<Course.InfoBean> course() {
        return mGetApi.course();
    }
   //课程详情
    @Override
    public Call courseDetail(@Query("id") int id) {
        return mGetApi.courseDetail(id);
    }
     //购买课程
    @Override
    public Call<Result> buyCourse(@Query("uid") String uid,@Query("cid") int cid) {
        return mGetApi.buyCourse(uid, cid);
    }
    //用户钱包
    @Override
    public Call<Result> wallet(@Query("id") String id) {
        return mGetApi.wallet(id);
    }
    //我的
    @Override
    public Call<MySelf> mySelf(@Query("id") String id) {
        return mGetApi.mySelf(id);
    }
    //获取个人信息
    @Override
    public Call<UserInfo> userInfo(@Query("id") String id) {
        return mGetApi.userInfo(id);
    }

    /**    //修改个人信息
     * 多部分上传Uid	用户id
     * Picture	头像
     * user_name	用户名
     * Like	爱好
     * Sex	性别
     * Sign	个性签名
     *
     * @param uid
     * @param picture
     * @param name
     * @param like
     * @param sex
     * @param sign
     */
    @Override
    public Call<Result> modifyUserInfo(@Part("uid") String uid, @Part MultipartBody.Part picture, @Part("user_name") String name, @Part("like") String like, @Part("sex") String sex, @Part("sign") String sign) {

        return mGetApi.modifyUserInfo(uid, picture, name, like, sex, sign);
    }
   // 提现账户
    @Override
    public Call<CashAccount> cashAccount(@Query("id") String id) {
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
    public Call<Publish> myPublish(@Query("uid") String uid) {
        return mGetApi.myPublish(uid);
    }
    //订单
    @Override
    public Call<Order> myOrder(@Query("id") String id, @Query("class") int type) {
        return mGetApi.myOrder(id, type);
    }
    //订单详情
    @Override
    public Call<CourseInfo> orderDetail(@Query("id") String id) {
        return mGetApi.orderDetail(id);
    }
    // 意见反馈
    @Override
    public Call<Result> suggest(@Field("uid") String id, @Field("content") String content) {
        return mGetApi.suggest(id, content);
    }
    //我的推广
    @Override
    public Call<SpreadResult> spread(@Query("uid") String id) {
        return mGetApi.spread(id);
    }

    //添加我的上级
    @Override
    public Call<Result> addHighLevel(@Query("uid") String id, @Query("code") String code) {
        return mGetApi.addHighLevel(id,code);
    }
      //提现
    @Override
    public Call<Result> cash(@Query("uid") String id, @Query("money") double money, @Query("type") int type) {
        return mGetApi.cash(id, money, type);
    }
    //旅游
    @Override
    public Call<Travel> travel() {
        return mGetApi.travel();
    }
   // 旅游详情
    @Override
    public Call<TravelInfo> travelInfo(@Query("id") String id) {
        return mGetApi.travelInfo(id);
    }
    //购买旅游
    @Override
    public Call<Result> buyTravel(@Query("uid") String uid, @Query("tid") int tid) {
        return mGetApi.buyTravel(uid,tid);
    }

    /**
     * 参数uid，时间month（格式必须为：年 -月） 钱数money
     *   升级vip
     * @param map
     */
    @Override
    public Call<Result> vip(@QueryMap Map<String, String> map) {
        return mGetApi.vip(map);
    }
    //按照爱好查找用户
    @Override
    public Call<UserList.UserListInfo> likeFriend(@Part("uid") String uid, @Part("like") String like) {
        return mGetApi.likeFriend(uid, like);
    }
}
