package com.example.main.shop.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.main.shop.Adapter.FriendAdapter;
import com.example.main.shop.View.FooterView;
import com.example.main.shop.R;
import com.github.jdsjlzx.util.RecyclerViewUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.sharesdk.framework.ShareSDK;

/**
 * Created by Administrator on 2016/9/29 0029.
 * 朋友圈的fragment
 */
public class FriendFragment extends Fragment {
    private String AppKey="17bfcb6cd2ea8";
    @Bind(R.id.rc)RecyclerView mRecyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.friend, container,false);
        return view;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FriendAdapter friendAdapter=new FriendAdapter(getContext());
        mRecyclerView.setAdapter(friendAdapter);
        //add a FooterView
        RecyclerViewUtils.setFooterView(mRecyclerView, new FooterView(getContext()));
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化Sdk
        ShareSDK.initSDK(getContext(),AppKey);
    }
}
