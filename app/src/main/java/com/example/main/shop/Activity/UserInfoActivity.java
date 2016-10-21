package com.example.main.shop.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.main.shop.Constans.Result;
import com.example.main.shop.Constans.UserInfo;
import com.example.main.shop.HttpUtils.NetClient;
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
        SharedPreferences sp = getSharedPreferences("mobile", MODE_PRIVATE);
        final String mobile = sp.getString("login", "");
        UserInfo userInfo=new UserInfo();
        int uid = userInfo.getUid();
        Call<UserInfo> userInfoCall =  NetClient.getInstance().userInfo(uid);
        userInfoCall.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                UserInfo userInfo1 = response.body();
                String userame = userInfo1.getUser_name();
                String sex = userInfo1.getSex();
                String like = userInfo1.getLike();
                tvName.setText(userame);
                tvLike.setText(like);
                tvSex.setText(sex);
                tvMobile.setText(mobile);
               Log.d(TAG, "onResponse: "+userame+sex+like);
            }
            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {

            }
        });
    }

    //设置头像
    @OnClick({R.id.iv_photo,R.id.tv_setPhoto})
    public void setPhoto(){
        mActivityUtils.startActivity(CameraActivity.class);
    }
    //设置昵称
    @OnClick(R.id.rl_name)
    public void setUserNmae(){
           mActivityUtils.startActivity(PersonalSignature.class);
    }
    //设置性别
    @OnClick(R.id.rl_sex)
    public void setUserSex(){

    }
    //设置爱好
    @OnClick(R.id.rl_like)
    public void setUserLike(){

    }
    //设置银行卡
    @OnClick(R.id.rl_cash)
    public void setUserCash(){

    }
    //保存
    @OnClick(R.id.tv_save)
    public void saveUserInfo(){
        UserInfo userInfo=new UserInfo();
        //上传个人信息
        Call<Result> resultCall = NetClient.getInstance().modifyUserInfo(userInfo);
        resultCall.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result body = response.body();
                int code = body.getCode();
                String msg = body.getMsg();
                if (code == 101) {
                  mActivityUtils.showToast(msg);
                    return;
                }
                if (code == 102) {
                    mActivityUtils.showToast(msg);
                    return;
                }
                if (code == 103) {
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
    }
}
