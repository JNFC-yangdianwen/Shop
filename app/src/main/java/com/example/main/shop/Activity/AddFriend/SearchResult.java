package com.example.main.shop.Activity.AddFriend;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.main.shop.Constans.User1;
import com.example.main.shop.HttpUtils.MyRequest;
import com.example.main.shop.HttpUtils.NetClient;
import com.example.main.shop.HttpUtils.NetOkHttp;
import com.example.main.shop.R;
import com.example.main.shop.Utils.ActivityUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

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
 * 搜索完成之后的结果页面
 */

public class SearchResult extends AppCompatActivity {
    private static final String TAG = "SearchResult";
    @Bind(R.id.tv_name)TextView name;///用户名
    @Bind(R.id.iv_photo)ImageView photo;//用户头像
    @Bind(R.id.tv_sign)TextView sign;//
    private ActivityUtils activityUtils;
    public  static  int type;//如果为1，表明手机号查找，为2则表明扫描二维码
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        ButterKnife.bind(this);
        activityUtils = new ActivityUtils(this);
        //手机号查找好友显示的结果
        if (type == 1) {
            name.setText(AddFeiendActivity.user_name);
            ImageLoader.getInstance().clearMemoryCache();
            ImageLoader.getInstance().displayImage(NetClient.IMAGE_URL+AddFeiendActivity.photo,photo);
            sign.setText(AddFeiendActivity.account);
        }
        //扫描二维码的结果
        if (type == 2) {
            name.setText(AddFeiendActivity.result);
        }

    }
    @OnClick ({R.id.iv_back,R.id.ll_sure})
    public void action(View v){
        switch (v.getId()){
            case R.id.iv_back: //按键返回
                finish();
                break;
            case R.id.ll_sure: //确认添加
                String uid = User1.getInstance().getUid();
                Request request = MyRequest.getInstance().addFrd(uid, AddFeiendActivity.id);
                Call call = NetOkHttp.getInstance().getCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String string = response.body().string();
                        Log.d(TAG, "SearchResult: "+string);
                        try {
                            JSONObject jsonObject=new JSONObject(string);
                          final   String msg = jsonObject.getString("msg");
                            //Toast弹窗必须执行的UI线程中即所谓的主线程
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    activityUtils.showToast(msg);
                                }
                            });

//                            {"result":"fail","msg":"\u8be5\u7528\u6237\u5df2\u662f\u60a8\u7684\u597d\u53cb","code":103}
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

                break;
        }
    }

}
