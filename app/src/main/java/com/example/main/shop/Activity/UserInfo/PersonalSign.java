package com.example.main.shop.Activity.UserInfo;

import android.content.Intent;
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

public class PersonalSign extends AppCompatActivity {
    @Bind(R.id.et_input)EditText mEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.tv_save)
    public void saveContent(){
        //点击确认之后保存个性签名到服务器
        String content = mEditText.getText().toString().trim();
        Intent intent = new Intent();
        intent.putExtra("sign", content);
                /*
                 * 调用setResult方法表示我将Intent对象返回给之前的那个Activity，这样就可以在onActivityResult方法中得到Intent对象，
                 */
        setResult(1003, intent);
    }
    //返回
    @OnClick(R.id.iv_back)
    public void back(){
        finish();
    }
}
