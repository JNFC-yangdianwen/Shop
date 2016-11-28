package com.example.main.shop.Activity.Publish;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.main.shop.Adapter.PublishAdapter;
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
public class MyPublish extends AppCompatActivity {
    private static final String TAG = "MyPublish";
    @Bind(R.id.lv_my)ListView listView;
    private List<Publish.InfoBean> data;
    private List<String > pictureData;//图片集合
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_release);
        ButterKnife.bind(this);
        pictureData=new ArrayList<>();
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
                        Publish.InfoBean infoBean=new Publish.InfoBean();//我的发布实体类
                        String photo =  jsonArray.getJSONObject(i).getString("photo");//图像
                        String user_name =  jsonArray.getJSONObject(i).getString("user_name");//昵称
                        String time =  jsonArray.getJSONObject(i).getString("time");//时间
                        String content =  jsonArray.getJSONObject(i).getString("content");//发布的内容
                        String id = jsonArray.getJSONObject(i).getString("id");
                        String click_count = jsonArray.getJSONObject(i).getString("click_count");//点赞数量
                        JSONArray picture = jsonArray.getJSONObject(i).getJSONArray("picture");
                        for (int j = 0; j < picture.length(); j++) {
                            String pic = (String) picture.get(i);
                            pictureData.add(pic);
                        }
                        infoBean.setUser_name(user_name);
                        infoBean.setPhoto(photo);
                        infoBean.setContent(content);
                        infoBean.setId(id);
                        infoBean.setClick_count(click_count);
                        infoBean.setPicture(pictureData);
                        infoBean.setTime(time);
                        data.add(infoBean);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        PublishAdapter adapter=new PublishAdapter(data,getApplicationContext(),this);
//        Log.d(TAG, "onCreate: ............................." +data.size());
        listView.setAdapter(adapter);
    }
    @OnClick(R.id.iv_back)
    public void back(){
        finish();
    }
}
