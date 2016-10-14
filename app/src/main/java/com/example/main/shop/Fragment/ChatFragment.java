package com.example.main.shop.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.main.shop.Adapter.MssAdapter;
import com.example.main.shop.Activity.MessageActivity;
import com.example.main.shop.R;

/**
 * Created by Administrator on 2016/9/29 0029.
 * 消息的fragment
 */

public class ChatFragment extends Fragment implements  AdapterView.OnItemClickListener {
    private ListView mListView;
    private MssAdapter mMssAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mss, container, false);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListView = (ListView) view.findViewById(R.id.lv);
        mMssAdapter = new MssAdapter(getContext());
        mListView.setAdapter(mMssAdapter);
        mListView.setOnItemClickListener(this);
    }
    //点击每个item之后跳转到对应的聊天窗口
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (id==mMssAdapter.getItemId(position)) {
            Intent intent=new Intent(getContext(),MessageActivity.class);
            startActivity(intent);
        }
    }
}
