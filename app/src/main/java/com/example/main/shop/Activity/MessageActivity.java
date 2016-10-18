package com.example.main.shop.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.main.shop.R;


//具体的聊天窗口页面
public class MessageActivity extends AppCompatActivity {
    private static final String TAG = "MessageActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rc_fr_conversation);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
