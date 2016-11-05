package com.example.main.shop.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.main.shop.Activity.ReleaseMsgActivity;
import com.example.main.shop.Adapter.FriendAdapter;
import com.example.main.shop.Constans.Dynamic;
import com.example.main.shop.Constans.UserInfo;
import com.example.main.shop.HttpUtils.NetClient;
import com.example.main.shop.R;
import com.example.main.shop.Utils.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    @Bind(R.id.lv_frd)ListView mListview;//朋友圈的listview
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
        mUtils = new ActivityUtils(this);
        mData=new ArrayList<>();
        // 获取朋友圈信息
        String uid = UserInfo.getInstance().getUid();
        Call<Dynamic> dynamicCall = NetClient.getInstance().getDynamic(uid);
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
                    List<Dynamic.DynamicInfo> infos = dynamic.getInfo();
                    mData.addAll(infos);
                    return;
                }
                if (code == 102) {
                    mUtils.showToast(msg);
                    return;
                }
            }
            @Override
            public void onFailure(Call<Dynamic> call, Throwable t) {
                new Throwable(t.getMessage());
            }
        });
        FriendAdapter friendAdapter=new FriendAdapter(mData,getContext());
        mListview.setAdapter(friendAdapter);
        //add a FooterView

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化Sdk
        ShareSDK.initSDK(getContext(),AppKey);
    }
    @OnClick(R.id.release)
    public void release(){
        mUtils.startActivity(ReleaseMsgActivity.class);
    }

}
