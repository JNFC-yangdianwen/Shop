package com.example.main.shop.Activity.AddFriend;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.main.shop.Constans.FindFriend;
import com.example.main.shop.HttpUtils.NetClient;
import com.example.main.shop.R;
import com.example.main.shop.Utils.ActivityUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import com.xys.libzxing.zxing.activity.CaptureActivity;

/**
 * 添加好友的页面,
 *         1.扫码添加好友
 *         2.添加手机联系人
 *         3.附近的人
 */

public class AddFeiendActivity extends AppCompatActivity {
    private static final String TAG = "AddFeiendActivity";
@Bind(R.id.et_input)EditText eTmobile;
    private ActivityUtils activityUtils;
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    public static String user_name;
    public static String photo;
    public static String account;
    public static String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feiend);
        ButterKnife.bind(this);
        activityUtils = new ActivityUtils(this);
    }
    //添加朋友
    @OnClick({R.id.iv_search,R.id.rl_scan,R.id.rl_contact,R.id.rl_here,R.id.rl_like})
    public void addFrd(View v){
        switch (v.getId()){
            case R.id.iv_search: //根据手机号查找好友，返回所查手机号的id及用户信息
                String mobile = eTmobile.getText().toString().trim();
                Call<FindFriend.FriendInfo> friendCall = NetClient.getInstance().findFriend(mobile);
                friendCall.enqueue(new Callback<FindFriend.FriendInfo>() {
                    @Override
                    public void onResponse(Call<FindFriend.FriendInfo> call, Response<FindFriend.FriendInfo> response) {
                        FindFriend.FriendInfo body = response.body();
                        Log.d(TAG, "addActivity查询号码的信息。。。。。。。。。" +body);
                        FindFriend.FriendInfo friend = response.body();
                        //所查手机号的id
                        id = friend.getId();
                        account = friend.getAccount();
                        photo = friend.getPhoto();
                        user_name = friend.getUser_name();
                        //跳转结果页面
                        activityUtils.startActivity(SearchResult.class);
                    }

                    @Override
                    public void onFailure(Call<FindFriend.FriendInfo> call, Throwable t) {

                    }
                });


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
            case R.id.rl_contact://添加手机联系人，直接调取手机通讯录
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, 100);
            break;
            case R.id.rl_here://附近人

            break;
            case R.id.rl_like://相同爱好
                       activityUtils.startActivity(SameHobby.class);
            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
                    if (resultCode == Activity.RESULT_OK) {
                        Uri contactData = data.getData();
                        Cursor c =  managedQuery(contactData, null, null, null, null);
                        if (c.moveToFirst()) {
                            String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                            Log.d(TAG, "onActivityResult: "+name);
                        }
                    }
    }
}
