package com.example.main.shop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.main.shop.R;

/**
 * Created by Administrator on 2016/9/29 0029.
 */

public class MssAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    public MssAdapter() {
    }
    public MssAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return 20;
    }
    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //首先判断convertview是否为空，如果为空则去加载mss的item布局
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.mssitem, null);
        }
        ImageView mssIv = (ImageView) convertView.findViewById(R.id.mss_iv);
        TextView mssUser = (TextView) convertView.findViewById(R.id.mss_user);
        TextView mss = (TextView) convertView.findViewById(R.id.mss);
        TextView mssTime = (TextView) convertView.findViewById(R.id.mss_time);
        mssIv.setImageResource(R.mipmap.ic_launcher);
        mssUser.setText("姓名");
        mss.setText("消息");
        mssTime.setText(""+System.currentTimeMillis());
        return convertView;
    }
}
