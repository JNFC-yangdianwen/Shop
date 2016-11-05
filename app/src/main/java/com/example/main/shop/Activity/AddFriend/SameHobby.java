package com.example.main.shop.Activity.AddFriend;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.main.shop.Adapter.LikeAdapter;
import com.example.main.shop.Constans.UserInfo;
import com.example.main.shop.Constans.UserList;
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

//相同兴趣的人
public class SameHobby extends AppCompatActivity {
    @Bind(R.id.gv)GridView gridView;
    private static final String TAG = "LikeActivity";
    private List<String> data;
    private String [] like={
            "运动","旅游","美食","购物",
            "游戏","休闲","爬山","娱乐",
            "金融","语言","艺术","乐器"};
    private String item;
    private ActivityUtils activityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_same_hobby);
        ButterKnife.bind(this);
        activityUtils = new ActivityUtils(this);
        data=new ArrayList<>();
        for (int i = 0; i < like.length; i++) {
            data.add(like[i]);
        }
        final LikeAdapter likeAdapter=new LikeAdapter(data,this);
        gridView.setAdapter(likeAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int postion, long id) {
                if (postion==id) {
                    item = (String) likeAdapter.getItem(postion);
//                     LikeAdapter.textView.setTextColor(Color.parseColor("#32acd4"));
                    Toast.makeText(SameHobby.this, item, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    //查找好友
    @OnClick(R.id.tv_find)
    public  void save(){
        String uid = UserInfo.getInstance().getUid();
        Call<UserList.UserListInfo> likeFriend = NetClient.getInstance().likeFriend(7, item);
        likeFriend.enqueue(new Callback<UserList.UserListInfo>() {
            @Override
            public void onResponse(Call<UserList.UserListInfo> call, Response<UserList.UserListInfo> response) {
                UserList.UserListInfo userList = response.body();
                String like = userList.getLike();
                String username = userList.getUsername();
                Log.d(TAG, "onResponse: ............................................"+like+username);
            }

            @Override
            public void onFailure(Call<UserList.UserListInfo> call, Throwable t) {
                      new Throwable(t.getMessage());
            }
        });
    }
    //返回
    @OnClick(R.id.iv_back)
    public void back(){
        finish();
    }
}
