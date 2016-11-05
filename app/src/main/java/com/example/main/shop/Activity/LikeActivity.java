package com.example.main.shop.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.main.shop.Adapter.LikeAdapter;
import com.example.main.shop.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 设置爱好页面
 */

public class LikeActivity extends AppCompatActivity {
    @Bind(R.id.gv)GridView gridView;
    private static final String TAG = "LikeActivity";
    private List<String> data;
     private String [] like={
             "运动","旅游","美食","购物",
             "游戏","休闲","爬山","娱乐",
             "金融","语言","艺术","乐器"};
    private String item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like);
        ButterKnife.bind(this);
        data=new ArrayList<>();
        for (int i = 0; i < like.length; i++) {
            data.add(like[i]);
        }
        final LikeAdapter likeAdapter=new LikeAdapter(data,this);
        gridView.setAdapter(likeAdapter);
         gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int postion, long id) {
                 if (postion==id) {
                     item = (String) likeAdapter.getItem(postion);
//                     LikeAdapter.textView.setTextColor(Color.parseColor("#32acd4"));
                     Toast.makeText(LikeActivity.this, item, Toast.LENGTH_SHORT).show();
                 }
             }
         });

    }
    //保存
    @OnClick(R.id.tvSave)
    public  void save(){
        if (TextUtils.isEmpty(item) ) {
            Toast.makeText(this, "请选择您的爱好", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "save: ，，，，，，，，，，，，，"+item);
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("like", item);
                /*
                 * 调用setResult方法表示我将Intent对象返回给之前的那个Activity，这样就可以在onActivityResult方法中得到Intent对象，
                 */
        setResult(1002, intent);
        //    结束当前这个Activity对象的生命
          finish();
    }
    //返回
    @OnClick(R.id.iv_back)
    public void back(){
        finish();
    }
}
