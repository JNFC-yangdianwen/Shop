package com.example.main.shop.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.example.main.shop.Constans.RegistResult;
import com.example.main.shop.Constans.Result;
import com.example.main.shop.Constans.UserInfo;
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
    private ActivityUtils mUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist);
        ButterKnife.bind(this);
        mUtils = new ActivityUtils(this);
    }

    //获取短信验证码
    @OnClick(R.id.tv_time)
    public void getSMSS() {
        //手机号
        mPhoneNumber = userPhone.getText().toString().trim();
        if (TextUtils.isEmpty(mPhoneNumber)) {
            mUtils.showToast("请输入手机号");
            return;
        }
        //倒计时
        TimeCount timeCount = new TimeCount(mTextView, 60000, 1000);
        timeCount.start();
        Map<String, String> map = new HashMap<>();
        map.put("mobile", mPhoneNumber);
        map.put("type", String.valueOf(1));
        Call<Result> call = NetClient.getInstance().getCode(map);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();
                int code = result.getCode();
                String msg = result.getMsg();
                if (code == 101) {
                    mUtils.showToast(msg);
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
            }
            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                new Throwable(t.getMessage());
            }
        });
    }

    @OnClick(R.id.l_sure)
    public void sure() {
        mInviteNumber = inviteNum.getText().toString().trim(); //邀请码
        mIdent = identNum.getText().toString().trim();//验证码
        mPswNum = psw.getText().toString().trim();//密码
        //手机号，验证码，密码都不能为空，
        //注册
        Map<String, String> map = new HashMap<>();
        map.put("mobile", mPhoneNumber);
        map.put("code", mIdent);
        map.put("invite_code", mInviteNumber);
        map.put("pwd", mPswNum);
        Call<RegistResult> register = NetClient.getInstance().register(map);
        register.enqueue(new Callback<RegistResult>() {
            @Override
            public void onResponse(Call<RegistResult> call, Response<RegistResult> response) {
                //注册成功
                RegistResult result = response.body();
                int code = result.getCode();
                String msg = result.getMsg();
                //用户id
                int id = result.getId();
                if (code == 101) {
                    mUtils.showToast(msg);
                    //设置用户id
                    UserInfo.getInstance().setUid(id);
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
                if (code == 109) {
                    mUtils.showToast(msg);
                    return;
                }
            }

            @Override
            public void onFailure(Call<RegistResult> call, Throwable t) {
                new Throwable(t.getMessage());
            }
        });
    }
    // 返回按键
    @OnClick(R.id.iv_back)
    public void back(){
        finish();
    }
}
