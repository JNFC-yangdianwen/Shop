package com.example.main.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by Administrator on 2016/9/29 0029.
 */

public class MssFragment extends Fragment implements  AdapterView.OnItemClickListener {

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
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (id==mMssAdapter.getItemId(position)) {
            Intent intent=new Intent(getContext(),Message.class);
            startActivity(intent);
        }
    }
}
