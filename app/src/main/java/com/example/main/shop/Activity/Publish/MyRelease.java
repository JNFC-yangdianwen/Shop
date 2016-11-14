package com.example.main.shop.Activity.Publish;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.main.shop.Adapter.FriendAdapter;
import com.example.main.shop.Constans.Publish;
import com.example.main.shop.Constans.User1;
import com.example.main.shop.HttpUtils.MyRequest;
import com.example.main.shop.HttpUtils.NetOkHttp;
import com.example.main.shop.R;

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

//我的发布页面
public class MyRelease extends AppCompatActivity {
    private static final String TAG = "MyRelease";
@Bind(R.id.lv_my)ListView listView;
    private List<Publish.InfoBean> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_release);
        ButterKnife.bind(this);
         data=new ArrayList<>();
        String uid = User1.getInstance().getUid();
//        Call<Publish> call = NetClient.getInstance().myPublish(uid);
//        Log.d(TAG, "onCreate: ...........................uid="+uid);
//        call.enqueue(new Callback<Publish>() {
//            @Override
//            public void onResponse(Call<Publish> call, Response<Publish> response) {
//                Publish info = response.body();
//                List<Publish.InfoBean> info1 = info.getInfo();
//                for (int i = 0; i < info1.size(); i++) {
//                    String time = info1.get(i).getTime();
//                    String user_name = info1.get(i).getUser_name();
//                    String content = info1.get(i).getContent();
//                    Log.d(TAG, "onResponse: ....................."+info1.size()+time+user_name+content);
//                }
//                data.add(info);
//            }
//
//            @Override
//            public void onFailure(Call<Publish> call, Throwable t) {
//
//            }
//        });
        Request mySend = MyRequest.getInstance().getMySend(uid);
        Call call = NetOkHttp.getInstance().getCall(mySend);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.d(TAG, "onResponse: 。。。。。。。。"+string);
                try {
                    JSONObject jsonObject=new JSONObject(string);
                    JSONArray jsonArray = (JSONArray) jsonObject.get("info");
                    for (int i = 0; i < jsonArray.length(); i++) {
                       Publish.InfoBean infoBean=new Publish.InfoBean();
                        String photo = (String) jsonArray.getJSONObject(i).getString("photo");
                        String user_name = (String) jsonArray.getJSONObject(i).getString("user_name");
                        String time = (String) jsonArray.getJSONObject(i).getString("time");
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String content = (String) jsonArray.getJSONObject(i).getString("content");
                        infoBean.setUser_name(user_name);
                        infoBean.setPhoto(photo);
                        infoBean.setContent(content);
//                        infoBean.setPicture(List<?> picture);
                        infoBean.setTime(time);
                        data.add(infoBean);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        FriendAdapter friendAdapter=new FriendAdapter(data,this);
        Log.d(TAG, "onCreate: ............................." +data.size());
        listView.setAdapter(friendAdapter);
    }
    @OnClick(R.id.iv_back)
    public void back(){
        finish();
    }
}
