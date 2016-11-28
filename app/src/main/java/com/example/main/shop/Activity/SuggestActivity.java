package com.example.main.shop.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

/**
 * 意见反馈页面
 */

public class SuggestActivity extends AppCompatActivity {
@Bind(R.id.et_input)EditText input;
    private ActivityUtils activityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest);
        ButterKnife.bind(this);
        activityUtils = new ActivityUtils(this);
    }

    @OnClick({R.id.iv_back,R.id.tv_sure})
    public void action(View v){
        switch (v.getId()){
            case R.id.iv_back :  //返回按键
                finish();
            break;
            case R.id.tv_sure :  //确认
                String uid = User1.getInstance().getUid();
                String content = input.getText().toString();
                Request suggest = MyRequest.getInstance().suggest(uid, content);
                Call call = NetOkHttp.getInstance().getCall(suggest);
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
                         final   String msg = jsonObject.getString("msg");
                            if (code.equals("101")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        activityUtils.showToast(msg);
                                        finish();
                                    }
                                });
                                return;
                            }    if (code.equals("102")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        activityUtils.showToast(msg);
                                    }
                                });
                                return;
                            }    if (code.equals("103")) {
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
                break;
        }

    }
}
