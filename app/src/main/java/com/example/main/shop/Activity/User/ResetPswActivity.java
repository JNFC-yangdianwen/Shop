package com.example.main.shop.Activity.User;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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
    @Bind(R.id.tv_title)TextView title;//设置标题
    private ActivityUtils activityUtils;
    private String phoneNumber;


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
        phoneNumber = number.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNumber)) {
            activityUtils.showToast("请填写正确的手机号");
            return;
        }
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
                int code = result.getCode();
                String msg = result.getMsg();
                if (code==101) {
                    //获取成功
                    activityUtils.showToast(msg);
                    return;
                }  if (code==102) {
                    activityUtils.showToast(msg);
                    return;
                }  if (code==103) {
                    activityUtils.showToast(msg);
                    return;
                }if (code==104) {
                    activityUtils.showToast(msg);
                    return;
                }if (code==105) {
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
    //确认修改密码
    @OnClick(R.id.ll_sure)
    public void resetPsw(){
        String pwd = psw.getText().toString().trim();
        Map<String ,String> map=new HashMap<>();
        map.put("mobile",phoneNumber);
        map.put("pwd",pwd);
        Call<Result> resultCall = NetClient.getInstance().forgetPsw(map);
        resultCall.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();
                String msg = result.getMsg();
                int code = result.getCode();
                if (code==101) {
                   activityUtils.showToast(msg);
                    return;
                }if (code==102) {
                   activityUtils.showToast(msg);
                    return;
                }if (code==103) {
                   activityUtils.showToast(msg);
                    return;
                }if (code==104) {
                   activityUtils.showToast(msg);
                    return;
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }
    @OnClick(R.id.iv_back)
    public void back(){
        finish();
    }

}
