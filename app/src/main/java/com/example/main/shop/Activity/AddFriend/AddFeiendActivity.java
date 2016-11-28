package com.example.main.shop.Activity.AddFriend;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.main.shop.HttpUtils.MyRequest;
import com.example.main.shop.HttpUtils.NetOkHttp;
import com.example.main.shop.R;
import com.example.main.shop.Utils.ActivityUtils;
import com.xys.libzxing.zxing.activity.CaptureActivity;

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
    public static String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feiend);
        ButterKnife.bind(this);
        activityUtils = new ActivityUtils(this);
    }
    //添加朋友
    @OnClick({R.id.iv_search,R.id.rl_scan,R.id.rl_contact,R.id.rl_here,R.id.rl_like,R.id.iv_back})
    public void addFrd(View v){
        switch (v.getId()){
            case R.id.iv_search: //根据手机号查找好友，返回所查手机号的id及用户信息
                SearchResult.type=1;
                String mobile = eTmobile.getText().toString().trim();
                if (TextUtils.isEmpty(mobile)) {
                    activityUtils.showToast("请输入正确的手机号");
                    return;
                }
                Request request = MyRequest.getInstance().getFrdMobile(mobile);
                Call call = NetOkHttp.getInstance().getCall(request);
                call.enqueue(new Callback() {

                    private JSONObject jsonObject;

                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String string = response.body().string();
                        Log.d(TAG, "call: ....................."+string);

                        try {
                            jsonObject = new JSONObject(string);
                            String code = jsonObject.getString("code");
                            final String msg = jsonObject.getString("msg");
                            if (code .equals("101")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //跳转结果页面
                                        JSONObject info = null;
                                        try {
                                            info = (JSONObject) jsonObject.get("info");
                                            id = info.getString("id");
                                            user_name = info.getString("user_name");
                                            photo = info.getString("photo");
                                            account = info.getString("account");
                                            Log.d(TAG, "addActivity查询号码的信息。。。。。。。。。。。。。。。。: "+id+account+photo+user_name);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                        activityUtils.showToast(msg);
                                        activityUtils.startActivity(SearchResult.class);
                                    }
                                });
                               return;
                            }  if (code .equals("102")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        activityUtils.showToast(msg);
                                    }
                                });
                               return;
                            }  if (code .equals("103")) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        activityUtils.showToast(msg);
                                    }
                                });
                               return;
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

                break;
            case R.id.rl_scan://扫描二维码
                SearchResult.type=2;
            startActivityForResult(new Intent(AddFeiendActivity.this,CaptureActivity.class),0);
               break;
            case R.id.rl_contact://添加手机联系人，直接调取手机通讯录
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent,100 );
            break;
            case R.id.rl_here://附近人
                 activityUtils.startActivity(NearActivity.class);
            break;
            case R.id.iv_back://返回
                 finish();
            break;
            case R.id.rl_like://相同爱好
                   activityUtils.startActivity(SameHobby.class);
            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RESULT_OK://处理扫描二维码的结果
                if (data == null) {
                    return;
                }
                Bundle bundle = data.getExtras();
                result = bundle.getString("result");
                startActivity(new Intent(this,SearchResult.class));
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onActivityResult: ................." + result);

                break;
            case 100://处理手机通讯录
                if (data == null) {
                    return;
                }
                Uri contactData = data.getData();
                Cursor c = managedQuery(contactData, null, null, null, null);
                if (c.moveToFirst()) {
                    String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    Log.d(TAG, "onActivityResult: " + name);
                    /**
                     *  如果手机通讯录中没有朋友注册该应用
                     */

                    break;
                }
        }
    }
}
