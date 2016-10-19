package com.example.main.shop.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.main.shop.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 用户个人信息的界面
 */
public class UserInfoActivity extends AppCompatActivity {
    @Bind(R.id.ll_name)LinearLayout userName;//昵称
    @Bind(R.id.ll_like)LinearLayout like;//爱好
    @Bind(R.id.ll_sex)LinearLayout sex;//性别
    @Bind(R.id.ll_cash)LinearLayout cash;//提现账户
    @Bind(R.id.tv_name)TextView tvName;
    @Bind(R.id.tv_like)TextView tvLike;
    @Bind(R.id.tv_sex)TextView tvSex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
    }
}
