package com.example.main.shop.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


//具体的聊天窗口页面
public class MessageActivity extends AppCompatActivity {
    private static final String TAG = "MessageActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.rc_fr_conversation);
       //继承的是ActionBarActivity，直接调用 自带的 Actionbar，下面是Actionbar 的配置，如果不用可忽略…
        getSupportActionBar().setTitle("聊天");
//        getSupportActionBar().setLogo(R.drawable.de_bar_logo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.de_actionbar_back);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
