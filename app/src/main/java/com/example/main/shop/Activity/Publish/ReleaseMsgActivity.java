package com.example.main.shop.Activity.Publish;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.main.shop.Activity.UserInfo.ShareReward;
import com.example.main.shop.Constans.User1;
import com.example.main.shop.HttpUtils.MyRequest;
import com.example.main.shop.HttpUtils.NetOkHttp;
import com.example.main.shop.R;
import com.example.main.shop.Utils.ActivityUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.hybridsquad.android.library.CropHandler;
import org.hybridsquad.android.library.CropHelper;
import org.hybridsquad.android.library.CropParams;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Request;

/**
 * 发布消息
 */
public class ReleaseMsgActivity extends AppCompatActivity {
    private static final String TAG = "ReleaseMsgActivity";
    @Bind(R.id.et_content)
    EditText tvContent;
    @Bind(R.id.iv_pic)
    ImageView imageView;
    @Bind(R.id.cb)CheckBox checkBox;
    private ActivityUtils mActivityUtils;
    public static int rewardcount; //有奖数量
    public static int share =2 ;//分享是否有奖，默认为1有奖，2为无奖
    public static double rewardmoney;//每个金额
    private String photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_msg);
        ButterKnife.bind(this);
        Log.d(TAG, "onCreate: ...................." + share);
        mActivityUtils = new ActivityUtils(this);
    }

    //发布
    @OnClick(R.id.send)
    public void send() {
        //文本内容
        String content = tvContent.getText().toString();
        String uid = User1.getInstance().getUid();
        SharedPreferences preferences = getSharedPreferences("uid", MODE_PRIVATE);
        String user_uid = preferences.getString("user_uid","");
        Log.d(TAG, "send: ....................................." +user_uid+ share + rewardmoney + rewardcount);
        //  //添加动态,所需参数  uid ，文本内容，图片，是否有奖，有奖为1，无奖为 2，奖励数量，每个奖金额度
        Request publish = MyRequest.getInstance().publish(uid, content, String.valueOf(share), photo, String.valueOf(rewardcount), String.valueOf(rewardmoney));
        okhttp3.Call call = NetOkHttp.getInstance().getCall(publish);
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                String string = response.body().string();
                Log.d(TAG, "onResponse: ............................"+string);
            }
        });
//        File file=new File(photo);
//        MultipartBody.Part formData = MultipartBody.Part.createFormData("image/png", file.getName());
//        Call<Result> resultCall = NetClient.getInstance().addPost(uid, content, formData, String.valueOf(rewardcount), String.valueOf(rewardmoney), String.valueOf(rewardcount));
//        resultCall.enqueue(new Callback<Result>() {
//            @Override
//            public void onResponse(Call<Result> call, Response<Result> response) {
//                Result result = response.body();
//                int code = result.getCode();
//                String msg = result.getMsg();
//                if (code == 101) {
//                    mActivityUtils.showToast(msg);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Result> call, Throwable t) {
//
//            }
//        });
    }
    //使用Universal—imageLoader截图
    private CropParams cropParams;
    private CropHandler cropHandler = new CropHandler() {
        @Override
        public void onPhotoCropped(Uri uri) {
            photo = "file://" + uri.getPath();
            ImageLoader.getInstance().displayImage(photo, imageView);
        }

        @Override
        public void onCropCancel() {

        }

        @Override
        public void onCropFailed(String message) {

        }

        @Override
        public CropParams getCropParams() {
            cropParams = new CropParams();
            cropParams.aspectX = 300;
            cropParams.aspectY = 300;
            return cropParams;
        }

        @Override
        public Activity getContext() {
            return ReleaseMsgActivity.this;
        }
    };

    //添加图片
    @OnClick(R.id.addPic)
    public void addPhoto() {
        Intent intent = CropHelper.buildCaptureIntent(cropHandler.getCropParams().uri);
        startActivityForResult(intent, CropHelper.REQUEST_CAMERA);
    }

    //取消
    @OnClick(R.id.tv_cancel)
    public void cancel() {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CropHelper.handleResult(cropHandler, requestCode, resultCode, data);
    }

    //分享有奖,进入添加奖励页面
    @OnClick(R.id.cb)
    public void shareReward() {
        mActivityUtils.startActivity(ShareReward.class);
        checkBox.setChecked(false);
    }
}
