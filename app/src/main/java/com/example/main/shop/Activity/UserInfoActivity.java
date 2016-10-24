package com.example.main.shop.Activity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
           mActivityUtils.startActivity(UserNameActivity.class);
           tvName.setText(UserNameActivity.mName);
    }
    //设置性别
    @OnClick(R.id.rl_sex)
    public void setUserSex(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("请选择性别");
        final String[] sex = {"男", "女",};
        //    设置一个单项选择下拉框
        /**
         * 第一个参数指定我们要显示的一组下拉单选框的数据集合
         * 第二个参数代表索引，指定默认哪一个单选框被勾选上，1表示默认'女' 会被勾选上
         * 第三个参数给每一个单选项绑定一个监听器
         */
        builder.setSingleChoiceItems(sex, 1, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Toast.makeText(UserInfoActivity.this, "性别为：" + sex[which], Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

            }
        });
        builder.show();
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
    @OnClick(R.id.tv_saveInfo)
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
