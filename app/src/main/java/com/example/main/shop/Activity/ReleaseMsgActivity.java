package com.example.main.shop.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.main.shop.Constans.ReleaseDynamic;
import com.example.main.shop.Constans.Result;
import com.example.main.shop.Constans.UserInfo;
import com.example.main.shop.HttpUtils.NetClient;
import com.example.main.shop.R;
import com.example.main.shop.Utils.ActivityUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * 发布消息
 */
public class ReleaseMsgActivity extends AppCompatActivity {
@Bind(R.id.et_content)EditText tvContent;
    private ActivityUtils mActivityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_msg);
        ButterKnife.bind(this);
        mActivityUtils = new ActivityUtils(this);
    }
    //发表
    @OnClick(R.id.send)
    public void send(){
        //文本内容
        String content = tvContent.getText().toString();
        UserInfo userInfo=new UserInfo();
        int uid = userInfo.getUid();
         //  //添加动态
        Call<Result> resultCall = NetClient.getInstance().addPost(uid,content, String.valueOf(R.drawable.rc_ic_picture),2,1,1);
        resultCall.enqueue(new Callback<Result>() {
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
                }
                if (code == 103) {
                    mActivityUtils.showToast(msg);
                    return;
                }
                if (code == 104) {
                    mActivityUtils.showToast(msg);
                    return;
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                     new  Throwable(t.getMessage());
            }
        });
    }
    //添加图片
    @OnClick(R.id.addPic)
    public void addPhoto(){

    }
}
