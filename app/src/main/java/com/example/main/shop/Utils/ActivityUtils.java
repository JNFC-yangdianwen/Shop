package com.example.main.shop.Utils;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/10/17 0017.
 */

public class ActivityUtils extends AppCompatActivity{
    //Toast方法
    public void Toast(Context context,String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
    //跳转页面
    public void startActivity(Context context, Class<?> activity){
        Intent intent=new Intent(context.getApplicationContext(),activity);
        startActivity(intent);
    }
}
