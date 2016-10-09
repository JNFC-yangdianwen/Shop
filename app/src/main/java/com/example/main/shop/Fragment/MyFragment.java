package com.example.main.shop.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.main.shop.Activity.UserInfoActivity;
import com.example.main.shop.Adapter.MyAdapter;
import com.example.main.shop.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/10/9 0009.
 * My的Fragment页面
 */

public class MyFragment extends Fragment implements AdapterView.OnItemClickListener {
    @Bind(R.id.my_lv)ListView mListView;
    @Bind(R.id.userInfo)LinearLayout mLinearLayout;
    private List<String> mData;
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
        MyAdapter myAdapter=new MyAdapter(getContext(),mData);
        mListView.setAdapter(myAdapter);
        mListView.setOnItemClickListener(this);
    }
    //跳转个人信息的Activity
    @OnClick(R.id.userInfo)
    public void onClick(){
        Intent intent =new Intent(getContext(), UserInfoActivity.class);
        startActivity(intent);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mData=new ArrayList<>();
        mData.add("我的消息");
        mData.add("升级vip");
        mData.add("我的发布");
        mData.add("我的订单");
        mData.add("我的推广");
        mData.add("关于平台");
        mData.add("意见反馈");
        mData.add("退出登录");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position==id) {
            if (id==0){

            }
            if (id==1){

            }
            if (id==2){

            }
            if (id==3){

            }
        }
    }
}
