package com.example.main.shop;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.main.shop.Fragment.ChatFragment;
import com.example.main.shop.Fragment.FriendFragment;
import com.example.main.shop.Fragment.MyFragment;
import com.example.main.shop.Fragment.WalletFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

//主界面使用frament,开启融云连接服务
public class MainActivity extends FragmentActivity {

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
    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;
    private ChatFragment chatFragment;
    private WalletFragment walletFragment;
    private MyFragment myFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        FragmentTransaction transaction = getFragmentTransaction();
        //加载朋友圈的fragment
        mFriendFragment=new FriendFragment();
        transaction.add(R.id.fl,mFriendFragment);
        transaction.commit();
        String token="3A3QI7k6o8AV7jwGcC0VrLO0Zp8veqjrL783P9LaIfQgvbReLntYP/ZB7tLtpxkTzC7DWbCQ9/1F6Xqv4dp/1A==";
        //连接融云
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                //token失效，重新获取
            }

            @Override
            public void onSuccess(String s) {
                Log.d(TAG, "onSuccess: 。。。。。。。。。融云连接成功");

            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }


    @OnClick({R.id.iv_frd, R.id.iv_mss, R.id.iv_wallet, R.id.iv_my})
    public void onClick(View v) {
        FragmentTransaction transaction = getFragmentTransaction();
        hideFragment(transaction);
        switch (v.getId()) {
            case R.id.iv_frd:
                if (mFriendFragment == null) {
                    mFriendFragment=new FriendFragment();
                    transaction.add(R.id.fl,mFriendFragment);
                    transaction.commit();
                    Log.d(TAG, "onClick: ......................"+mFriendFragment);

                }else {
                    transaction.show(mFriendFragment);
                    transaction.commit();
                }
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

                break;
            case R.id.iv_mss:
                //加载聊天的fragment
                if (chatFragment == null) {
                    chatFragment=new ChatFragment();
                    transaction.add(R.id.fl,chatFragment);
                    transaction.commit();
                }else {
                    transaction.show(chatFragment);
                    transaction.commit();
                }
                mIvFriend.setImageResource(R.drawable.shouye1_);
                mIvMss.setImageResource(R.drawable.shouye2);
                mIvWallet.setImageResource(R.drawable.shouye3_);
                mIvMy.setImageResource(R.drawable.shouye4_);
                mTvMss.setTextColor(Color.parseColor("#32acd4"));
                mTvFriend.setTextColor(0x8A000000);
                mTvWallet.setTextColor(0x8A000000);
                mTvMy.setTextColor(0x8A000000);
                break;
            case R.id.iv_wallet:
                //加载钱包的fragment
                if (walletFragment == null) {
                    walletFragment=new WalletFragment();
                    transaction.add(R.id.fl,walletFragment);
                    transaction.commit();
                }else {
                    transaction.show(walletFragment);
                    transaction.commit();
                }
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
                if (myFragment == null) {
                    myFragment=new MyFragment();
                    transaction.add(R.id.fl,myFragment);
                    transaction.commit();
                }else {
                    transaction.show(myFragment);
                    transaction.commit();
                }
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

    //获取事务
    private FragmentTransaction getFragmentTransaction() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        return fragmentManager.beginTransaction();
    }


    //隐藏fragment
    private void hideFragment(FragmentTransaction transaction) {
        if (mFriendFragment != null) {
            transaction.hide(mFriendFragment);
        }  if (myFragment != null) {
            transaction.hide(myFragment);
        }  if (walletFragment != null) {
            transaction.hide(walletFragment);
        }  if (chatFragment != null) {
            transaction.hide(chatFragment);
        }
    }
    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }
}

