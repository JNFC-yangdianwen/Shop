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
import java.util.Map;

/**
 * Created by Administrator on 2016/10/11 0011.
 */

public class WalletAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private  List<String> mData;
    private int mImage[] ={R.drawable.benjing,R.drawable.benjing,R.drawable.benjing,R.drawable.benjing,R.drawable.benjing,R.drawable.benjing,};
    public WalletAdapter() {

    }
    public WalletAdapter(Context context,List< String> data) {
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
            convertView = mInflater.inflate(R.layout.wallet_item, null);
        }
        ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_gv);
        imageView.setImageResource(R.drawable.benjing);
        for (int i = 0; i < position; i++) {
            imageView.setImageResource(mImage[i]);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.tv_sub);
        textView.setText( mData.get(position));
        return convertView;
    }
}
