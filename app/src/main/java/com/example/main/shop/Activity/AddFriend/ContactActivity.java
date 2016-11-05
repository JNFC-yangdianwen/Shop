package com.example.main.shop.Activity.AddFriend;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.main.shop.Adapter.ContactAdaptr;
import com.example.main.shop.Constans.FriendList;
import com.example.main.shop.Constans.UserInfo;
import com.example.main.shop.HttpUtils.NetClient;
import com.example.main.shop.R;
import com.example.main.shop.Utils.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactActivity extends AppCompatActivity {
@Bind(R.id.lv_contact)ListView listView;
    private ActivityUtils activityUtils;
    private List<FriendList> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ButterKnife.bind(this);
        data=new ArrayList<>();
        activityUtils = new ActivityUtils(this);
        String uid = UserInfo.getInstance().getUid();
        Call<FriendList> listCall = NetClient.getInstance().friendList(uid);
        listCall.enqueue(new Callback<FriendList>() {
            @Override
            public void onResponse(Call<FriendList> call, Response<FriendList> response) {
                FriendList body = response.body();
                data.add(body);
            }

            @Override
            public void onFailure(Call<FriendList> call, Throwable t) {

            }
        });
        ContactAdaptr contactAdaptr=new ContactAdaptr(this,data);
        listView.setAdapter(contactAdaptr);
    }
    @OnClick(R.id.iv_add)
    public void addFrd(){
        activityUtils.startActivity(AddFeiendActivity.class);
        finish();
    }
}
