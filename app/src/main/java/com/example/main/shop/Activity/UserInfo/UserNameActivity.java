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

//设置用户名
public class UserNameActivity extends AppCompatActivity {
@Bind(R.id.et_name)EditText etName;
    public  String mName;
    public  UserInfo userInfo;
    private ActivityUtils activityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_name);
        ButterKnife.bind(this);
        activityUtils = new ActivityUtils(this);
    }
    @OnClick({R.id.iv_back,R.id.ll_save})
   public void back(View v){
        mName = etName.getText().toString();
        switch(v.getId()){
            //返回
            case R.id.iv_back:
                if (mName == "") {
                    mName=UserInfo.getInstance().getUser_name();
                }
                 finish();
                break;
            //保存昵称
            case R.id.ll_save:
                if (mName.equals("")) {
                      activityUtils.showToast("请输入昵称");
                    return;
                }else {
                Intent intent = new Intent();
                intent.putExtra("result", mName);
                /*
                 * 调用setResult方法表示我将Intent对象返回给之前的那个Activity，这样就可以在onActivityResult方法中得到Intent对象，
                 */
                setResult(1001, intent);
                //    结束当前这个Activity对象的生命
                finish();
                }
                break;
        }
    }
}

