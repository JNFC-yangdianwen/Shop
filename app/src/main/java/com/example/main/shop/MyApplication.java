package com.example.main.shop;

import android.app.Application;

import io.rong.imkit.RongIM;

/**
 * Created by Administrator on 2016/10/14 0014.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化融云
        RongIM.init(this);
    }
}
