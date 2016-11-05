package com.example.main.shop.Activity.PayActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.main.shop.R;
import com.example.main.shop.Utils.ActivityUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
//提现页面
public class CashActivity extends AppCompatActivity {
@Bind(R.id.pay)ImageView imageView;//支付宝
    @Bind(R.id.wechat_pay)ImageView iv_wechat;//微信
    @Bind(R.id.cash_card)ImageView iv_cashCard;//银行卡
    private int payType;
    private ActivityUtils activityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash);
        ButterKnife.bind(this);
        activityUtils = new ActivityUtils(this);
    }
    @OnClick({R.id.rl_pay,R.id.rl_wechatPay,R.id.rl_cash})
    public void payType(View v){
        switch (v.getId()){
            case R.id.rl_pay://支付宝支付
                imageView.setVisibility(View.VISIBLE);
                imageView.setImageResource(R.mipmap.sure);
                iv_wechat.setVisibility(View.INVISIBLE);
                iv_cashCard.setVisibility(View.INVISIBLE);
                payType=1;
                break;
            case R.id.rl_wechatPay://微信支付
                iv_wechat.setVisibility(View.VISIBLE);
                iv_wechat.setImageResource(R.mipmap.sure);
                imageView.setVisibility(View.INVISIBLE);
                iv_cashCard.setVisibility(View.INVISIBLE);
                payType=2;
                break;
            case R.id.rl_cash://银行卡支付
                iv_cashCard.setVisibility(View.VISIBLE);
                iv_cashCard.setImageResource(R.mipmap.sure);
                iv_wechat.setVisibility(View.INVISIBLE);
                imageView.setVisibility(View.INVISIBLE);
                payType=3;
                break;
        }
    }
    @OnClick(R.id.tv_sure)
    public void sure(){
        if (payType==1) {
            //进入绑定支付宝页面
           activityUtils.startActivity(AlipayActivity.class);
            return;
        }if (payType==2) {
            //进入绑定微信页面
           activityUtils.startActivity(WeachatPayActivity.class);
            return;
        }if (payType==3) {
            //进入银行卡绑定页面
          activityUtils.startActivity(BankCardActivity.class);
            return;
        }
    }
}
