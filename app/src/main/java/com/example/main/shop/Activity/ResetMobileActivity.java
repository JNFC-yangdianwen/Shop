package com.example.main.shop.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.main.shop.Constans.User;
import com.example.main.shop.HttpUtils.NetClient;
import com.example.main.shop.R;
import com.example.main.shop.Utils.TimeCount;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.RequestBody;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_mobile);
        ButterKnife.bind(this);
    }

    //获取验证码
    @OnClick(R.id.tv_getCode)
    public void getCode() {
        mOriginNumber = originNumber.getText().toString().trim();
        mCode = identNumber.getText().toString().trim();
        mNewNumber = newNumber.getText().toString().trim();
        Login.type = 3;
        TimeCount timeCount = new TimeCount(tvCode, 6000, 1000);
        //开始倒计时
        timeCount.start();
        User user = new User(mNewNumber, Login.type);
        Call<RequestBody> code = NetClient.getInstance().getCode(user);
        code.enqueue(new Callback<RequestBody>() {
            @Override
            public void onResponse(Call<RequestBody> call, Response<RequestBody> response) {
                int code1 = response.code();
                if (code1 == 101) {
                    Toast.makeText(ResetMobileActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (code1 == 102) {
                    Toast.makeText(ResetMobileActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (code1 == 103) {
                    Toast.makeText(ResetMobileActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (code1 == 104) {
                    Toast.makeText(ResetMobileActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (code1 == 105) {
                    Toast.makeText(ResetMobileActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call<RequestBody> call, Throwable t) {

            }
        });
    }
    //点击保存时
    @OnClick(R.id.tv_save)
    public void saveNewNumber(){
        
    }
}
