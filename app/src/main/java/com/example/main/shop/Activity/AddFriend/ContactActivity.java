package com.example.main.shop.Activity.AddFriend;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.main.shop.Activity.Rong.Conversation;
import com.example.main.shop.Adapter.ContactAdaptr;
import com.example.main.shop.Constans.FriendList;
import com.example.main.shop.Constans.User1;
import com.example.main.shop.HttpUtils.MyRequest;
import com.example.main.shop.HttpUtils.NetOkHttp;
import com.example.main.shop.R;
import com.example.main.shop.Utils.ActivityUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
//通讯录
public class ContactActivity extends AppCompatActivity {
@Bind(R.id.lv_contact)ListView listView;
    private ActivityUtils activityUtils;
    private List<FriendList.InfoBean> data;
    private static final String TAG = "ContactActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ButterKnife.bind(this);
        //创建集合
        data=new ArrayList<>();
        activityUtils = new ActivityUtils(this);
        String uid = User1.getInstance().getUid();
        Request frd = MyRequest.getInstance().getFrdList(uid);
        Call call = NetOkHttp.getInstance().getCall(frd);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.d(TAG, "ContactActivity: 。。。。。。。。。。。。。。。。。。。。"+string);
                try {
                    JSONObject jsonObject=new JSONObject(string);
                    JSONArray info = (JSONArray) jsonObject.get("info");
                    for (int i = 0; i < info.length(); i++) {
                        FriendList.InfoBean friendList= new FriendList.InfoBean();
                        String user_name = info.getJSONObject(i).getString("user_name");
                        String photo =  info.getJSONObject(i).getString("photo");
                        String id = info.getJSONObject(i).getString("id");
                        friendList.setPhoto(photo);
                        friendList.setId(id);
                        friendList.setUser_name(user_name);
                        data.add(friendList);
                        Log.d(TAG, "onResponse: ........................"+data);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Log.d(TAG, "onCreate: ............................."+data.size());
        ContactAdaptr contactAdaptr=new ContactAdaptr(this,data);
        listView.setAdapter(contactAdaptr);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == id) {
                    activityUtils.startActivity(Conversation.class);
                }
            }
        });
    }
    @OnClick(R.id.iv_add)
    public void addFrd(){
        activityUtils.startActivity(AddFeiendActivity.class);
        finish();
    }
}
