package com.example.main.shop.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.main.shop.Activity.UserInfo.MyInfoActivity;
import com.example.main.shop.Activity.MyMssAcitivity;
import com.example.main.shop.Activity.Publish.MyRelease;
import com.example.main.shop.Activity.SpreadActivity;
import com.example.main.shop.Activity.SuggestActivity;
import com.example.main.shop.Activity.User.LoginActivity;
import com.example.main.shop.Constans.MySelf;
import com.example.main.shop.Constans.User1;
import com.example.main.shop.Constans.UserInfo;
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
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/10/9 0009.
 * My的Fragment页面
 */

public class MyFragment extends Fragment{
    private static final String TAG = "MyFragment";
    @Bind(R.id.userInfo) RelativeLayout mRelativeLayout;
    @Bind(R.id.userPhoto)ImageView imageView;//头像
    @Bind(R.id.userName)TextView tvName;//昵称
    @Bind(R.id.userHobby)TextView hobby;//爱好
    private ActivityUtils mActivityUtils;
    private String user_name;
    private String photo;
    private String like;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my, container, false);
        return view;
    }
    //请求网络数据
    private void getData() {
        String uid = UserInfo.getInstance().getUid();
        Call<MySelf> selfCall = NetClient.getInstance().mySelf(uid);
        selfCall.enqueue(new Callback<MySelf>() {
            @Override
            public void onResponse(Call<MySelf> call, Response<MySelf> response) {
                MySelf mySelf = response.body();
                int code = mySelf.getCode();
                if (code == 101) {
                    Log.d(TAG, "onResponse: .................."+mySelf.getInfo().getUser_name()+mySelf.getInfo().getLike());
                    tvName.setText(mySelf.getInfo().getUser_name());
                    ImageLoader.getInstance().displayImage(mySelf.getInfo().getPhoto(),imageView);
                    hobby.setText(mySelf.getInfo().getLike());
                }
            }
            @Override
            public void onFailure(Call<MySelf> call, Throwable t) {
                new Throwable(t.getMessage());
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        mActivityUtils=new ActivityUtils(this);
        //设置头像
        setData();


//        ImageLoader.getInstance().displayImage(UserInfo.getInstance().getPhoto(),imageView);
//        tvName.setText(UserInfo.getInstance().getUser_name());
    }

    private void setData() {
        Request request = MyRequest.getInstance().getMyinfo(User1.getInstance().getUid());
        okhttp3.Call call = NetOkHttp.getInstance().getCall(request);
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                 mActivityUtils.showToast(e.getMessage());
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                String string = response.body().string();
                Log.d(TAG, "myinfo: .........."+string);
                try {
                    JSONObject jsonObject=new JSONObject(string);
                    JSONObject info = (JSONObject) jsonObject.get("info");
                    user_name =  info.getString("user_name");
                    photo = info.getString("photo");
                    like =  info.getString("like");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ImageLoader.getInstance().displayImage(NetClient.IMAGE_URL+photo,imageView);
                        tvName.setText(user_name);
                        hobby.setText("兴趣："+like);
                    }
                });
            }

        });
    }

    @OnClick({R.id.userInfo,R.id.rl_mss,
            R.id.rl_vip,R.id.rl_publish,
            R.id.rl_order,R.id.rl_spread,
            R.id.rl_platform,R.id.rl_suggest,
            R.id.rl_exit})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.userInfo:  //跳转个人信息的Activity
                mActivityUtils.startActivity(MyInfoActivity.class);
            break;
            case R.id.rl_mss://进入我的消息页面
                mActivityUtils.startActivity(MyMssAcitivity.class);
            break;
            case R.id.rl_vip://进入升级vip页面
//                mActivityUtils.startActivity(UserInfoActivity.class);
            break;
            case R.id.rl_publish://进入我的发布页面
                mActivityUtils.startActivity(MyRelease.class);
            break;
            case R.id.rl_order://进入我的订单页面
//                mActivityUtils.startActivity(UserInfoActivity.class);
            break;
            case R.id.rl_spread://进入我的推广页面
                mActivityUtils.startActivity(SpreadActivity.class);
            break;
            case R.id.rl_platform://进入关于平台页面
//                mActivityUtils.startActivity(UserInfoActivity.class);
            break;
            case R.id.rl_suggest://进入反馈意见页面
                mActivityUtils.startActivity(SuggestActivity.class);
            break;
            case R.id.rl_exit://退出登录
                mActivityUtils.startActivity(LoginActivity.class);
                getActivity().supportFinishAfterTransition();
            break;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
//    public  void run(Activity activity){
//       activity. runOnUiThread();
//    }
}
