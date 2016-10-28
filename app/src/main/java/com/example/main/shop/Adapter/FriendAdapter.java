package com.example.main.shop.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.main.shop.Constans.Dynamic;
import com.example.main.shop.R;
import com.mob.commons.SHARESDK;

import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import io.rong.imageloader.utils.L;

/**
 * Created by Administrator on 2016/9/29 0029.
 * 朋友圈列表适配器
 */

public class FriendAdapter extends BaseAdapter {
private List<Dynamic.DynamicInfo> mData;
    private LayoutInflater mInflater;

    public FriendAdapter(List<Dynamic.DynamicInfo> mData,Context context) {
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
        TextView tvUser = (TextView) convertView.findViewById(R.id.tv_user);
        TextView tvTime = (TextView) convertView.findViewById(R.id.tv_time);
        TextView mss = (TextView) convertView.findViewById(R.id.tv_mss);
        LinearLayout clikGood = (LinearLayout) convertView.findViewById(R.id.clickGood);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_clickGood);
        TextView tv = (TextView) convertView.findViewById(R.id.tvclick1);
        imageView.setImageResource(R.mipmap.good);
        tv.setText("点赞");
        tvUser.setText(mData.get(postion).getUser_name());
        tvTime.setText(mData.get(postion).getTime());
        mss.setText(mData.get(postion).getContent());

        return convertView;
    }
}
