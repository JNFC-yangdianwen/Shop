package com.example.main.shop.HttpUtils;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2016/10/8 0008.
 */

public class NetClient {
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
        mRetrofit = new Retrofit.Builder().baseUrl("").build();
        return mRetrofit;
    }
    //call模型数据
    public Call<RequestBody> getApi(){
        return mRetrofit.create(RequestServer.class).getString();
    }
}
