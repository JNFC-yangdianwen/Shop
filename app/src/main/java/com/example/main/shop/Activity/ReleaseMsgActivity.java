package com.example.main.shop.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.main.shop.Constans.UserInfo;
import com.example.main.shop.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 发布消息
 */
public class ReleaseMsgActivity extends AppCompatActivity {
@Bind(R.id.et_content)EditText tvContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_msg);
        ButterKnife.bind(this);
    }
    //发表
    @OnClick(R.id.send)
    public void send(){
        //文本内容
        String content = tvContent.getText().toString();
        UserInfo userInfo=new UserInfo();
        int uid = userInfo.getUid();
//        Dynamic.DynamicInfo dynamicInfo=new Dynamic.DynamicInfo(uid,1,content,);
//        //添加动态
//        NetClient.getInstance().addPost()
    }
    //添加图片
    @OnClick(R.id.addPic)
    public void addPhoto(){

    }
}
