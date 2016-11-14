package com.example.main.shop.Activity.Rong;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import com.example.main.shop.R;
//单聊会话列表
public class Conversation extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        getSupportActionBar().setTitle("聊天");
        getSupportActionBar().setLogo(R.drawable.fanhui);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //设置返回按钮
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.fanhui);
    }
    //点击返回按键的操作
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
