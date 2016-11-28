package com.example.main.shop.Activity.Wallet;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

//旅游详情
public class TravelInfoActivity extends AppCompatActivity {
    private static final String TAG = "TravelInfoActivity";
@Bind(R.id.iv_travel)ImageView image;
    @Bind(R.id.tv_title)TextView tvTile;
    @Bind(R.id.tv_mobile)TextView tvMobile;
    @Bind(R.id.tv_adress)TextView tvAdress;
    @Bind(R.id.tv_type)TextView type;
    @Bind(R.id.tv_intro)TextView tvIntro;
    @Bind(R.id.tv_Novip)TextView tvNoVIP;
    @Bind(R.id.tv_vip)TextView tvVip;
    private JSONObject info;
    private ActivityUtils activityUtils;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_info);
        ButterKnife.bind(this);
        activityUtils = new ActivityUtils(this);
        progressDialog = new ProgressDialog(this);
        getData();
    }

    private void getData() {
        Request request = MyRequest.getInstance().travelInfo(TravelActivity.tid);
        Call call = NetOkHttp.getInstance().getCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                progressDialog.dismiss();
                String string = response.body().string();
                try {
                    JSONObject jsonObject=new JSONObject(string);
                    info = (JSONObject) jsonObject.get("info");
                    final String title = info.getString("title");
                    final String picture = info.getString("picture");
                    final String adress = info.getString("adress");
                    final String intro = info.getString("intro");
                    final String mobile = info.getString("mobile");
                    final String money = info.getString("money");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ImageLoader.getInstance().displayImage(NetClient.IMAGE_URL+picture,image);
                            tvTile.setText(title);
                            tvMobile.setText("联系电话："+mobile);
                            tvAdress.setText("公司地址："+adress);
                            tvIntro.setText("套餐介绍："+intro);
                            tvNoVIP.setText(" ¥"+money);
                            tvVip.setText(" ¥"+money);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
@OnClick({R.id.iv_back,R.id.tv_buy})
    public void action(View v){
    switch (v.getId()){
        case R.id.iv_back:
            finish();
        break;
        case R.id.tv_buy:
            String uid = User1.getInstance().getUid();
            Request request = MyRequest.getInstance().buyTravel(uid, TravelActivity.tid);
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
                        }   if (code.equals("102")) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    activityUtils.showToast(msg);
                                }
                            });
                           return;
                        }   if (code.equals("103")) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    activityUtils.showToast(msg);
                                }
                            });
                           return;
                        }  if (code.equals("104")) {
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
}
}
