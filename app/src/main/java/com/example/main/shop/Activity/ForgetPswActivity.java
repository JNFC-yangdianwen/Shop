package com.example.main.shop.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.main.shop.HttpUtils.NetClient;
import com.example.main.shop.R;
import com.example.main.shop.Utils.TimeCount;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.RequestBody;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_psw);
        ButterKnife.bind(this);
        mTelNumber = phoneNumber.getText().toString().trim();
        mIdentNum = identNumber.getText().toString().trim();
        mPassWord = psw.getText().toString().trim();
    }

    //获取验证码
    @OnClick(R.id.tv_time)
    public void getIdentNumber() {
        TimeCount timeCount = new TimeCount(mTextView, 6000, 1000);
        timeCount.start();
    }

    //确认密码
    @OnClick(R.id.l_sure)
    public void surePsw() {
        if (!(TextUtils.isEmpty(mTelNumber) && TextUtils.isEmpty(mIdentNum) && TextUtils.isEmpty(mPassWord))) {
            Map<String, String> map = new HashMap();
            map.put("mobile", mTelNumber);
            map.put("code", mIdentNum);
            map.put("pwd", mPassWord);
            Call<RequestBody> call = NetClient.getInstance().forgetPsw(map);
            call.enqueue(new Callback<RequestBody>() {
                @Override
                public void onResponse(Call<RequestBody> call, Response<RequestBody> response) {
                    int code = response.code();
                    String message = response.message();
                    //修改密码成功
                    if (code == 101) {
                        Toast.makeText(ForgetPswActivity.this, message, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);
                        return;
                    }
                    if (code == 102) {
                        Toast.makeText(ForgetPswActivity.this, message, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (code == 103) {
                        Toast.makeText(ForgetPswActivity.this, message, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (code == 104) {
                        Toast.makeText(ForgetPswActivity.this, message, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (code == 105) {
                        Toast.makeText(ForgetPswActivity.this, message, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (code == 106) {
                        Toast.makeText(ForgetPswActivity.this, message, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (code == 107) {
                        Toast.makeText(ForgetPswActivity.this, message, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (code == 108) {
                        Toast.makeText(ForgetPswActivity.this, message, Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                @Override
                public void onFailure(Call<RequestBody> call, Throwable t) {

                }
            });

        }
    }
}
