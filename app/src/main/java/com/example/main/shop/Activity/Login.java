package com.example.main.shop.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.main.shop.Constans.Result;
import com.example.main.shop.HttpUtils.NetClient;
import com.example.main.shop.MainActivity;
import com.example.main.shop.R;
import com.example.main.shop.Utils.ActivityUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//登陆界面
public class Login extends AppCompatActivity {

    @Bind(R.id.iv_login)ImageView mImageView;
    @Bind(R.id.et_user)EditText userName;
    @Bind(R.id.et_psw)EditText psw;
    private String regs="^[1][3578][0-9]{9}";
    private ActivityUtils mUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mUtils = new ActivityUtils();
    }
   @OnClick({R.id.iv_login})
    public void login(){
       String mobile = userName.getText().toString().trim();
       String passWord = psw.getText().toString().trim();
       Map<String,String> map=new HashMap<>();
       map.put("mobile",mobile);
       map.put("pwd",passWord);
       //执行登陆
       Call<Result> login = NetClient.getInstance().login(map);
       login.enqueue(new Callback<Result>() {
           @Override
           public void onResponse(Call<Result> call, Response<Result> response) {
               Result result = response.body();
               String msg = result.getMsg();
               int code = result.getCode();
               //登陆成功
               if (code==101){
                   Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                   startActivity(intent);
                   return;
               }if (code==102){
                  mUtils.Toast(getApplicationContext(),msg);
                 return;
               }if (code==103){
                   mUtils.Toast(getApplicationContext(),msg);
                   return;
               }
           }
           @Override
           public void onFailure(Call<Result> call, Throwable t) {
             new Throwable(t.getMessage());
           }
       });
   }
    //点击注册新用户
    @OnClick(R.id.tv_register)
    public void register(){
        mUtils.startActivity(this,RegistActivity.class);
    }
    //点击忘记密码
    @OnClick(R.id.tv_forgetPsw)
    public void forgetPsw(){
        mUtils.startActivity(this,ForgetPswActivity.class);
    }
}