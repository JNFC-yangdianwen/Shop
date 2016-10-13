package com.example.main.shop.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.main.shop.Constans.User;
import com.example.main.shop.HttpUtils.NetClient;
import com.example.main.shop.R;
import com.example.main.shop.Utils.TimeCount;
import com.mob.commons.SMSSDK;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//注册界面
public class RegistActivity extends AppCompatActivity {
    @Bind(R.id.et_phoneNumber)
    EditText userPhone;//手机号
    @Bind(R.id.et_inviteNum)
    EditText inviteNum;//邀请码
    @Bind(R.id.et_ident)
    EditText identNum;//验证码
    @Bind(R.id.tv_time)
    TextView mTextView;//获取验证码
    @Bind(R.id.et_psw)
    EditText psw;//密码
    private static final String TAG = "RegistActivity";
    //短信验证的Appkey：17cca60187574
    private String Appkey = "17cca60187574";
    private String mPhoneNumber;
    private String mInviteNumber;
    private String mIdent;
    private String mPswNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist);
        ButterKnife.bind(this);
        //手机号
        mPhoneNumber = userPhone.getText().toString().trim();
        //邀请码
        mInviteNumber = inviteNum.getText().toString().trim();
        //验证码
        mIdent = identNum.getText().toString().trim();
        mPswNum = psw.getText().toString().trim();
        SMSSDK.setAppKey(Appkey);
    }

    //获取短信验证码
    @OnClick(R.id.tv_time)
    public void getSMSS() {
        //倒计时
        TimeCount timeCount = new TimeCount(mTextView, 6000, 1000);
        timeCount.start();
        User user=new User(mPhoneNumber,Login.type);
        Call<RequestBody> call = NetClient.getInstance().getCode(user);
        call.enqueue(new Callback<RequestBody>() {
            @Override
            public void onResponse(Call<RequestBody> call, Response<RequestBody> response) {
                int code = response.code();
                String message = response.message();
                if (code == 101) {
                    Toast.makeText(RegistActivity.this, message, Toast.LENGTH_SHORT).show();
                    return;
                }
                if (code == 102) {
                    Toast.makeText(RegistActivity.this, message, Toast.LENGTH_SHORT).show();
                    return;
                }
                if (code == 103) {
                    Toast.makeText(RegistActivity.this, message, Toast.LENGTH_SHORT).show();
                    return;
                }
                if (code == 104) {
                    Toast.makeText(RegistActivity.this, message, Toast.LENGTH_SHORT).show();
                    return;
                }
                if (code == 105) {
                    Toast.makeText(RegistActivity.this, message, Toast.LENGTH_SHORT).show();
                    return;
                }

            }

            @Override
            public void onFailure(Call<RequestBody> call, Throwable t) {

            }
        });
    }

    @OnClick(R.id.l_sure)
    public void sure() {

        //手机号，验证码，密码都不能为空，
        if ((TextUtils.isEmpty(mPhoneNumber) && TextUtils.isEmpty(mIdent) && mPswNum.length() >= 6)) {
            //注册
            User user=new User(mPhoneNumber,mPswNum,mIdent,mInviteNumber);
            Call<RequestBody> register = NetClient.getInstance().register(user);
            register.enqueue(new Callback<RequestBody>() {
                @Override
                public void onResponse(Call<RequestBody> call, Response<RequestBody> response) {
                    RequestBody body = response.body();
                    Log.d(TAG, "onResponse: " + body);
                }

                @Override
                public void onFailure(Call<RequestBody> call, Throwable t) {

                }
            });
        } else {
            if (TextUtils.isEmpty(mPhoneNumber)) {
                Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(mIdent)) {
                Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            if (mPswNum.length() < 6) {
                Toast.makeText(this, "密码长度不能小于6", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

    public void getSmscode(String number, int type) {
    }
}
