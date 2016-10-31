package com.example.main.shop.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import com.example.main.shop.Constans.UserInfo;
import com.example.main.shop.HttpUtils.NetClient;
import com.example.main.shop.R;
import com.example.main.shop.Utils.ActivityUtils;
import com.nostra13.universalimageloader.core.ImageLoader;


import org.hybridsquad.android.library.CropHelper;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.main.shop.Activity.UserInfoActivity.CHOOSE_PICTURE;
import static com.example.main.shop.Activity.UserInfoActivity.TAKE_PICTURE;

//添加好友的页面
public class AddFeiendActivity extends AppCompatActivity {
@Bind(R.id.et_input)EditText eTmobile;
    private ActivityUtils activityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feiend);
        ButterKnife.bind(this);
        activityUtils = new ActivityUtils(this);
    }
    //添加朋友
    @OnClick({R.id.et_input,R.id.rl_scan,R.id.rl_contact,R.id.rl_here,R.id.rl_like})
    public void addFrd(View v){
        switch (v.getId()){
            case R.id.et_input: //输入手机号添加朋友， 好友的id
                String mobile = eTmobile.getText().toString().trim();
//                NetClient.getInstance().addFriend(UserInfo.getInstance().getUid(),);
                break;
            case R.id.rl_scan://扫描二维码
                AlertDialog.Builder dialog=new  AlertDialog.Builder(this);
                dialog.setTitle("");
                String[] items = { "选择本地照片", "拍照" };
                dialog.setNegativeButton("取消", null);
                dialog.setItems(items, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case CHOOSE_PICTURE: // 选择本地照片
                                break;
                            case TAKE_PICTURE: // 拍照
//                                startActivityForResult(new Intent(AddFeiendActivity.this,CaptureActivity.class),0);
                                break;
                        }
                    }
                });
               break;
            case R.id.rl_contact://通讯录

            break;
            case R.id.rl_here://附近人

            break;
            case R.id.rl_like://相同爱好

            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            //返回结果
            String result = bundle.getString("result");

        }
    }
}
