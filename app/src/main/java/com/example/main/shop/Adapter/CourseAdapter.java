package com.example.main.shop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    private Context context;
    public CourseAdapter(List<Course.InfoBean> mData, Context context) {
        this.context=context;
        mInflater = LayoutInflater.from(context);
        data = mData;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Course.InfoBean getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.course_item, null);
        }
        Button buy = (Button) convertView.findViewById(R.id.buy);
        TextView title = (TextView) convertView.findViewById(R.id.courseTile);
        ImageView iv = (ImageView) convertView.findViewById(R.id.iv);
        title.setText(data.get(position).getTitle());
        buy.setOnClickListener(new ListViewButtonOnClickListener(position));
        ImageLoader.getInstance().displayImage(NetClient.IMAGE_URL + data.get(position).getPicture(), iv);
        return convertView;
    }

    class ListViewButtonOnClickListener implements View.OnClickListener {
        private int position;// 记录ListView中Button所在的Item的位置

        public ListViewButtonOnClickListener(int position) {
            this.position = position;
        }
        @Override
        public void onClick(View v) {
                    switch (v.getId()){
                        case R.id.buy:
                            Toast.makeText(context, "购买", Toast.LENGTH_SHORT).show();
                        break;
                    }
        }
    }
}
