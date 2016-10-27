package com.example.main.shop;

import android.app.Application;

import com.example.main.shop.Constans.RegistResult;
import com.example.main.shop.Constans.UserInfo;

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
        //初始化用户
       UserInfo.init(this);
    }
}
