package com.example.main.shop.HttpUtils;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.QueryMap;

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
    //call模型数据
    public Call<RequestBody> getCode(@QueryMap Map<String, String> param){
        return mRetrofit.create(RequestServer.class).getCode(param);
    }
}
