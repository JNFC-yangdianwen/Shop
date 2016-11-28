package com.example.main.shop.Activity.Wallet;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.main.shop.Constans.User1;
import com.example.main.shop.HttpUtils.MyRequest;
import com.example.main.shop.HttpUtils.NetClient;
import com.example.main.shop.HttpUtils.NetOkHttp;
import com.example.main.shop.R;
import com.example.main.shop.Utils.ActivityUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

//课程详情页
public class CourseDetail extends AppCompatActivity {
    private static final String TAG = "CourseDetail";
    @Bind(R.id.iv_course)ImageView imageView;
    @Bind(R.id.tv_teacher)TextView tvTeacher;
    @Bind(R.id.tv_time)TextView tvTime;
    @Bind(R.id.tv_adress)TextView tvAdress;
    @Bind(R.id.tv_intro)TextView tvIntro;
    @Bind(R.id.tv_Novip)TextView tvNoVip;
    @Bind(R.id.tv_vip)TextView tvVip;
    @Bind(R.id.tv_title)TextView tvTitle;
    @Bind(R.id.tv_mobile)TextView tvMobile;
    private ActivityUtils activityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courese_detail);
        ButterKnife.bind(this);
        activityUtils = new ActivityUtils(this);
        //获取课程的详情
        Request request = MyRequest.getInstance().getCourseDetail(CourseActivity.courseId);
        Call call = NetOkHttp.getInstance().getCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                try {
                    Log.d(TAG, "onResponse: ......................"+string);
                    JSONObject jsonObject=new JSONObject(string);
                    JSONObject  info = (JSONObject) jsonObject.get("info");
                    final String picture = info.getString("picture");
                    final String title = info.getString("title");
                    final String money = info.getString("money");
                    final String time = info.getString("time");
                    final String teacher = info.getString("teacher");
                    final String intro = info.getString("intro");
                    final String mobile = info.getString("mobile");
                    final String address = info.getString("adress");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.d(TAG, "run: ................................."+teacher+intro+title+address+mobile);
                            ImageLoader.getInstance().displayImage(NetClient.IMAGE_URL+picture,imageView);
                            tvTeacher.setText("授课老师："+teacher);
                            tvIntro.setText("课程介绍："+intro);
                            tvAdress.setText("授课地址："+address);
                            tvMobile.setText("联系电话："+mobile);
                            tvTime.setText("授课时间："+time);
                            tvTitle.setText(title);
                            tvNoVip.setText(" ¥"+money);
                            tvNoVip.setTextColor(Color.parseColor("#de3434"));
                            tvVip.setText(" ¥"+money);
                            tvVip.setTextColor(Color.parseColor("#de3434"));
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
    //购买课程
    @OnClick(R.id.tv_buy)
    public void buy(){
        String uid = User1.getInstance().getUid();
        Request request = MyRequest.getInstance().buyCourse(uid, CourseActivity.courseId);
        Call call = NetOkHttp.getInstance().getCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                try {
                    JSONObject jsonObject=new JSONObject(string);
                    String code = jsonObject.getString("code");
                   final String msg = jsonObject.getString("msg");
                    if (code.equals("101")) {
                         runOnUiThread(new Runnable() {
                             @Override
                             public void run() {
                                 activityUtils.showToast(msg);
                             }
                         });

                        return;
                    } if (code.equals("102")) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                activityUtils.showToast(msg);
                            }
                        });                        return;
                    } if (code.equals("103")) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                activityUtils.showToast(msg);
                            }
                        });                        return;
                    } if (code.equals("104")) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                activityUtils.showToast(msg);
                            }
                        });
                        return;
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
