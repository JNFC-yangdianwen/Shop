package com.example.main.shop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.main.shop.R;

import java.util.List;

/**
 * Created by Administrator on 2016/10/9 0009.
 * 这是My页面的适配器
 */

public class MyAdapter extends BaseAdapter{
    private List<String> mData;
    private LayoutInflater mInflater;
    //空参构造函数
    public MyAdapter() {
    }
    public MyAdapter(Context context,List<String> data) {
     mInflater=LayoutInflater.from(context);
        mData=data;
    }
    @Override
    public int getCount() {
        return mData.size();
    }
    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView= mInflater.inflate(R.layout.myitem, null);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.tv_MyLv);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_MyLv);
        imageView.setImageResource(R.mipmap.right_arrow);
        textView.setText(mData.get(position));
        return convertView;
    }
}
