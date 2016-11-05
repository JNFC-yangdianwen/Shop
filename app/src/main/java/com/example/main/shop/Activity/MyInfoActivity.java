package com.example.main.shop.Activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.main.shop.Activity.PayActivity.CashActivity;
import com.example.main.shop.Activity.User.LoginActivity;
import com.example.main.shop.Activity.User.ResetMobileActivity;
import com.example.main.shop.Activity.User.ResetPswActivity;
import com.example.main.shop.Constans.Result;
import com.example.main.shop.Constans.UserInfo;
import com.example.main.shop.HttpUtils.NetClient;
import com.example.main.shop.R;
import com.example.main.shop.Utils.ActivityUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.hybridsquad.android.library.CropHandler;
import org.hybridsquad.android.library.CropHelper;
import org.hybridsquad.android.library.CropParams;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.main.shop.Activity.UserInfoActivity.CHOOSE_PICTURE;
import static com.example.main.shop.Activity.UserInfoActivity.TAKE_PICTURE;

/**
 * 我的个人信息页面
 */
public class MyInfoActivity extends AppCompatActivity {
    private static final String TAG = "MyInfoActivity";
    @Bind(R.id.iv_photo)ImageView photo;//头像
    @Bind(R.id.tv_name)TextView name;//昵称
    @Bind(R.id.tv_like)TextView like;//爱好
    @Bind(R.id.tv_sex)TextView tvSex;//性别
    @Bind(R.id.tv_sign)TextView sign;//个性签名
    @Bind(R.id.tv_mobile)TextView mobile;//手机号
    @Bind(R.id.iv_qr)ImageView ivQrCode;//二维码
    private ActivityUtils mActivityUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_infon);
        ButterKnife.bind(this);
        mActivityUtils=new ActivityUtils(this);
        //进入此页面则立即获取个人信息
        String uid = UserInfo.getInstance().getUid();
        Call<UserInfo> userInfoCall = NetClient.getInstance().userInfo(uid);
        userInfoCall.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                
                UserInfo userInfo = response.body();
                String photo = userInfo.getPhoto();
                String sign = userInfo.getSign();

            }
            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {


            }
        });
        ImageLoader.getInstance().displayImage(UserInfo.getInstance().getPhoto(),photo);
        name.setText(UserInfo.getInstance().getUser_name());
        like.setText(UserInfo.getInstance().getLike());
        tvSex.setText(UserInfo.getInstance().getSex());
//        sign.setText(UserInfo.getInstance().);
        SharedPreferences preferences = getSharedPreferences(LoginActivity.Login, MODE_PRIVATE);
        String userMobile = preferences.getString(LoginActivity.Mobile, "");
        mobile.setText(userMobile);
    }
    //裁剪图片处理
    private CropHandler mCropHandler=new CropHandler() {
        @Override
        public void onPhotoCropped(Uri uri) {
            Log.d(TAG, "onPhotoCropped: .................        运行");
            String picture = "file://" + uri.getPath();
            ImageLoader.getInstance().displayImage(picture, photo);
            UserInfo.getInstance().setPhoto(picture);
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
            return MyInfoActivity.this;
        }
    };

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
                name.setText(result_value);
                UserInfo.getInstance().setUser_name(result_value);
                Log.d(TAG, "onActivityResult: 。。。。。。用户名"+result_value);
                break;
            case 1001:
                if (data == null) {
                    return;
                }
                String hobby = data.getStringExtra("like");
                like.setText(hobby);
                UserInfo.getInstance().setLike(hobby);
                Log.d(TAG, "onActivityResult: ........爱好"+hobby);
                break;
            case 1002:
                if (data == null) {
                    sign.setText("未设置个性签名");
                    return;
                }
                String userSign = data.getStringExtra("sign");
                sign.setText(userSign);
                UserInfo.getInstance().setSign(userSign);
                break;
//                //处理图像
            case CropHelper.REQUEST_CROP://相机
                CropHelper.handleResult(mCropHandler, requestCode, resultCode, data);
                break;
            case CropHelper.REQUEST_CAMERA://相册
                CropHelper.handleResult(mCropHandler, requestCode, resultCode, data);
                break;

        }
    }
        //进入设置页面
        @OnClick({R.id.rl_photo, R.id.rl_name,
                R.id.rl_like, R.id.rl_sex,
                R.id.rl_sign, R.id.rl_qrcode,
                R.id.rl_mobile, R.id.rl_cash,
                R.id.rl_fix,R.id.tv_saveInfo})
        public void setInfo(View v) {

            switch (v.getId()) {
                case R.id.rl_photo://修改头像
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("设置头像");
                    String[] items = {"选择本地照片", "拍照"};
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
                    break;
                case R.id.rl_name://修改昵称
                    Intent intent = new Intent();
                    intent.putExtra("message",1);
                    intent.setClass(this, UserNameActivity.class);
                    startActivityForResult(intent, 1000);
                    break;
                case R.id.rl_like://修改爱好
                    Intent intent1 = new Intent();
                    intent1.putExtra("message",1);
                    intent1.setClass(this,LikeActivity.class);
                    startActivityForResult(intent1, 1001);
                    break;
                case R.id.rl_sex://修改性别
                    final AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                    builder1.setTitle("请选择性别");
                    final String[] sex = {"男", "女",};
                    //    设置一个单项选择下拉框
                    /**
                     * 第一个参数指定我们要显示的一组下拉单选框的数据集合
                     * 第二个参数代表索引，指定默认哪一个单选框被勾选上，1表示默认'女' 会被勾选上
                     * 第三个参数给每一个单选项绑定一个监听器
                     */
                    builder1.setSingleChoiceItems(sex,0, new DialogInterface.OnClickListener()
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
                    builder1.show();
                    break;
                case R.id.rl_sign://修改个性签名
                    Intent intent2 = new Intent();
                    intent2.putExtra("message",1);
                    intent2.setClass(this,PersonalSign.class);
                    startActivityForResult(intent2, 1002);
                    break;
                case R.id.rl_qrcode://查看二维码
                      mActivityUtils.startActivity(QrcodeAcitivity.class);

                    break;
                case R.id.rl_mobile://修改手机号
                      mActivityUtils.startActivity(ResetMobileActivity.class);
                    break;
                case R.id.rl_cash://提现
                   mActivityUtils.startActivity(CashActivity.class);

                    break;
                case R.id.rl_fix://修改密码
                      mActivityUtils.startActivity(ResetPswActivity.class);
                    break;
                case R.id.tv_saveInfo://保存个人信息
                    UserInfo userInfo = UserInfo.getInstance();
                    Call<Result> saveUserInfo = NetClient.getInstance().modifyUserInfo(userInfo.getUid(),userInfo.getPhoto()
                    ,userInfo.getUser_name(),userInfo.getLike(),userInfo.getSex(),"opjknoippokjnmkj");
                    saveUserInfo.enqueue(new Callback<Result>() {
                        @Override
                        public void onResponse(Call<Result> call, Response<Result> response) {
                            Result result = response.body();
                            int code = result.getCode();
                            String msg = result.getMsg();
                            if (code == 101) {
                                mActivityUtils.showToast(msg);
                                return;
                            }
                            if (code == 102) {
                                mActivityUtils.showToast(msg);
                                return;
                            }  if (code == 103) {
                                mActivityUtils.showToast(msg);
                                return;
                            }
                        }

                        @Override
                        public void onFailure(Call<Result> call, Throwable t) {
                              new Throwable(t.getMessage());
                        }
                    });
                    break;

            }
        }

    }
