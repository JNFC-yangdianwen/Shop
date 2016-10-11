package com.example.main.shop.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.main.shop.R;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/10/11 0011.
 * 旅游的fragment
 */

public class TravellFragment extends WalletSuperFragment {
    @Bind(R.id.title)TextView mTvTitle;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTvTitle.setText("旅游");
    }
}
