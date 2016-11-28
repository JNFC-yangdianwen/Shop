package com.example.main.shop.Activity.UserInfo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.main.shop.Constans.UserInfo;
import com.example.main.shop.HttpUtils.NetClient;
import com.example.main.shop.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
//二维码页面
public class QrcodeAcitivity extends AppCompatActivity {
    @Bind(R.id.iv_qr) ImageView qrImage;//二维码
    @Bind(R.id.qr_photo)ImageView photo;//头像
    @Bind(R.id.qr_name)TextView name;//昵称
    private static final String TAG = "QrcodeAcitivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        ButterKnife.bind(this);
        String userName = MyInfoActivity.userName;
        String userPhoto = UserInfo.getInstance().getPhoto();
        Bitmap qrCodeImage = EncodingUtils.createQRCode(userName, 180, 180,null);
        qrImage.setImageBitmap(qrCodeImage);//二维码图片
        name.setText(userName);
        ImageLoader.getInstance().displayImage(NetClient.IMAGE_URL+userPhoto,photo);
        Log.d(TAG, "onCreate: 。。。。。。。。。。。。。。。。。。。。。"+userName+userPhoto);
    }
@OnClick (R.id.iv_back)
    public void back(){
         finish();
      }
}
