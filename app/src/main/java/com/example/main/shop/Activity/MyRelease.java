package com.example.main.shop.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.main.shop.Adapter.FriendAdapter;
import com.example.main.shop.Constans.Dynamic;
import com.example.main.shop.Constans.UserInfo;
import com.example.main.shop.HttpUtils.NetClient;
import com.example.main.shop.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//我的发布页面
public class MyRelease extends AppCompatActivity {
@Bind(R.id.lv_my)ListView listView;
    private List< Dynamic.DynamicInfo> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_release);
        ButterKnife.bind(this);
         data=new ArrayList<>();
        String uid = UserInfo.getInstance().getUid();
        Call<Dynamic.DynamicInfo> call = NetClient.getInstance().myPublish(uid);
        call.enqueue(new Callback<Dynamic.DynamicInfo>() {
            @Override
            public void onResponse(Call<Dynamic.DynamicInfo> call, Response<Dynamic.DynamicInfo> response) {
                Dynamic.DynamicInfo info = response.body();
                data.add(info);
            }

            @Override
            public void onFailure(Call<Dynamic.DynamicInfo> call, Throwable t) {

            }
        });
        FriendAdapter friendAdapter=new FriendAdapter(data,this);
        listView.setAdapter(friendAdapter);
    }
}
