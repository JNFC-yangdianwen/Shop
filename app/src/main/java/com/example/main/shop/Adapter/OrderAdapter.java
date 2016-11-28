package com.example.main.shop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.main.shop.Constans.Order;
import com.example.main.shop.HttpUtils.NetClient;
import com.example.main.shop.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/11/16.
 */

public class OrderAdapter extends BaseAdapter {
      private List<Order.InfoBean> mData;
      private LayoutInflater inflater;
      public OrderAdapter(Context context,List<Order.InfoBean> mData) {
          this.mData = mData;
          inflater=LayoutInflater.from(context);
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
           convertView= inflater.inflate(R.layout.order_item, null);
        }
        TextView title = (TextView) convertView.findViewById(R.id.tv_title);//标题
        ImageView pic = (ImageView) convertView.findViewById(R.id.iv_pic);//图片
        title.setText(mData.get(position).getTitle());
        ImageLoader.getInstance().displayImage(NetClient.IMAGE_URL+mData.get(position).getPicture(),pic);
        return convertView;
    }
}
