package com.example.main.shop.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.main.shop.Constans.SpreadResult;
import com.example.main.shop.Constans.UserInfo;
import com.example.main.shop.HttpUtils.NetClient;
import com.example.main.shop.R;
import com.example.main.shop.Utils.ActivityUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 推广页面
 */

public class SpreadActivity extends AppCompatActivity {
@Bind(R.id.tv_Super)TextView mTvSuper;//我的上级
    @Bind(R.id.tv_oneLevel)TextView mTvOneLevel;//一级成员
     @Bind(R.id.tv_secondLevel)TextView mTvSecondLevel;//二级成员
    @Bind(R.id.tv_thirdLevel)TextView mTvThirdLevel;//三级成员
    private ActivityUtils activityUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spread);
        ButterKnife.bind(this);
        activityUtils=new ActivityUtils(this);
        UserInfo userInfo =new UserInfo();
        int uid = userInfo.getUid();
        //获取我的推广
        Call<SpreadResult> spreadCall = NetClient.getInstance().spread(uid);
        spreadCall.enqueue(new Callback<SpreadResult>() {
            @Override
            public void onResponse(Call<SpreadResult> call, Response<SpreadResult> response) {
                SpreadResult spreadResult = response.body();
                int code = spreadResult.getCode();
                //响应成功
                if (code==101){
                    //设置我的推广信息
                    mTvSuper.setText(spreadResult.getHighLevel());
                    //一级会员人数
                    int onecount = spreadResult.getOnecount();
                    //二级会员人数
                    int sendcount = spreadResult.getSendcount();
                    //三级会员人数
                    int thirdcount = spreadResult.getThirdcount();
                    if (onecount == 0) {
                        return;
                    }else
                    if (onecount!=0&&sendcount==0){
                        mTvOneLevel.setText(spreadResult.getOnecount()+"人");
                        return;
                    }else if (onecount!=0&&sendcount!=0){
                        mTvOneLevel.setText(spreadResult.getOnecount()+"人");
                        mTvSecondLevel.setText(spreadResult.getOnecount()+"人");
                        return;
                    }else if (onecount!=0&&sendcount!=0&&thirdcount!=0){
                        mTvOneLevel.setText(spreadResult.getOnecount()+"人");
                        mTvSecondLevel.setText(spreadResult.getOnecount()+"人");
                        mTvThirdLevel.setText(spreadResult.getOnecount()+"人");
                        return;
                    }
                    return;
                }
                if ( code==102){

                    return;
                }

            }

            @Override
            public void onFailure(Call<SpreadResult> call, Throwable t) {

            }
        });
    }
    //明细
    @OnClick(R.id.detail)
    public void detail(){
        activityUtils.startActivity(DetailActivity.class);
        finish();
    }
    //充值
    @OnClick(R.id.recharge)
    public void recharge(){
        activityUtils.startActivity(RechargeActivity.class);
        finish();
    }
}
