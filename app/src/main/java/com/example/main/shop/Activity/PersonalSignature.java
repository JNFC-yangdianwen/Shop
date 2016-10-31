package com.example.main.shop.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.main.shop.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 个性签名
 */

public class PersonalSignature extends AppCompatActivity {
    @Bind(R.id.et_input)EditText mEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_signature);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.tv_sure)
    public void saveContent(){
        //点击确认之后保存个性签名到服务器
        String content = mEditText.getText().toString().trim();
    }
    //返回
    @OnClick(R.id.iv_back)
    public void back(){
        finish();
    }
}
