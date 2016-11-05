package com.example.main.shop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.main.shop.R;

import java.util.List;

/**
 * Created by Administrator on 2016/10/26.
 */

public class LikeAdapter extends BaseAdapter {
    private List<String> mData;
    private LayoutInflater mInflater;
    public static  TextView textView;

    public LikeAdapter(List<String> mData, Context context) {
        this.mData = mData;
        mInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int postion) {
        return mData.get(postion);
    }

    @Override
    public long getItemId(int postion) {
        return postion;
    }

    @Override
    public View getView(int postion, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.likeitem, null);
        }
        textView = (TextView) convertView.findViewById(R.id.tvLike);
        textView.setText(mData.get(postion));
        return convertView;
    }
}
