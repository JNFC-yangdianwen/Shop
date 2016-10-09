package com.example.main.shop.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.main.shop.R;
//引导页面
//用户只有第一次安装打开之后才会展示此页
//加载完成此页之后跳转至登陆界面

public class LeadActivity extends AppCompatActivity {
public boolean isFirst=true;
    private String SHARE_APP_TAG="TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead);
        SharedPreferences setting = getSharedPreferences(SHARE_APP_TAG, 0);
        Boolean user_first = setting.getBoolean("FIRST",true);
        if(user_first){//第一次
            setting.edit().putBoolean("FIRST", false).commit();
            Toast.makeText(LeadActivity.this, "第一次", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(LeadActivity.this, "不是第一次", Toast.LENGTH_LONG).show();
        }
     }
}
