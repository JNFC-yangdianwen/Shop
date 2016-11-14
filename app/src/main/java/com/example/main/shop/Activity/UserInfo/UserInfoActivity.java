package com.example.main.shop.Activity.UserInfo;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.main.shop.Activity.User.LoginActivity;
import com.example.main.shop.Constans.User1;
import com.example.main.shop.Constans.UserInfo;
import com.example.main.shop.HttpUtils.MyRequest;
import com.example.main.shop.HttpUtils.NetOkHttp;
import com.example.main.shop.MainActivity;
import com.example.main.shop.R;
import com.example.main.shop.Utils.ActivityUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.hybridsquad.android.library.CropHandler;
import org.hybridsquad.android.library.CropHelper;
import org.hybridsquad.android.library.CropParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 用户个人信息的界面
 */
public class UserInfoActivity extends AppCompatActivity {
    @Bind(R.id.tv_name)TextView tvName;
    @Bind(R.id.tv_like)TextView tvLike;
    @Bind(R.id.tv_sex)TextView tvSex;
    @Bind(R.id.tv_mobile)TextView tvMobile;
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    @Bind(R.id.iv_photo) ImageView ivPhoto;//头像
    private static final String TAG = "UserInfoActivity";
    private ActivityUtils mActivityUtils;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        mActivityUtils = new ActivityUtils(this);
//        ImageLoader.getInstance().displayImage(UserInfo.getInstance().getPhoto(),ivPhoto);
        //手机号
        SharedPreferences sp = getSharedPreferences(LoginActivity.Login, MODE_PRIVATE);
        String moblie = sp.getString(LoginActivity.Mobile, "");
        tvMobile.setText(moblie);
    }
    //裁剪图片处理
    private CropHandler mCropHandler=new CropHandler() {
    @Override
    public void onPhotoCropped(Uri uri) {
        Log.d(TAG, "onPhotoCropped: .................运行");
        String photo ="file://"+uri.getPath();
        ImageLoader.getInstance().displayImage(photo,ivPhoto);
        UserInfo.getInstance().setPhoto(photo);
    }

    @Override
    public void onCropCancel() {
        mActivityUtils.showToast("cancel");
    }

    @Override
    public void onCropFailed(String message) {
        mActivityUtils.showToast(message);
    }

    @Override
    public CropParams getCropParams() {
        CropParams cropParams = new CropParams();
        cropParams.aspectX = 300;
        cropParams.aspectY = 300;
        return cropParams;
    }

    @Override
    public Activity getContext() {
        return UserInfoActivity.this;
    }
};

    //设置头像
    @OnClick({R.id.iv_photo,R.id.tv_setPhoto})
    public void setPhoto(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("设置头像");
        String[] items = { "选择本地照片", "拍照" };
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case CHOOSE_PICTURE: // 选择本地照片
                        //清理缓存
                        ImageLoader.getInstance().clearMemoryCache();
                        ImageLoader.getInstance().clearDiskCache();
                        CropHelper.clearCachedCropFile(mCropHandler.getCropParams().uri);
                        Intent intent = CropHelper.buildCropFromGalleryIntent(mCropHandler.getCropParams());
                        startActivityForResult(intent, CropHelper.REQUEST_CROP);
                        break;
                    case TAKE_PICTURE: // 拍照
                        //清理缓存
                        ImageLoader.getInstance().clearMemoryCache();
                        ImageLoader.getInstance().clearDiskCache();
                        CropHelper.clearCachedCropFile(mCropHandler.getCropParams().uri);
                        Intent intent1 = CropHelper.buildCaptureIntent(mCropHandler.getCropParams().uri);
                        startActivityForResult(intent1, CropHelper.REQUEST_CAMERA);
                        break;
                }
            }
        });
        builder.create().show();
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
                if (data == null) {
                    return;
                }
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
                //处理图像
            case CropHelper.REQUEST_CROP://相机
                CropHelper.handleResult(mCropHandler, requestCode, resultCode, data);
                break;
            case CropHelper.REQUEST_CAMERA://相册
                CropHelper.handleResult(mCropHandler, requestCode, resultCode, data);
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
        Log.d(TAG, "saveUserInfo 用户id"+UserInfo.getInstance().getUid()+ "用户头像"+ UserInfo.getInstance().getPhoto()+
                "用户名"+  UserInfo.getInstance().getUser_name()+"用户爱好"+ UserInfo.getInstance().getLike()+
                "用户性别"+UserInfo.getInstance().getSex());
//        Call<Result> userInfoCall = NetClient.getInstance().addUserInfo(uid,
//              UserInfo.getInstance().getPhoto(),UserInfo.getInstance().getUser_name(),
//                UserInfo.getInstance().getLike(),UserInfo.getInstance().getSex()
//                );
//
//        userInfoCall.enqueue(new Callback<Result>() {
//            @Override
//            public void onResponse(Call<Result> call, Response<Result> response) {
//                Result result = response.body();
//                int code = result.getCode();
//                String msg = result.getMsg();
//                if (code== 101) {
//                    //保存成功之后，
//                   mActivityUtils.showToast(msg);
//                    //type值为0
//                    mActivityUtils.startActivity(MainActivity.class);
//                    finish();
//                    return;
//                } if (code== 102) {
//                    mActivityUtils.showToast(msg);
//                    return;
//                } if (code== 103) {
//                    mActivityUtils.showToast(msg);
//                    return;
//                }
//            }
//            @Override
//            public void onFailure(Call<Result> call, Throwable t) {
//                  new Throwable(t.getMessage());
//            }
//        });

        String uid = User1.getInstance().getUid();
        Request request = MyRequest.getInstance().addUserInfo(uid, UserInfo.getInstance().getPhoto(),
                UserInfo.getInstance().getUser_name(),
                UserInfo.getInstance().getLike(), UserInfo.getInstance().getSex());
        Call call = NetOkHttp.getInstance().getCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.d(TAG, "login: "+string);
                try {
                    JSONObject jsonObject=new JSONObject(string);
                    int code = (int) jsonObject.get("code");
                    String msg = (String) jsonObject.get("msg");
                    if (code == 101) {
                        mActivityUtils.showToast(msg);
                        return;
                    } if (code == 102) {
                        mActivityUtils.showToast(msg);
                        return;
                    } if (code == 103) {
                        mActivityUtils.showToast(msg);
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
