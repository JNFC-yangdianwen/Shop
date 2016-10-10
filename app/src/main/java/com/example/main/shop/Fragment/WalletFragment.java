package com.example.main.shop.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.main.shop.Activity.DetailActivity;
import com.example.main.shop.Activity.RechargeActivity;
import com.example.main.shop.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/10/9 0009.
 * 钱包的fragment
 */

public class WalletFragment extends Fragment {
    @Bind(R.id.recharge)TextView mTextViewPay;
    @Bind(R.id.detail)TextView detail;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wallet, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
    }
    @OnClick({R.id.recharge,R.id.detail})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.recharge:
                //跳转到充值页面
                Intent intent=new Intent(getContext(), RechargeActivity.class);
                startActivity(intent);
                break;
            case R.id.detail:
                //跳转到明细页面
                Intent intent1=new Intent(getContext(), DetailActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
