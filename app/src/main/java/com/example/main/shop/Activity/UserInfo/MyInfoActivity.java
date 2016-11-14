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
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.main.shop.Activity.PayActivity.CashActivity;
import com.example.main.shop.Activity.User.LoginActivity;
import com.example.main.shop.Activity.User.ResetMobileActivity;
import com.example.main.shop.Activity.User.ResetPswActivity;
import com.example.main.shop.Constans.Result;
import com.example.main.shop.Constans.User1;
import com.example.main.shop.Constans.UserInfo;
import com.example.main.shop.HttpUtils.MyRequest;
import com.example.main.shop.HttpUtils.NetClient;
import com.example.main.shop.HttpUtils.NetOkHttp;
import com.example.main.shop.R;
import com.example.main.shop.Utils.ActivityUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.hybridsquad.android.library.CropHandler;
import org.hybridsquad.android.library.CropHelper;
import org.hybridsquad.android.library.CropParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private ActivityUtils mActivityUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_infon);
        ButterKnife.bind(this);
        mActivityUtils=new ActivityUtils(this);
        //进入此页面则立即获取个人信息
        String uid = User1.getInstance().getUid();
        Request request = MyRequest.getInstance().getMyinfo(uid);
        Call call = NetOkHttp.getInstance().getCall(request);
        call.enqueue(new Callback() {

            private String userSign;
            private String userSex;
            private String userLike;
            private String userPhoto;
            private String userName;

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.d(TAG, "myinfo: ............"+string);
                try {
                    JSONObject jsonObject=new JSONObject(string);
                    JSONObject info = (JSONObject) jsonObject.get("info");
                    //用户信息
                    for (int i = 0; i <info.length() ; i++) {
                        userPhoto= info.getString("photo");
                        userName = info.getString("user_name");
                        userLike=info.getString("like");
                        userSign=info.getString("sign");
                        userSex=info.getString("sex");
                    }

                       runOnUiThread(new Runnable() {
                           @Override
                           public void run() {
                               name.setText(userName);
                               sign.setText(userSign);
                               ImageLoader.getInstance().displayImage(NetClient.IMAGE_URL+userPhoto,photo);
                               tvSex.setText(userSex);
                               like.setText(userLike);
                           }
                       });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        sign.setText(UserInfo.getInstance().getSign());
        SharedPreferences preferences = getSharedPreferences(LoginActivity.Login, MODE_PRIVATE);
        String userMobile = preferences.getString(LoginActivity.Mobile, "");
        mobile.setText(userMobile);
    }
    //裁剪图片处理
    private CropHandler mCropHandler=new CropHandler() {
        @Override
        public void onPhotoCropped(Uri uri) {
            Log.d(TAG, "onPhotoCropped: .................        运行");
            File file=new File(uri.getPath());
            Log.d(TAG, "onPhotoCropped: ..."+file);
            Log.d(TAG, "onPhotoCropped: ...................."+file.length());
            ImageLoader.getInstance().displayImage("file://"+file.getAbsolutePath(), photo);
            UserInfo.getInstance().setPhoto(file.getName());
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
                    String uid = User1.getInstance().getUid();
//                    Map<String,String> map=new HashMap<>();
//                    map.put("uid",uid);
//                    map.put("like",UserInfo.getInstance().getLike());
//                    map.put("sex",UserInfo.getInstance().getSex());
//                    map.put("user_name",UserInfo.getInstance().getUser_name());
//                    map.put("sign",UserInfo.getInstance().getSign());
//                    File file=new File(UserInfo.getInstance().getPhoto());
//                    Request request = MyRequest.getFileRequest("http://renrenshang.tongyi100.cn/index.php/Api/user_info_edit", file, map);
//                    Call call = NetOkHttp.getInstance().getCall(request);
//                    call.enqueue(new Callback() {
//                        @Override
//                        public void onFailure(Call call, IOException e) {
//
//                        }
//                        @Override
//                        public void onResponse(Call call, Response response) throws IOException {
//                                String string = response.body().string();
//                                Log.d(TAG, "onResponse: ..............................."+string);
//                        }
//                    });
//                    Request request = MyRequest.getInstance().modifyUserInfo(uid,
//                            UserInfo.getInstance().getUser_name(),
//                            UserInfo.getInstance().getPhoto(),
//                            UserInfo.getInstance().getLike(), UserInfo.getInstance().getSex(), UserInfo.getInstance().getSign());
//                    Log.d(TAG, "setInfo: .................................."+UserInfo.getInstance().getPhoto().length());
//                   okhttp3.Call call = NetOkHttp.getInstance().getCall(request);
//                    call.enqueue(new okhttp3.Callback() {
//                        @Override
//                        public void onFailure(okhttp3.Call call, IOException e) {
//
//                        }
//
//                        @Override
//                        public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
//                            String string = response.body().string();
//                            try {
//                                JSONObject jsonObject=new JSONObject(string);
//                                int code = (int) jsonObject.get("code");
//                                Object msg = jsonObject.get("msg");
//                                if (code == 101) {
//                                    Log.d(TAG, "onResponse: ........................................"+string);
//                                   mActivityUtils.showToast(msg.toString());
//                                }     if (code == 102) {
//                                    Log.d(TAG, "onResponse: ........................................"+string);
//                                   mActivityUtils.showToast(msg.toString());
//                                }     if (code == 103) {
//                                    Log.d(TAG, "onResponse: ........................................"+string);
//                                   mActivityUtils.showToast(msg.toString());
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    });
////                    RequestBody body = RequestBody.create(null, UserInfo.getInstance().getPhoto());
//
                    File file1=new File(UserInfo.getInstance().getPhoto());
                    RequestBody photoRequestBody = RequestBody.create(MediaType.parse("image/png"), file1);
                    MultipartBody.Part photo = MultipartBody.Part.createFormData("photo", file1.getName(), photoRequestBody);
                    retrofit2.Call<Result> modifyUserInfo = NetClient.getInstance().modifyUserInfo(uid,photo,
                            UserInfo.getInstance().getUser_name(),
                            UserInfo.getInstance().getLike(),
                            UserInfo.getInstance().getSex(), UserInfo.getInstance().getSign());
                    modifyUserInfo.enqueue(new retrofit2.Callback<Result>() {
                        @Override
                        public void onResponse(retrofit2.Call<Result> call, retrofit2.Response<Result> response) {
                            Result result = response.body();
                            int code = result.getCode();
                            String msg = result.getMsg();
                            if (code == 101) {
                                mActivityUtils.showToast(msg);
                                return;
                            }     if (code == 102) {
                                mActivityUtils.showToast(msg);
                                return;
                            }     if (code == 103) {
                                mActivityUtils.showToast(msg);
                                return;
                            }
                        }

                        @Override
                        public void onFailure(retrofit2.Call<Result> call, Throwable t) {

                        }
                    });
                    break;

            }
        }
    @OnClick(R.id.iv_back)
    public void back(){
        finish();
    }
    }
