package com.example.main.shop.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.main.shop.R;

import butterknife.OnClick;

/**
 * 意见反馈页面
 */

public class SuggestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest);
    }
    //返回按键
    @OnClick(R.id.iv_back)
    public void back(){
        finish();
    }
}
