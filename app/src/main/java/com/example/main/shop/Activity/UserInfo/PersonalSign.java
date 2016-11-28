package com.example.main.shop.Activity.UserInfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.main.shop.Constans.UserInfo;
import com.example.main.shop.R;
import com.example.main.shop.Utils.ActivityUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 个性签名
 */

public class PersonalSign extends AppCompatActivity {
    @Bind(R.id.et_input)EditText mEditText;
    private ActivityUtils activityUtils;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        ButterKnife.bind(this);
        activityUtils = new ActivityUtils(this);
    }
    @OnClick({R.id.tv_save,R.id.iv_back})
    public void action(View v){
        content = mEditText.getText().toString().trim();
        switch (v.getId()){
            case  R.id.tv_save :
                if (content.equals("")) {
                    activityUtils.showToast("请输入个性签名");
                }else {
                Intent intent = new Intent();
                intent.putExtra("sign", content);
                /*
                 * 调用setResult方法表示我将Intent对象返回给之前的那个Activity，这样就可以在onActivityResult方法中得到Intent对象，
                 */
                setResult(1003, intent);
                    finish();
                }
            break;
            case  R.id.iv_back://按键返回
                if (content.equals("")) {
                    content = UserInfo.getInstance().getSign();
                }
                finish();
            break;
        }
    }
}
