package com.example.main.shop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.main.shop.Constans.MyMsg;
import com.example.main.shop.R;

import java.util.List;

/**
 * Created by Administrator on 2016/9/29 0029.
 */

public class MssAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<MyMsg.MessageInfo> mData;
    public MssAdapter() {
    }
    public MssAdapter(Context context,List<MyMsg.MessageInfo> data) {
        mData=data;
        mInflater = LayoutInflater.from(context);
    }
    //添加一条消息
    public void addMsg(MyMsg.MessageInfo msg){
        mData.add(msg);
    }
    public void addAll(List<MyMsg.MessageInfo> data){
        mData.addAll(data);
    }
    //删除消息
    public void clearMsy(){
        mData.clear();
    }
    @Override
    public int getCount() {
        return mData.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //首先判断convertview是否为空，如果为空则去加载mss的item布局
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.mymssitem, null);
        }
        TextView time = (TextView) convertView.findViewById(R.id.dateTime);
        TextView content = (TextView) convertView.findViewById(R.id.tvContent);
        time.setText(mData.get(position).getTime());
        content.setText(mData.get(position).getMessage());
        return convertView;
    }
}
