package com.example.main.shop.Activity.UserInfo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.main.shop.Constans.UserInfo;
import com.example.main.shop.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QrcodeAcitivity extends AppCompatActivity {
@Bind(R.id.iv_qr) ImageView qrImage;//二维码
    @Bind(R.id.qr_photo)ImageView photo;//头像
    @Bind(R.id.qr_name)TextView name;//昵称

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        ButterKnife.bind(this);
        String user_name = UserInfo.getInstance().getUser_name();
        String userPhoto = UserInfo.getInstance().getPhoto();
        Bitmap bitmap = BitmapFactory.decodeFile(userPhoto);
        Bitmap qrCodeImage = EncodingUtils.createQRCode(user_name, 180, 180,bitmap);
        qrImage.setImageBitmap(qrCodeImage);
        ImageLoader.getInstance().displayImage(userPhoto, this.photo);
        name.setText(user_name);
    }
@OnClick (R.id.iv_back)
    public void back(){
         finish();
      }
}
