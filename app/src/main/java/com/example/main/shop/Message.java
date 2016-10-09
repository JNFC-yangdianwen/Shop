package com.example.main.shop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


//具体的聊天窗口页面
public class Message extends AppCompatActivity {
    @Bind(R.id.et_content)EditText mEditText;
    @Bind(R.id.tv_send)TextView tvSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_acitivity);
        ButterKnife.bind(this);
        //输入框调用输入键盘时布局自动上移
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }
    @OnClick({R.id.tv_send})
    public  void onClick(){
        //获取到输入框的内容
        String content = mEditText.getText().toString().trim();
    }
}
