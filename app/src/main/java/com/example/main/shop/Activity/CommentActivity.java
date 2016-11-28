package com.example.main.shop.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.main.shop.Adapter.FriendAdapter;
import com.example.main.shop.Constans.Dynamic;
import com.example.main.shop.HttpUtils.NetClient;
import com.example.main.shop.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

//评论的页面
public class CommentActivity extends AppCompatActivity {
    @Bind(R.id.iv_user)ImageView imageView;
    @Bind(R.id.tv_user)TextView usre;
    @Bind(R.id.tv_time)TextView time;
    @Bind(R.id.tv_mss)TextView content;
    @Bind(R.id.tv_praiseCount)TextView count;
    @Bind(R.id.iv_pic)ImageView picture;
    private static final String TAG = "CommentActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);
        //本页面的数据
        Dynamic.InfoBean bean = FriendAdapter.bean;
        ImageLoader.getInstance().displayImage(NetClient.IMAGE_URL+bean.getPhoto(),imageView);
        usre.setText(bean.getUser_name());
        time.setText(bean.getTime());
        content.setText(bean.getContent());
        ImageLoader.getInstance().displayImage(NetClient.IMAGE_URL+bean.getPicture(),picture);

    }
    @OnClick({R.id.iv_back,R.id.clickGood,R.id.clickShare})
    public void action(View v){
        switch (v.getId()){
            case R.id.iv_back:
                finish();
            break;
            case R.id.clickGood:

            break;
            case R.id.clickShare:

            break;
        }
    }
}
