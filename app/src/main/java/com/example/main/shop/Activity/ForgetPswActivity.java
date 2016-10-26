package com.example.main.shop.Activity;

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

/**
 * 忘记密码的页面
 */

public class ForgetPswActivity extends AppCompatActivity {
    @Bind(R.id.et_phoneNumber)
    EditText phoneNumber;
    @Bind(R.id.et_ident)
    EditText identNumber;
    @Bind(R.id.et_psw)
    EditText psw;
    @Bind(R.id.tv_time)
    TextView mTextView;
    private String mTelNumber;
    private String mIdentNum;
    private String mPassWord;
    private ActivityUtils mUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_psw);
        ButterKnife.bind(this);
        mUtils = new ActivityUtils(this);
    }

    //获取验证码
    @OnClick(R.id.tv_time)
    public void getIdentNumber() {
        mTelNumber = phoneNumber.getText().toString().trim();
        if (TextUtils.isEmpty(mTelNumber)) {
            mUtils.showToast("请输入手机号");
            return;
        }
        TimeCount timeCount = new TimeCount(mTextView, 60000, 1000);
        timeCount.start();
    }
    //确认密码
    @OnClick(R.id.l_sure)
    public void surePsw() {
        mIdentNum = identNumber.getText().toString().trim();
        mPassWord = psw.getText().toString().trim();
            Map<String, String> map = new HashMap();
            map.put("mobile", mTelNumber);
            map.put("code", mIdentNum);
            map.put("pwd", mPassWord);
            Call<Result> call = NetClient.getInstance().forgetPsw(map);
            call.enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    Result result = response.body();
                    int code = result.getCode();
                    String msg = result.getMsg();
                    //修改密码成功
                    if (code == 101) {
                        mUtils.showToast(msg);
                        mUtils.startActivity(LoginActivity.class);
                        return;
                    }
                    if (code == 102) {
                        mUtils.showToast(msg);
                        return;
                    }
                    if (code == 103) {
                        mUtils.showToast(msg);
                        return;
                    }
                    if (code == 104) {
                        mUtils.showToast(msg);
                        return;
                    }
                    if (code == 105) {
                        mUtils.showToast(msg);
                        return;
                    }
                    if (code == 106) {
                        mUtils.showToast(msg);
                        return;
                    }
                    if (code == 107) {
                        mUtils.showToast(msg);
                        return;
                    }
                    if (code == 108) {
                        mUtils.showToast(msg);
                        return;
                    }
                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                         new Throwable(t.getMessage());
                }
            });

        }
    //返回按键
    @OnClick(R.id.iv_back)
    public void back(){
        finish();
    }
}
