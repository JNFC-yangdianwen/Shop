package com.example.main.shop.Activity.User;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.example.main.shop.Constans.Result;
import com.example.main.shop.HttpUtils.NetClient;
import com.example.main.shop.R;
import com.example.main.shop.Utils.ActivityUtils;
import com.example.main.shop.Utils.TimeCount;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//修改密码
public class ResetPswActivity extends AppCompatActivity {
@Bind(R.id.et_phoneNumber)EditText number;//手机号
    @Bind(R.id.et_ident)EditText ident;//验证码
    @Bind(R.id.et_psw)EditText psw;//密码
    @Bind(R.id.tv_time)TextView textView;
    private ActivityUtils activityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_psw);
        ButterKnife.bind(this);
        activityUtils = new ActivityUtils(this);
    }
    //获取验证码
    @OnClick(R.id.tv_time)
    public void getIdent(){
        String phoneNumber = number.getText().toString().trim();
        TimeCount timeCount=new TimeCount(textView,6*10*1000,1000);
        timeCount.start();
        Map<String,String> map=new HashMap<>();
        map.put("mobile",phoneNumber);
        //修改密码为2
        map.put("type", String.valueOf(2));
        Call<Result> code = NetClient.getInstance().getCode(map);
        code.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();
                int code1 = result.getCode();
                String msg = result.getMsg();
                if (code1 == 101) {
                    //获取成功
                    activityUtils.showToast(msg);
                    return;
                }  if (code1 == 102) {
                    activityUtils.showToast(msg);
                    return;
                }  if (code1 == 103) {
                    activityUtils.showToast(msg);
                    return;
                }if (code1 == 104) {
                    activityUtils.showToast(msg);
                    return;
                }if (code1 == 105) {
                    activityUtils.showToast(msg);
                    return;
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                new Throwable(t.getMessage());
            }
        });
    }
    @OnClick(R.id.iv_back)
    public void back(){
        finish();
    }
}
