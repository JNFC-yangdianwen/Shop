package com.example.main.shop.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.main.shop.Adapter.CourseAdapter;
import com.example.main.shop.Constans.Course;
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
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;


//课程页面
public class CourseActivity extends AppCompatActivity {
@Bind(R.id.lv_course)ListView listView;
    private List<Course.InfoBean> data;
    private static final String TAG = "CourseActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        ButterKnife.bind(this);
        data=new ArrayList<>();
//        getData();
        get();
        CourseAdapter adapter=new CourseAdapter(data,CourseActivity.this);
        listView.setAdapter(adapter);
    }

    private void get() {
        Request course = MyRequest.getInstance().getCourse();
        okhttp3.Call call = NetOkHttp.getInstance().getCall(course);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.d(TAG, "onResponse: ................................."+string);
                try {
                    JSONObject jsonObject=new JSONObject(string);
                    JSONArray info = (JSONArray) jsonObject.get("info");
                    for (int i = 0; i < info.length(); i++) {
                        Course.InfoBean course=new Course.InfoBean();
                        JSONObject object = info.getJSONObject(i);
                        String title = object.getString("title");
                        String picture = object.getString("picture");
                        course.setTitle(title);
                        course.setPicture(picture);
                        data.add(course);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }




//    private void getData() {
//        Call<Course.InfoBean> course =  NetClient.getInstance().course();
//        course.enqueue(new Callback<Course.InfoBean>() {
//            @Override
//            public void onResponse(Call<Course.InfoBean> call, Response<Course.InfoBean> response) {
//                Course.InfoBean infoBean = response.body();
//                Log.d(TAG, "onResponse: ......................................."+infoBean.toString());
//                   data.add(infoBean);
//            }
//
//            @Override
//            public void onFailure(Call<Course.InfoBean> call, Throwable t) {
//
//            }
//        });
//    }
});}
    }