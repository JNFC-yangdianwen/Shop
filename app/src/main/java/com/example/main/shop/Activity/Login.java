package com.example.main.shop.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.main.shop.HttpUtils.NetClient;
import com.example.main.shop.MainActivity;
import com.example.main.shop.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//登陆界面
public class Login extends AppCompatActivity {
    @Bind(R.id.iv_login)ImageView mImageView;
    @Bind(R.id.et_user)EditText userName;
    @Bind(R.id.et_psw)EditText psw;
    private String regs="^[1][3578][0-9]{9}";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }
   @OnClick({R.id.iv_login})
    public void login(){
       String user = userName.getText().toString().trim();
       String passWord = psw.getText().toString().trim();
       if (TextUtils.isEmpty(user)){
           Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
           return;
       }
       if (passWord.length()<6){
           Toast.makeText(this, "密码长度不能小于6", Toast.LENGTH_SHORT).show();
           return;
       }
       if (!user.matches(regs)){
           Toast.makeText(this, "手机号不正确", Toast.LENGTH_SHORT).show();
           return;
       }
       //以上都符合去数据空中匹配用户数据
       Map<String,String> map=new HashMap<>();
       map.put("mobile",user);
       map.put("pwd",passWord);
       Call<RequestBody> login = NetClient.getInstance().getApi().login(map);
       login.enqueue(new Callback<RequestBody>() {
           @Override
           public void onResponse(Call<RequestBody> call, Response<RequestBody> response) {
               int code = response.code();
               //登陆成功
               if (code==101){
                   Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                   startActivity(intent);
                   return;
               }if (code==102){
                 return;
               }if (code==103){
                   Toast.makeText(Login.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                   return;
               }
           }
           @Override
           public void onFailure(Call<RequestBody> call, Throwable t) {

           }
       });
   }
}