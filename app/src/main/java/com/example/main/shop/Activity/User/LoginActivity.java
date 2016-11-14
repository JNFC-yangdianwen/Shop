package com.example.main.shop.Activity.User;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.main.shop.Activity.UserInfo.UserInfoActivity;
import com.example.main.shop.Constans.LoginResult;
import com.example.main.shop.Constans.User1;
import com.example.main.shop.HttpUtils.NetClient;
import com.example.main.shop.MainActivity;
import com.example.main.shop.R;
import com.example.main.shop.Utils.ActivityUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 登陆界面
 *实现自动登陆
 *
 */

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    @Bind(R.id.iv_login)ImageView mImageView;
    @Bind(R.id.et_user)EditText userName;
    @Bind(R.id.et_psw)EditText psw;
    private ActivityUtils mUtils;
    public static String Login="login";
    public static String Mobile="mobile";
    private String PassWord="psw";
    private ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mUtils = new ActivityUtils(this);
//        //自动登陆
//        SharedPreferences login = getSharedPreferences("login", MODE_PRIVATE);
//        String mobile = login.getString(Mobile, "");
//        String passWord = login.getString(PassWord, "");
//        Map<String,String> map=new HashMap<>();
//        map.put("mobile", mobile);
//        map.put("pwd",passWord);
//        Call<LoginResult> loginResultCall = NetClient.getInstance().login(map);
//        loginResultCall.enqueue(new Callback<LoginResult>() {
//            @Override
//            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
////                  mProgressDialog=ProgressDialog.show(LoginActivity.this,"","登陆中，请稍后");
//                LoginResult result = response.body();
//                String msg = result.getMsg();
//                int code = result.getCode();
//                //登陆成功
//                if (code==101){
////                    mProgressDialog.dismiss();
//                    mUtils.showToast(msg);
//                    int uid = result.getUid();
//                    UserInfo userInfo=new UserInfo();
//                    userInfo.setUid(uid);
//                    Log.d(TAG, "onResponse: "+uid);
//                    int type = result.getType();
//                    /**
//                     * 登陆之后如果type=0，不需要填写个人信息，
//                     * 如果type =1，填写个人信息
//                     */
//                    if (type == 0) {
//                        mUtils.startActivity(MainActivity.class);
//                        return;
//                    }
//                    if (type==1){
//                        //填写个人信息
//                        mUtils.startActivity(UserInfoActivity.class);
//                    }
//                    return;
//                }if (code==102){
//                    mUtils.showToast(msg);
//                    return;
//                }if (code==103){
//                    mUtils.showToast(msg);
//                    return;
//                }
//            }
//
//            @Override
//            public void onFailure(Call<LoginResult> call, Throwable t) {
//                new Throwable(t.getMessage());
//            }
//        });
////        PayTask payTask =new PayTask(this);
////        String version = payTask.getVersion();
////        Log.d(TAG, "onCreate: "+version);
    }

   @OnClick({R.id.iv_login})
    public void login(){
//       mUtils.startActivity(UserInfoActivity.class);
       mProgressDialog=ProgressDialog.show(this,"","登陆中，请稍后");
       String  mobile = userName.getText().toString().trim();
       String passWord = psw.getText().toString().trim();
       //保存手机号,密码
       SharedPreferences sp=getSharedPreferences(Login,MODE_PRIVATE);
       SharedPreferences.Editor edit = sp.edit();
       edit.putString(Mobile,mobile);
       edit.putString(PassWord,passWord);
       edit.commit();
       Map<String,String> map=new HashMap<>();
       map.put("mobile", mobile);
       map.put("pwd",passWord);
       //执行登陆
       Call<LoginResult> login = NetClient.getInstance().login(map);
       login.enqueue(new Callback<LoginResult>() {
           @Override
           public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
               LoginResult result = response.body();
               String msg = result.getMsg();
               int code = result.getCode();
               String uid = result.getUid();
               if (uid == null) {
                   mUtils.showToast("用户名和密码都不能为空 ");
                   mProgressDialog.dismiss();
                   return;
               }
               SharedPreferences sp = getSharedPreferences("uid", MODE_PRIVATE);
               SharedPreferences.Editor edit = sp.edit().putString("user_uid", uid);
               edit.commit();
               User1.getInstance().setUid(uid);
               Log.d(TAG, "onResponse: ........................"+uid);
               //登陆成功
               if (code==101){
                   //进度条关闭
                   mProgressDialog.dismiss();
                   mUtils.showToast(msg);
                   int type = result.getType();
                   /**
                    * 登陆之后如果type=0，不需要填写个人信息，
                    * 如果type =1，填写个人信息
                    */
                   if (type == 0) {
                       mUtils.startActivity(MainActivity.class);
                       finish();
                   return;
                   }
                   if (type==1){
                       //填写个人信息
                       mUtils.startActivity(UserInfoActivity.class);
                       finish();
                   }
                   finish();
                   return;
               }if (code==102){
                   mProgressDialog.dismiss();
                   mUtils.showToast(msg);
                 return;
               }if (code==103){
                   mProgressDialog.dismiss();
                   mUtils.showToast(msg);
                   return;
               }
           }
           @Override
           public void onFailure(Call<LoginResult> call, Throwable t) {
             new Throwable(t.getMessage());
           }
       });
   }
    //点击注册新用户
    @OnClick(R.id.tv_register)
    public void register(){
        mUtils.startActivity(RegistActivity.class);
    }
    //点击忘记密码
    @OnClick(R.id.tv_forgetPsw)
    public void forgetPsw(){
        mUtils.startActivity(ForgetPswActivity.class);
    }
}