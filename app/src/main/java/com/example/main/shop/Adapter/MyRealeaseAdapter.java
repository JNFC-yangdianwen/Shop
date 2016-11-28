package com.example.main.shop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.main.shop.Constans.Publish;
import com.example.main.shop.Constans.UserInfo;
import com.example.main.shop.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/11/3.
 */

public class MyRealeaseAdapter extends BaseAdapter {
    private List<Publish.InfoBean> mData;
    private LayoutInflater mInflater;

    public MyRealeaseAdapter(List<Publish.InfoBean> mData,Context context) {
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
    public View getView(int postion, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.friend_item, null);
        }
        ImageView ivUser = (ImageView) convertView.findViewById(R.id.iv_user);
        TextView tvUser = (TextView) convertView.findViewById(R.id.tv_user);//用户名
        TextView tvTime = (TextView) convertView.findViewById(R.id.tv_time);//时间
        TextView mss = (TextView) convertView.findViewById(R.id.tv_mss);//文本信息
        ImageView pic = (ImageView) convertView.findViewById(R.id.iv_pic);//图片
        TextView count = (TextView) convertView.findViewById(R.id.tv_praiseCount);
        LinearLayout clikGood = (LinearLayout) convertView.findViewById(R.id.clickGood);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_clickGood);
        ImageLoader.getInstance().displayImage(UserInfo.getInstance().getPhoto(),ivUser);//设置头像
        ImageLoader.getInstance().displayImage(mData.get(postion).getPhoto(),pic);//设置图片
        tvUser.setText(UserInfo.getInstance().getUser_name());//用户名
        tvTime.setText(mData.get(postion).getTime());//时间
        mss.setText(mData.get(postion).getContent());//内容
        count.setText(mData.get(postion).getClick_count()+"人觉得很赞");
        return convertView;
    }

}
