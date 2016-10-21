package com.example.main.shop.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

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

/**
 * 修改手机号的页面
 */

public class ResetMobileActivity extends AppCompatActivity {
    @Bind(R.id.et_originNumber)
    EditText originNumber;//原来的手机号
    @Bind(R.id.et_identNumber)
    EditText identNumber;//验证码
    @Bind(R.id.et_newNumber)
    EditText newNumber;//新手机号
    @Bind(R.id.tv_getCode)
    TextView tvCode;
    private String mOriginNumber;
    private String mCode;
    private String mNewNumber;
    private ActivityUtils mUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_mobile);
        ButterKnife.bind(this);
        mUtils = new ActivityUtils(this);

    }

    //获取验证码
    @OnClick(R.id.tv_getCode)
    public void getCode() {
        mOriginNumber = originNumber.getText().toString().trim();//原来的手机号
        mCode = identNumber.getText().toString().trim();//验证码
        mNewNumber = newNumber.getText().toString().trim();//新手机号
        TimeCount timeCount = new TimeCount(tvCode, 6000, 1000);
        //开始倒计时
        timeCount.start();
        Map<String, String> map = new HashMap<>();
        map.put("mobile", mOriginNumber);
        map.put("type", String.valueOf(3));
        Call<Result> code = NetClient.getInstance().getCode(map);
        code.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();
                int code1 = result.getCode();
                String msg = result.getMsg();
                if (code1 == 101) {
                    mUtils.showToast(msg);
                    return;
                }
                if (code1 == 102) {
                    mUtils.showToast(msg);
                    return;
                }
                if (code1 == 103) {
                    mUtils.showToast(msg);
                    return;
                }
                if (code1 == 104) {
                    mUtils.showToast(msg);
                    return;
                }
                if (code1 == 105) {
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

    //点击保存时
    @OnClick(R.id.tv_save)
    public void saveNewNumber() {
        Map<String, String> map = new HashMap<>();
        UserInfo userInfo=new UserInfo();
        int uid = userInfo.getUid();
        map.put("uid", String.valueOf(uid));
        map.put("mobile", mOriginNumber);
        map.put("new_mobile", mNewNumber);
        map.put("type", String.valueOf(3));
        Call<Result> resetMobileCall = NetClient.getInstance().resetMobile(map);
        resetMobileCall.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result1 = response.body();
                int code = result1.getCode();
                String msg = result1.getMsg();
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
                if (code == 106) {
                    mUtils.showToast(msg);
                    return;
                }
                if (code == 107) {
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
    // 返回按键
    @OnClick(R.id.iv_back)
    public void back(){
        finish();
    }
}
