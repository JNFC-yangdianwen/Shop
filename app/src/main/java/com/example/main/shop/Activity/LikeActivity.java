package com.example.main.shop.Activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.main.shop.Adapter.LikeAdapter;
import com.example.main.shop.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LikeActivity extends AppCompatActivity {
    @Bind(R.id.gv)GridView gridView;
    private static final String TAG = "LikeActivity";
    private List<String> data;
     private String [] like={
             "运动","旅游","美食","购物",
             "游戏","休闲","爬山","娱乐",
             "金融","语言","艺术","乐器"};

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
                 if (id==postion) {
                     TextView item = (TextView) likeAdapter.getItem(postion);
                     //字体变颜色
                         item.setTextColor(Color.parseColor("#32acd4"));
                 } else{
                     TextView item = (TextView) likeAdapter.getItem(postion);
                     //字体变颜色
                     item.setTextColor(0x8A000000);
                 }
             }
         });

    }
    //保存
    @OnClick(R.id.tvSave)
    public  void save(){

    }
    //返回
    @OnClick(R.id.iv_back)
    public void back(){
        finish();
    }
}
