package com.example.main.shop.HttpUtils;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Administrator on 2016/11/8.
 */

public class NetOkHttp {
    private static NetOkHttp netOkHttp;
    private final  static String BASE_URL="";
    public static NetOkHttp getInstance() {
        if (netOkHttp == null) {
            netOkHttp =new NetOkHttp();
        }
        return netOkHttp ;
    }

    public Call getCall(Request request) {
        OkHttpClient oKhttpClient=new OkHttpClient();
        Call call = oKhttpClient.newCall(request);
        return call;
    }
}
