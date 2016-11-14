package com.example.main.shop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.main.shop.Constans.FriendList;
import com.example.main.shop.HttpUtils.NetClient;
import com.example.main.shop.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/11/11.
 */

public class PeopleAdapter extends BaseAdapter {
    private List<FriendList.InfoBean> mData;
    private LayoutInflater mInflater;

    public PeopleAdapter(List<FriendList.InfoBean> mData, Context context) {
        this.mData = mData;
        this.mInflater = LayoutInflater.from(context);
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
             convertView = mInflater.inflate(R.layout.contact_item, null);
        }
        ImageView photo = (ImageView) convertView.findViewById(R.id.iv_photo);
        TextView name = (TextView) convertView.findViewById(R.id.tv_name);
        //设置头像
        ImageLoader.getInstance().displayImage(NetClient.IMAGE_URL+ mData.get(position).getPhoto(),photo);
        name.setText(mData.get(position).getUser_name());
        return convertView;
    }
}
