package com.example.main.shop;

import android.app.Application;

import com.example.main.shop.Constans.UserInfo;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by Administrator on 2016/10/14 0014.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化融云
//        RongIM.init(this);
        //初始化用户
       UserInfo.init(this);
        initImageLoader();
    }  private void initImageLoader() {

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.tou1)
                .showImageOnFail(R.drawable.tou1)
                .showImageForEmptyUri(R.drawable.tou1)
                // .displayer(new RoundedBitmapDisplayer(getResources().getDimensionPixelOffset(R.dimen.dp_10)))
                .cacheOnDisk(true)  // 打开disk
                .cacheInMemory(true) // 打开cache
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .memoryCacheSize(5*1024*1024) // 设置内存缓存5MB
                .defaultDisplayImageOptions(options)// 设置默认的显示选项
                .build();

        ImageLoader.getInstance().init(config);
    }

}
