package com.example.main.shop;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.main.shop.Fragment.ChatFragment;
import com.example.main.shop.Fragment.FriendFragment;
import com.example.main.shop.Fragment.MyFragment;
import com.example.main.shop.Fragment.WalletFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

//主界面使用frament
public class MainActivity extends AppCompatActivity {
    //Appkey:17bfcb6cd2ea8
    private static final String TAG = "MainActivity";
    @Bind(R.id.iv_frd) ImageView mIvFriend;
    @Bind(R.id.iv_wallet) ImageView mIvWallet;
    @Bind(R.id.iv_mss) ImageView mIvMss;
    @Bind(R.id.iv_my) ImageView mIvMy;
    @Bind(R.id.tv_frd)TextView mTvFriend;
    @Bind(R.id.tv_mss)TextView mTvMss;
    @Bind(R.id.tv_wallet)TextView mTvWallet;
    @Bind(R.id.tv_my)TextView mTvMy;
    private FriendFragment mFriendFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        String token="3A3QI7k6o8AV7jwGcC0VrLO0Zp8veqjrL783P9LaIfQgvbReLntYP/ZB7tLtpxkTzC7DWbCQ9/1F6Xqv4dp/1A==";

        mFriendFragment = new FriendFragment();
        replaceFragment(mFriendFragment);
    }
    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }


    @OnClick({R.id.iv_frd, R.id.iv_mss, R.id.iv_wallet, R.id.iv_my})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_frd:
                //tag图片的切换
                mIvFriend.setImageResource(R.drawable.shouye1);
                mIvMss.setImageResource(R.drawable.shouye2_);
                mIvWallet.setImageResource(R.drawable.shouye3_);
                mIvMy.setImageResource(R.drawable.shouye4_);
                //字体颜色的变化
                mTvFriend.setTextColor(Color.parseColor("#32acd4"));
                mTvMss.setTextColor(0x8A000000);//灰色
                mTvWallet.setTextColor(0x8A000000);
                mTvMy.setTextColor(0x8A000000);
                replaceFragment(mFriendFragment);
                break;
            case R.id.iv_mss:
                //加载消息的fragment
                mIvFriend.setImageResource(R.drawable.shouye1_);
                mIvMss.setImageResource(R.drawable.shouye2);
                mIvWallet.setImageResource(R.drawable.shouye3_);
                mIvMy.setImageResource(R.drawable.shouye4_);
                mTvMss.setTextColor(Color.parseColor("#32acd4"));
                mTvFriend.setTextColor(0x8A000000);
                mTvWallet.setTextColor(0x8A000000);
                mTvMy.setTextColor(0x8A000000);
                ChatFragment chatFragment = new ChatFragment();
                replaceFragment(chatFragment);
                break;
            case R.id.iv_wallet:
                //加载钱包的fragment
                WalletFragment walletFragment=new WalletFragment();
                replaceFragment(walletFragment);
                mIvFriend.setImageResource(R.drawable.shouye1_);
                mIvMss.setImageResource(R.drawable.shouye2_);
                mIvWallet.setImageResource(R.drawable.shouye3);
                mIvMy.setImageResource(R.drawable.shouye4_);
                mTvWallet.setTextColor(Color.parseColor("#32acd4"));
                mTvFriend.setTextColor(0x8A000000);
                mTvMss.setTextColor(0x8A000000);
                mTvMy.setTextColor(0x8A000000);
                break;
            case R.id.iv_my:
                //加载我的fragment
                MyFragment myFragment=new MyFragment();
                replaceFragment(myFragment);
                mIvFriend.setImageResource(R.drawable.shouye1_);
                mIvMss.setImageResource(R.drawable.shouye2_);
                mIvWallet.setImageResource(R.drawable.shouye3_);
                mIvMy.setImageResource(R.drawable.shouye4);
                mTvMy.setTextColor(Color.parseColor("#32acd4"));
                mTvFriend.setTextColor(0x8A000000);
                mTvMss.setTextColor(0x8A000000);
                mTvWallet.setTextColor(0x8A000000);
                break;
        }
    }
      //切换fragment的方法
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();//获取fragment的管理者
        FragmentTransaction transaction = fragmentManager.beginTransaction();//开启事物
        transaction.add(R.id.vp,fragment);//添加fragment
        transaction.commit();//提交事务
    }
}
