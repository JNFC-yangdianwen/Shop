package com.example.main.shop.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.main.shop.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *明细页面
 */

public class DetailActivity extends AppCompatActivity {
@Bind(R.id.tv_time)TextView payTime;
@Bind(R.id.tv_time1)TextView travelTime;
@Bind(R.id.tv_time2)TextView subTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.iv_back)
    public void back(){
        finish();
    }
}
