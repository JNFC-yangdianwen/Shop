package com.example.main.shop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.main.shop.Constans.Course;
import com.example.main.shop.HttpUtils.NetClient;
import com.example.main.shop.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/11/10.
 */

public class CourseAdapter extends BaseAdapter {
    private List<Course.InfoBean> data;
    private LayoutInflater mInflater;
    public CourseAdapter(List<Course.InfoBean> mData, Context context) {
        mInflater=LayoutInflater.from(context);
        data = mData;
    }

    @Override
    public int getCount() {
        return data.size();
    }
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView= mInflater.inflate(R.layout.course_item, null);
        }
        TextView title = (TextView) convertView.findViewById(R.id.courseTile);
        ImageView iv = (ImageView) convertView.findViewById(R.id.iv);
        title.setText(data.get(position).getTitle());
        ImageLoader.getInstance().displayImage(NetClient.IMAGE_URL+data.get(position).getPicture(),iv);
        return convertView;
    }
}
