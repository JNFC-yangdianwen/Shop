package com.example.main.shop.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.main.shop.Constans.UserInfo;
import com.example.main.shop.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class UserNameActivity extends AppCompatActivity {
@Bind(R.id.et_name)EditText etName;
    public static String mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_name);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.iv_back,R.id.ll_save})
   public void back(View v){
        switch(v.getId()){
            //返回
            case R.id.iv_back:
               finish();
                break;
            //保存昵称
            case R.id.ll_save:
                mName = etName.getText().toString().trim();
                UserInfo userInfo=new UserInfo();
                userInfo.setUser_name(mName);
                finish();
                break;
        }
    }
}

