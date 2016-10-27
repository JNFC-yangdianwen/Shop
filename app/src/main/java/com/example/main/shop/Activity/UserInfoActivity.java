package com.example.main.shop.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.main.shop.Constans.LoginResult;
import com.example.main.shop.Constans.Result;
import com.example.main.shop.Constans.UserInfo;
import com.example.main.shop.HttpUtils.NetClient;
import com.example.main.shop.MainActivity;
import com.example.main.shop.R;
import com.example.main.shop.Utils.ActivityUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 用户个人信息的界面
 */
public class UserInfoActivity extends AppCompatActivity {
    @Bind(R.id.tv_name)TextView tvName;
    @Bind(R.id.tv_like)TextView tvLike;
    @Bind(R.id.tv_sex)TextView tvSex;
    @Bind(R.id.tv_mobile)TextView tvMobile;
//    @Bind(R.id.iv_photo)
//    static ImageView ivPhoto;//头像
    private static final String TAG = "UserInfoActivity";
    private ActivityUtils mActivityUtils;
    public static ImageView mView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        mActivityUtils = new ActivityUtils(this);
        mView = (ImageView) findViewById(R.id.iv_photo);
        //手机号
        SharedPreferences sp = getSharedPreferences(LoginActivity.Login, MODE_PRIVATE);
        String moblie = sp.getString(LoginActivity.Mobile, "");
        tvMobile.setText(moblie);
    }

    //设置头像
    @OnClick({R.id.iv_photo,R.id.tv_setPhoto})
    public void setPhoto(){
        mActivityUtils.startActivity(CameraActivity.class);
    }
    //设置昵称
    @OnClick(R.id.rl_name)
    public void setUserNmae(){
        //回传值
        Intent intent = new Intent();
        intent.putExtra("message",1);
        intent.setClass(this, UserNameActivity.class);
        startActivityForResult(intent, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //处理回传的数据
        switch (requestCode){
            case 1000:
                String result_value = data.getStringExtra("result");
                tvName.setText(result_value);
                UserInfo.getInstance().setUser_name(result_value);
                Log.d(TAG, "onActivityResult: 。。。。。。用户名"+result_value);
                break;
            case 1001:
                String like = data.getStringExtra("like");
            tvLike.setText(like);
            UserInfo.getInstance().setLike(like);
            Log.d(TAG, "onActivityResult: ........爱好"+like);
                break;

        }
    }

    //设置性别
    @OnClick(R.id.rl_sex)
    public void setUserSex(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择性别");
        final String[] sex = {"男", "女",};
        //    设置一个单项选择下拉框
        /**
         * 第一个参数指定我们要显示的一组下拉单选框的数据集合
         * 第二个参数代表索引，指定默认哪一个单选框被勾选上，1表示默认'女' 会被勾选上
         * 第三个参数给每一个单选项绑定一个监听器
         */
        builder.setSingleChoiceItems(sex,0, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                if (which ==0) {
                    tvSex.setText("男");
                    UserInfo.getInstance().setSex("男");
                    dialog.dismiss();
                    return;
                }if (which == 1) {
                tvSex.setText("女");
                UserInfo.getInstance().setSex("女");
                dialog.dismiss();
                return;

            }
            }
        });

builder.show();

    }


    //设置爱好
    @OnClick(R.id.rl_like)
    public void setUserLike(){
        Intent intent = new Intent();
        intent.putExtra("message",1);
        intent.setClass(this,LikeActivity.class);
        startActivityForResult(intent, 1001);
    }
    //保存
    @OnClick(R.id.tv_saveInfo)
    public void saveUserInfo(){
        //添加个人信息
        Call<Result> userInfoCall = NetClient.getInstance().addUserInfo(UserInfo.getInstance().getUid(),
                String.valueOf(R.drawable.tou1),UserInfo.getInstance().getUser_name(),
                UserInfo.getInstance().getLike(),UserInfo.getInstance().getSex()
                );
        Log.d(TAG, "saveUserInfo: "+UserInfo.getInstance().getUid());
        userInfoCall.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();
                int code = result.getCode();
                String msg = result.getMsg();
                if (code== 101) {
                    //保存成功之后，
                   mActivityUtils.showToast(msg);
                    //type值为0
                    LoginResult loginResult=new LoginResult();
                    loginResult.setType(0);
                    mActivityUtils.startActivity(MainActivity.class);
                    return;
                } if (code== 102) {
                    mActivityUtils.showToast(msg);
                    return;
                } if (code== 103) {
                    mActivityUtils.showToast(msg);
                    return;
                }
            }
            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                  new Throwable(t.getMessage());
            }
        });
    }
    //返回按键
    @OnClick(R.id.iv_back)
    public void back(){
        finish();
        //进入主页
        mActivityUtils.startActivity(MainActivity.class);
    }
}
