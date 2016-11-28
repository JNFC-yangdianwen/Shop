package com.example.main.shop.Activity.PayActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;

import com.example.main.shop.Constans.User1;
import com.example.main.shop.HttpUtils.MyRequest;
import com.example.main.shop.HttpUtils.NetOkHttp;
import com.example.main.shop.R;
import com.example.main.shop.Utils.ActivityUtils;

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

//微信账号页面
public class WeachatPayActivity extends AppCompatActivity {
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.et_wechatNum)
    EditText etWechatNum;
    private ActivityUtils activityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weachat_pay);
        ButterKnife.bind(this);
        activityUtils = new ActivityUtils(this);
    }
    //确认
    @OnClick(R.id.tv_sure)
    public void sure() {
        String name = etName.getText().toString().trim();
        String wechatNum = etWechatNum.getText().toString().trim();
        if (!(TextUtils.isEmpty(name)&&TextUtils.isEmpty(wechatNum))) {
            String uid = User1.getInstance().getUid();
            Request request = MyRequest.getInstance().cashAccount(uid, CashActivity.payType, name, wechatNum, "00000");
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
                      final  String msg = jsonObject.getString("msg");

                        if (code.equals("101")) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    activityUtils.showToast(msg);
                                }
                            });

                            return;
                        }
                        if (code.equals("102")) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    activityUtils.showToast(msg);
                                }
                            });

                            return;
                        } if (code.equals("103")) {
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
    //返回按键
    @OnClick(R.id.iv_back)
    public void back(){
        finish();
    }
}
