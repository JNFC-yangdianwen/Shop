package com.example.main.shop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.util.RecyclerViewUtils;

/**
 * Created by Administrator on 2016/9/29 0029.
 */
public class FriendFragment extends Fragment {
    private RecyclerView mRecyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.friend, container,false);
        return view;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rc);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FriendAdapter friendAdapter=new FriendAdapter(getContext());
        mRecyclerView.setAdapter(friendAdapter);
        //add a FooterView
        RecyclerViewUtils.setFooterView(mRecyclerView, new FooterView(getContext()));
    }
}
