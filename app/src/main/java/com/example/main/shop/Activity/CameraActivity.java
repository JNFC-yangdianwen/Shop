package com.example.main.shop.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.main.shop.R;

import java.io.File;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

// 打开相机或相册的页面
public class CameraActivity extends AppCompatActivity {
     private int type;
     private int mFeatureId;
     private MenuItem mItem;
     @Bind(R.id.option)ImageView ivOption;
    private File mFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
         ButterKnife.bind(this);
        //打开相机
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }
    //点击菜单按钮
   @OnClick(R.id.option)
    public void option(View view){
        showPopupMenu(view);
    }

    private void showPopupMenu(View view) {
        // View当前PopupMenu显示的相对View的位置
        PopupMenu popupMenu = new PopupMenu(this, view);
        // menu布局
        popupMenu.getMenuInflater().inflate(R.menu.main, popupMenu.getMenu());
        // menu的item点击事件
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                type=itemId;
                if (itemId == 0) {
                    //从相机获取图片
                    Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,1);
                }
                if (itemId == 1) {
                    //从相册获取图片
                    Intent intent=new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent,1);
                }
                return false;
            }
        });
        // PopupMenu关闭事件
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
                Toast.makeText(getApplicationContext(), "关闭PopupMenu", Toast.LENGTH_SHORT).show();
            }
        });
        popupMenu.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String fname = DateFormat.format("yyyyMMddhhmmss", new Date()).toString()+".jpg";
        //从相机获取的图片
        if (type==0){
            Bitmap photo = data.getParcelableExtra("data");
            //保存到存储卡
            UserInfoActivity.mView.setImageBitmap(photo);
        }
        //从相册获取的图片
        if (type==1){
            if (requestCode == 1 && resultCode == RESULT_OK && null != data) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();
                UserInfoActivity.mView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            }
        }
    }
    class  MyAsyncTask extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }
    }
}
