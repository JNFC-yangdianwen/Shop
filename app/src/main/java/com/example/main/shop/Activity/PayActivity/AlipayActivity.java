package com.example.main.shop.Activity.PayActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;

import com.example.main.shop.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

//支付宝页面
public class AlipayActivity extends AppCompatActivity {
@Bind(R.id.et_name)EditText etName;//姓名
    @Bind(R.id.et_alipayNum)EditText etAccount;//支付宝账户
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alipay);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.tv_sure)
    public void sure(){
        String name = etName.getText().toString().trim();
        String account = etAccount.getText().toString().trim();
        //姓名，账户都不为空，才可以提交个人信息
        if (!(TextUtils.isEmpty(name)&&TextUtils.isEmpty(account))) {

        }
    }
}
