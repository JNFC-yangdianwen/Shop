package com.example.main.shop.Activity.UserInfo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.main.shop.Activity.Publish.ReleaseMsgActivity;
import com.example.main.shop.R;
import com.example.main.shop.Utils.ActivityUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

//分享有奖
public class ShareReward extends AppCompatActivity {
@Bind(R.id.et_count)EditText eTcount;//分享个数
@Bind(R.id.et_money)EditText eTmoney;//每个钱数
    private double sum;
    @Bind(R.id.tv_sum)TextView tVsum;//总计
    private ActivityUtils activityUtils;
    private int count;
    private Double money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_reward);
        ButterKnife.bind(this);
        activityUtils = new ActivityUtils(this);
        eTmoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            //当输入框内容变化时
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                count = Integer.parseInt((eTcount.getText().toString().trim()));
                String text = eTmoney.getText().toString().trim();
                if ("".equals(text)) {
                    tVsum.setText(""+0.0);
                    return;
                }else
                if (text == null) {
                    Double money = Double.valueOf(text);
                    money=0.0;
                    sum= count *money;
                    return;
                }else if (!text.isEmpty()){
                    money = Double.valueOf(text);
                    sum= count * money;
                    tVsum.setText(""+sum);
                }
            }
        });

    }
    @OnClick({R.id.iv_back,R.id.tv_sure})
    public void action(View v){
           switch (v.getId()){
               //退出此页面
               case R.id.iv_back:
                   finish();
                   break;
               case R.id.tv_sure://确认
                   if (sum == 0) {
                       activityUtils.showToast("请输入有效金额");
                       return;
                   }
                   if (sum>0) {
                       //对ReleaseMsgActivity中的奖励数量，金额进行赋值
                       ReleaseMsgActivity.rewardcount=count;
                       ReleaseMsgActivity.rewardmoney=money;
                       ReleaseMsgActivity.share=2;
                       //扣除钱包中相应的金额
                   }//关闭此页面，进入发布页面
                      finish();
                   break;
           }
    }
}
