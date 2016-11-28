package com.example.main.shop.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.main.shop.Activity.AddFriend.ContactActivity;
import com.example.main.shop.R;
import com.example.main.shop.Utils.ActivityUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/9/29 0029.
 * 会话列表
 */

public class ChatFragment extends Fragment {
    private ActivityUtils activityUtils;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mss, container, false);
        return view;

    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        activityUtils = new ActivityUtils(this);

    }
    //添加朋友，通讯录
   @OnClick(R.id.iv_addFrd)
    public void addFriend(){
         //跳转至通讯录页面
       activityUtils.startActivity(ContactActivity.class);
       onDetach();
   }
}
