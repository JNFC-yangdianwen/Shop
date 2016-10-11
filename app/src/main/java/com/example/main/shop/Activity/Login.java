package com.example.main.shop.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.main.shop.MainActivity;
import com.example.main.shop.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

//登陆界面
public class Login extends AppCompatActivity {
    @Bind(R.id.iv_login)ImageView mImageView;
    @Bind(R.id.et_user)EditText userName;
    @Bind(R.id.et_psw)EditText psw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }
   @OnClick({R.id.iv_login})
    public void onClick(){
       String user = userName.getText().toString().trim();
       String passWord = psw.getText().toString().trim();
       if (TextUtils.isEmpty(user)){
           Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
           return;
       }
       if (TextUtils.isEmpty(passWord)){
           Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
           return;
       }
       //以上都符合去数据空中匹配用户数据
       //获取用户令牌token
       Intent intent=new Intent(this,MainActivity.class);
       startActivity(intent);
   }
}