package com.example.main.shop;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Bind(R.id.iv_frd)ImageView mImageView_frd ;
    @Bind(R.id.iv_mss)ImageView mImageView_mss;
    @Bind(R.id.iv_wallet)ImageView mImageView_vly;
    @Bind(R.id.iv_my)ImageView mImageView_my;
    @Bind(R.id.toolbar)Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        ActionBar toolbar = getSupportActionBar();
        toolbar.setTitle("人人商");
        FriendFragment friendFragment=new FriendFragment();
        replaceFragment(friendFragment);
    }
    @OnClick({R.id.iv_frd,R.id.iv_mss,R.id.iv_wallet,R.id.iv_my})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_frd:
                break;
            case R.id.iv_mss:
                //加载消息的fragment
                MssFragment mssFragment=new MssFragment();
                replaceFragment(mssFragment);
                break;
            case R.id.iv_wallet:
                break;
            case R.id.iv_my:
                break;
        }
    }
    //切换fragment的方法
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fl,fragment);
        transaction.commit();
    }
}
