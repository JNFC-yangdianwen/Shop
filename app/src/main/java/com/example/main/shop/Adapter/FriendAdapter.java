package com.example.main.shop.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.main.shop.R;

/**
 * Created by Administrator on 2016/9/29 0029.
 * 朋友圈列表适配器
 */

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.MyViewHolder> {
   private LayoutInflater mInflater;
    //空参构造函数
    public FriendAdapter() {
    }
    //初始化数据
    public FriendAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mImageView.setImageResource(R.mipmap.ic_launcher);
        holder.mTextViewUser.setText("123");
        holder.mTextViewTime.setText(""+System.currentTimeMillis());
        holder.mTextViewMss.setText("今天的消息：");

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder=new MyViewHolder(mInflater.inflate(R.layout.friend_item,parent,false));
        return myViewHolder;
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageView;
        TextView mTextViewUser;
        TextView mTextViewTime;
        TextView mTextViewMss;
        public MyViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_user);
            mTextViewUser= (TextView) itemView.findViewById(R.id.tv_user);
            mTextViewTime= (TextView) itemView.findViewById(R.id.tv_time);
            mTextViewMss= (TextView) itemView.findViewById(R.id.tv_mss);
        }
    }
}
