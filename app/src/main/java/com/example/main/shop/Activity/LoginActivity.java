package com.example.main.shop.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import com.alipay.sdk.app.PayTask;
import com.example.main.shop.Constans.LoginResult;
import com.example.main.shop.Constans.UserInfo;
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
public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    @Bind(R.id.iv_login)ImageView mImageView;
    @Bind(R.id.et_user)EditText userName;
    @Bind(R.id.et_psw)EditText psw;
    private ActivityUtils mUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mUtils = new ActivityUtils();
        PayTask payTask =new PayTask(this);
        String version = payTask.getVersion();
        Log.d(TAG, "onCreate: "+version);
    }
   @OnClick({R.id.iv_login})
    public void login(){
       String mobile = userName.getText().toString().trim();
       String passWord = psw.getText().toString().trim();
       Map<String,String> map=new HashMap<>();
       map.put("mobile",mobile);
       map.put("pwd",passWord);
       //执行登陆
       Call<LoginResult> login = NetClient.getInstance().login(map);
       login.enqueue(new Callback<LoginResult>() {
           @Override
           public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
               LoginResult result = response.body();
               String msg = result.getMsg();
               int code = result.getCode();
               //登陆成功
               if (code==101){
                   mUtils.Toast(getApplicationContext(),msg);

                   int uid = result.getUid();
                   UserInfo userInfo=new UserInfo();
                   userInfo.setUid(uid);
                   Log.d(TAG, "onResponse: "+uid);
                   int type = result.getType();
                   /**
                    * 登陆之后如果type=0，不需要填写个人信息，
                    * 如果type =1，填写个人信息
                    */
                   if (type == 0) {
                       mUtils.startActivity(LoginActivity.this,MainActivity.class);
                   return;
                   }
                   if (type==1){
                       //填写个人信息
                       mUtils.startActivity(getApplicationContext(),UserInfoActivity.class);
                   }
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
           public void onFailure(Call<LoginResult> call, Throwable t) {
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