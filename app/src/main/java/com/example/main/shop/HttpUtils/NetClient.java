package com.example.main.shop.HttpUtils;

import com.example.main.shop.Constans.Picture;
import com.example.main.shop.Constans.User;
import com.example.main.shop.Constans.UserInfo;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2016/10/8 0008.
 * http://renrenshang.tongyi100.cn/index.php/Api/get_code
 */

public class NetClient {
    private final String BASE_URL="http://renrenshang.tongyi100.cn/index.php/Api/";
    private static NetClient mNetClient;
    private Retrofit mRetrofit;
    //单例模式
    public static NetClient getInstance() {
        if (mNetClient == null) {
            mNetClient = new NetClient();
        }
        return mNetClient;
    }
    //创建retrofit对象
    public Retrofit getRetrofit() {
        mRetrofit = new Retrofit.Builder().baseUrl(BASE_URL).build();
        return mRetrofit;
    }
    public RequestServer getApi(){
        return mRetrofit.create(RequestServer.class);
    }
    //call模型数据,获取手机验证码
    public Call<RequestBody> getCode(User user){
        return getApi().getCode(user);
    }
    //注册
    public Call<RequestBody> register(User user){
        return getApi().register(user);
    }
    //登陆
    public Call<RequestBody> login(Map<String,String> map){
        return getApi().login(map);
    }
    //忘记密码
    public Call<RequestBody> forgetPsw(Map<String,String> map){
        return getApi().forgetPsw(map);
    }
    //添加用户信息
    public Call<UserInfo> addUserInfo(UserInfo userInfo){
        return getApi().addUserInfo(userInfo);
    }
    //获取轮播图
    public Call<Picture.PictureInfo> getPicture(){
        return  getApi().getPicture();
    }
}
