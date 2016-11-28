package com.example.main.shop.Activity.Wallet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.main.shop.Adapter.TravelAdapter;
import com.example.main.shop.Constans.Travel;
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

//旅游页面
public class TravelActivity extends AppCompatActivity {
@Bind(R.id.lv_travel)ListView listView;
    private List<Travel.InfoBean> data;
    private TravelAdapter adapter;
    public static String tid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);
        ButterKnife.bind(this);
        data=new ArrayList<>();
        final ActivityUtils activityUtils=new ActivityUtils(this);
        adapter = new TravelAdapter(data,this,this);
        new Thread(){
            @Override
            public void run() {
                super.run();
                getData();
            }
        }.start();
        listView.setAdapter(adapter);
        listView.setItemsCanFocus(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == id) {
                    Travel.InfoBean item = adapter.getItem(position);
                    tid = item.getId();
                    activityUtils.startActivity(TravelInfoActivity.class);
                }
            }
        });
    }

    private void getData() {
        Request travel = MyRequest.getInstance().travel();
        Call call = NetOkHttp.getInstance().getCall(travel);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                try {
                    JSONObject jsonObject=new JSONObject(string);
                    JSONArray info = (JSONArray) jsonObject.get("info");
                    for (int i = 0; i <info.length() ; i++) {
                        Travel.InfoBean infoBean=new Travel.InfoBean();
                        String id = info.getJSONObject(i).getString("id");
                        String title = info.getJSONObject(i).getString("title");
                        String picture = info.getJSONObject(i).getString("picture");
                        infoBean.setId(id);
                        infoBean.setTitle(title);
                        infoBean.setPicture(picture);
                        data.add(infoBean);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    @OnClick(R.id.iv_back)
    public void back(){
        finish();
    }
}
