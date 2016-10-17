package com.example.main.shop.HttpUtils;

import com.example.main.shop.Constans.Dynamic;
import com.example.main.shop.Constans.MyMsg;
import com.example.main.shop.Constans.Picture;
import com.example.main.shop.Constans.Result;
import com.example.main.shop.Constans.UserInfo;
import com.squareup.okhttp.ResponseBody;

import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FieldMap;
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
    public Call<Result> register(@FieldMap Map<String,String> map){
        return mGetApi.register(map);
    }
    //登陆
    @Override
    public Call<Result> login(@FieldMap Map<String,String> map) {
        return mGetApi.login(map);
    }
    //忘记密码
    public Call<Result> forgetPsw(@FieldMap Map<String,String> map){
        return mGetApi.forgetPsw(map);
    }
    //添加用户信息
    public Call<UserInfo> addUserInfo(@FieldMap Map<String,String> map){
        return mGetApi.addUserInfo(map);
    }
    //获取轮播图
    public Call<Picture.PictureInfo> getPicture(){
        return  mGetApi.getPicture();
    }

    @Override
    public Call<Dynamic.DynamicInfo> addPost(@FieldMap Map<String,String> map) {
        return mGetApi.addPost(map);
    }

    @Override
    public Call<Dynamic> getDynamic(@Query("uid") String uid) {
        return mGetApi.getDynamic(uid);
    }

    @Override
    public Call<MyMsg> getMymsg() {
        return mGetApi.getMymsg();
    }

    @Override
    public Call<ResponseBody> clickPraise() {
        return mGetApi.clickPraise();
    }

    @Override
    public Call<ResponseBody> comment() {
        return mGetApi.comment();
    }

    @Override
    public Call<ResponseBody> share() {
        return mGetApi.share();
    }

    @Override
    public Call<Result> resetMobile(@FieldMap Map<String, String> map) {
        return mGetApi.resetMobile(map);
    }
}
