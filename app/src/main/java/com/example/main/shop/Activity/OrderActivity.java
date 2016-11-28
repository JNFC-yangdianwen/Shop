package com.example.main.shop.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.main.shop.Fragment.OrderFragment;
import com.example.main.shop.Fragment.TravelOrder;
import com.example.main.shop.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

//我的订单页面
public class OrderActivity extends AppCompatActivity {
@Bind(R.id.tv_travel)TextView travel;
    @Bind(R.id.tv_course)TextView course;
    public static  String type ;//默认设置为2，显示旅游的订单

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.tv_travel,R.id.tv_course,R.id.iv_back})
    public void Action(View v){
        switch (v.getId()){
            case R.id.tv_travel:
               //加载我的订单旅游订单的fragment
                type="2";
                OrderFragment travelOrder=new OrderFragment();
                replace(travelOrder);
                travel.setTextColor(Color.parseColor("#32acd4"));
                course.setTextColor(0x8A000000);
                break;
            case R.id.tv_course:
                type="1";
                TravelOrder travelOrder1=new TravelOrder();
                replace(travelOrder1);
               //加载我的订单课程订单fragment
                course.setTextColor(Color.parseColor("#32acd4"));
                travel.setTextColor(0x8A000000);
                break;
            case R.id.iv_back:
               //返回
                type="2";
                finish();
                break;
        }
    }

    private void replace(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fl,fragment);
        transaction.commit();
    }
}
