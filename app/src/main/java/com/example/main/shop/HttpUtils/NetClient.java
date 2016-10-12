package com.example.main.shop.HttpUtils;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Field;

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
    public Call<RequestBody> getCode(Map<String,String> map){
        return getApi().getCode(map);
    }
    //注册
    public Call<RequestBody> register(@Field("mobile") String number,@Field("pwd")String pwd,@Field("code")String code,@Field("invite_code")String in_code){
        return getApi().register( number,pwd,code,in_code);
    }
    public Call<RequestBody> login(Map<String,String> map){
        return getApi().login(map);
    }
}
