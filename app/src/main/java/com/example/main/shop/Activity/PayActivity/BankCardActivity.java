package com.example.main.shop.Activity.PayActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;

import com.example.main.shop.Constans.Result;
import com.example.main.shop.Constans.UserInfo;
import com.example.main.shop.HttpUtils.NetClient;
import com.example.main.shop.R;
import com.example.main.shop.Utils.ActivityUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//银行卡号页面
public class BankCardActivity extends AppCompatActivity {
   @Bind(R.id.et_name)EditText etName;//姓名
    @Bind(R.id.et_cardNum)EditText etCardNum;//卡号
    @Bind(R.id.et_bank)EditText etBank;//开户行
    private ActivityUtils activityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_card);
        ButterKnife.bind(this);
        activityUtils = new ActivityUtils(this);
    }
    //提交银行卡信息
    @OnClick(R.id.tv_sure)
    public void sure(){
        String name = etName.getText().toString().trim();
        String cardNum = etCardNum.getText().toString().trim();
        String bank = etBank.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            activityUtils.showToast("姓名不能为空");
            return;
        }
        if (cardNum.length()!=17) {
            activityUtils.showToast("银行卡号不正确");
            return;
        }
        if (TextUtils.isEmpty(bank)) {
            activityUtils.showToast("请填写开户行");
            return;
        }
        if (!(TextUtils.isEmpty(name)&&TextUtils.isEmpty(cardNum)&&TextUtils.isEmpty(bank))) {
            String uid = UserInfo.getInstance().getUid();
            Map<String,String> map=new HashMap<>();
            map.put("uid", String.valueOf(uid));
            map.put("type", String.valueOf(3));
            map.put("user_name",name);
            map.put("account",cardNum);
            map.put("bank",bank);
            Call<Result> call = NetClient.getInstance().modifyCashAccount(map);
            call.enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    Result result = response.body();
                    int code = result.getCode();
                    String msg = result.getMsg();
                    if (code==101) {
                    activityUtils.showToast(msg);
                        return;
                    }   if (code==102) {
                    activityUtils.showToast(msg);
                        return;
                    }   if (code==103) {
                        activityUtils.showToast(msg);
                        return;
                    }

                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                    new Throwable(t.getMessage());
                }
            });
        }
    }
}
