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
import com.example.main.shop.Constans.Dynamic;
import com.example.main.shop.Constans.UserInfo;
import com.example.main.shop.HttpUtils.NetClient;
import com.example.main.shop.Utils.ActivityUtils;
import com.example.main.shop.View.FooterView;
import com.example.main.shop.R;
import com.github.jdsjlzx.util.RecyclerViewUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.sharesdk.framework.ShareSDK;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/9/29 0029.
 * 朋友圈的fragment
 */
public class FriendFragment extends Fragment {
    private String AppKey="17bfcb6cd2ea8";
    @Bind(R.id.rc)RecyclerView mRecyclerView;
    private List<Dynamic.DynamicInfo> mData;
    private ActivityUtils mUtils;

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
        mUtils = new ActivityUtils();
        UserInfo userInfo=new UserInfo();
        // 获取朋友圈信息
        Call<Dynamic> dynamicCall = NetClient.getInstance().getDynamic(userInfo.getUid());
        dynamicCall.enqueue(new Callback<Dynamic>() {
            @Override
            public void onResponse(Call<Dynamic> call, Response<Dynamic> response) {
                Dynamic body = response.body();
                int code = body.getCode();
                String msg = body.getMsg();
                /**
                 *   如果返回码为101表明获取朋友圈的信息成功
                 *   如果返回码为102表明参数为空
                 */

                if (code == 101) {
                    Dynamic dynamic = response.body();
                    List<Dynamic.DynamicInfo> infos = dynamic.getInfos();
                    mData.addAll(infos);
                    return;
                }
                if (code == 102) {
                    mUtils.Toast(getContext(),msg);
                    return;
                }
            }
            @Override
            public void onFailure(Call<Dynamic> call, Throwable t) {
                new Throwable(t.getMessage());
            }
        });
        mData=new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FriendAdapter friendAdapter=new FriendAdapter(getContext(),mData);
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
