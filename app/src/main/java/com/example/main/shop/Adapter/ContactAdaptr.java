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
 * Created by Administrator on 2016/11/4.
 */

public class ContactAdaptr extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<FriendList.InfoBean> mData;
    public ContactAdaptr() {

    }
    public ContactAdaptr(Context context, List<FriendList.InfoBean> data) {
        mInflater = LayoutInflater.from(context);
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
            convertView = mInflater.inflate(R.layout.contact_item, null);
        }
        ImageView photo = (ImageView) convertView.findViewById(R.id.iv_photo);
        TextView name = (TextView) convertView.findViewById(R.id.tv_name);
        String photo1 = mData.get(position).getPhoto();
        ImageLoader.getInstance().displayImage(NetClient.IMAGE_URL+photo1,photo);
        name.setText(mData.get(position).getUser_name());
        return convertView;
    }
}
