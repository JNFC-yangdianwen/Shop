package com.example.main.shop.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.main.shop.R;
import com.example.main.shop.Utils.ActivityUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 这是充值的页面
 */

public class RechargeActivity extends AppCompatActivity {
@Bind(R.id.et_money)EditText etMoney;
    @Bind(R.id.iv_brokerage)ImageView ivYongjin;
    @Bind(R.id.iv_alipay)ImageView ivAlipay;
    @Bind(R.id.iv_wechatPay)ImageView ivWechatPay;
    @Bind(R.id.iv_bankPay)ImageView ivBankPay;
    private  static int type=0;
    private ActivityUtils activityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        ButterKnife.bind(this);
        activityUtils = new ActivityUtils(this);
    }
    //选择支付方式
    @OnClick({R.id.iv_back,R.id.rlyong,R.id.rl_alipay,R.id.rl_wechat_pay,R.id.rl_bank,R.id.l_sure})
    public void selectPay(View v){
        switch (v.getId()){
            //返回键
            case R.id.iv_back:
                finish();
            break;
            case R.id.rlyong://佣金支付
                type=1;
                ivYongjin.setVisibility(View.VISIBLE);
                ivAlipay.setVisibility(View.GONE);
                ivWechatPay.setVisibility(View.GONE);
                ivBankPay.setVisibility(View.GONE);

            break;
            case R.id.rl_alipay://支付宝支付
                type=2;
                ivYongjin.setVisibility(View.GONE);
                ivAlipay.setVisibility(View.VISIBLE);
                ivWechatPay.setVisibility(View.GONE);
                ivBankPay.setVisibility(View.GONE);
            break;
            case R.id.rl_wechat_pay://微信支付
                type=3;
                ivYongjin.setVisibility(View.GONE);
                ivAlipay.setVisibility(View.GONE);
                ivWechatPay.setVisibility(View.VISIBLE);
                ivBankPay.setVisibility(View.GONE);
            break;
            case R.id.rl_bank://银联支付
                type=4;
                ivYongjin.setVisibility(View.GONE);
                ivAlipay.setVisibility(View.GONE);
                ivWechatPay.setVisibility(View.GONE);
                ivBankPay.setVisibility(View.VISIBLE);
            break;
            case R.id.l_sure://确认
                if (TextUtils.isEmpty(etMoney.getText().toString())) {
                    activityUtils.showToast("请输入充值金额");
                    return;
                }
                if (type == 0) {
                    activityUtils.showToast("请选择支付方式");
                    return;
                }
                double money = Double.parseDouble(etMoney.getText().toString());
                if (type == 1) {
                    activityUtils.showToast("佣金支付"+money);
                }if (type == 2) {
                    activityUtils.showToast("支付宝支付"+money);
                }if (type == 3) {
                    activityUtils.showToast("微信支付"+money);
                }if (type == 4) {
                    activityUtils.showToast("银联支付"+money);
                }
                break;
        }
    }
}
