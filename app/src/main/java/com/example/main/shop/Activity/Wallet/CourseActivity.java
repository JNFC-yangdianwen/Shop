package com.example.main.shop.Activity.Wallet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.main.shop.Adapter.CourseAdapter;
import com.example.main.shop.Constans.Course;
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


//课程页面
public class CourseActivity extends AppCompatActivity {
@Bind(R.id.lv_course)ListView listView;
    private List<Course.InfoBean> data;
    private static final String TAG = "CourseActivity";
    private ActivityUtils activityUtils;
    public static String courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        ButterKnife.bind(this);
        data=new ArrayList<>();
        activityUtils = new ActivityUtils(this);
        get();
        final CourseAdapter adapter=new CourseAdapter(data,CourseActivity.this);
        listView.setAdapter(adapter);
        listView.setItemsCanFocus(true);//listview的item的焦点设置
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == id) {
                    //获取课程详情
                    Course.InfoBean item = adapter.getItem(position);
                    courseId= item.getId();
                    String title = item.getTitle();
                    Log.d(TAG, "onItemClick: ................"+courseId+title+item.getPicture());
                    activityUtils.startActivity(CourseDetail.class);

                }
            }
        });
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
                        String id=object.getString("id");
                        String picture = object.getString("picture");
                        course.setTitle(title);
                        course.setPicture(picture);
                        course.setId(id);
                        data.add(course);
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