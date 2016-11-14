package com.example.main.shop.Activity.AddFriend;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.main.shop.HttpUtils.NetClient;
import com.example.main.shop.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;
/**
 * 搜索完成之后的结果页面
 */

public class SearchResult extends AppCompatActivity {
@Bind(R.id.tv_name)TextView name;///用户名
    @Bind(R.id.iv_photo)ImageView photo;//用户头像
    @Bind(R.id.tv_sign)TextView sign;//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        ButterKnife.bind(this);
        name.setText(AddFeiendActivity.user_name);
        ImageLoader.getInstance().clearMemoryCache();
        ImageLoader.getInstance().displayImage(NetClient.IMAGE_URL+AddFeiendActivity.photo,photo);
        sign.setText(AddFeiendActivity.account);
    }
}
