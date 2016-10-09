package com.example.main.shop.HttpUtils;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2016/10/8 0008.
 */

public interface RequestServer {
    @GET(" ")
    Call<RequestBody>getString();
}
