package com.example.main.shop.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.main.shop.Activity.LoginActivity;
import com.example.main.shop.Activity.MyInfoActivity;
import com.example.main.shop.Activity.MyMssAcitivity;
import com.example.main.shop.Activity.ReleaseMsgActivity;
import com.example.main.shop.Activity.SpreadActivity;
import com.example.main.shop.Activity.SuggestActivity;
import com.example.main.shop.Activity.UserInfoActivity;
import com.example.main.shop.Constans.UserInfo;
import com.example.main.shop.R;
import com.example.main.shop.Utils.ActivityUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/10/9 0009.
 * My的Fragment页面
 */

public class MyFragment extends Fragment{

    @Bind(R.id.userInfo) RelativeLayout mRelativeLayout;
    @Bind(R.id.userPhoto)ImageView imageView;//头像
    private ActivityUtils mActivityUtils;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        mActivityUtils=new ActivityUtils(this);
        //设置头像
        ImageLoader.getInstance().displayImage(UserInfo.getInstance().getPhoto(),imageView);
    }

    @OnClick({R.id.userInfo,R.id.rl_mss,
            R.id.rl_vip,R.id.rl_publish,
            R.id.rl_order,R.id.rl_spread,
            R.id.rl_platform,R.id.rl_suggest,
            R.id.rl_exit})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.userInfo:  //跳转个人信息的Activity
                mActivityUtils.startActivity(MyInfoActivity.class);
            break;
            case R.id.rl_mss://进入我的消息页面
                mActivityUtils.startActivity(MyMssAcitivity.class);
            break;
            case R.id.rl_vip://进入升级vip页面
//                mActivityUtils.startActivity(UserInfoActivity.class);
            break;
            case R.id.rl_publish://进入我的发布页面
//                mActivityUtils.startActivity(UserInfoActivity.class);
            break;
            case R.id.rl_order://进入我的订单页面
//                mActivityUtils.startActivity(UserInfoActivity.class);
            break;
            case R.id.rl_spread://进入我的推广页面
                mActivityUtils.startActivity(SpreadActivity.class);
            break;
            case R.id.rl_platform://进入关于平台页面
//                mActivityUtils.startActivity(UserInfoActivity.class);
            break;
            case R.id.rl_suggest://进入反馈意见页面
                mActivityUtils.startActivity(SuggestActivity.class);
            break;
            case R.id.rl_exit://退出登录
                mActivityUtils.startActivity(LoginActivity.class);
            break;
        }

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
