package com.example.main.shop.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.main.shop.Activity.CourseActivity;
import com.example.main.shop.Activity.DetailActivity;
import com.example.main.shop.Activity.RechargeActivity;
import com.example.main.shop.R;
import com.example.main.shop.Utils.ActivityUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/10/9 0009.
 * 钱包的fragment
 */

public class WalletFragment extends Fragment implements AdapterView.OnItemClickListener {
    @Bind(R.id.recharge)TextView mTextViewPay;
    @Bind(R.id.detail)TextView detail;
    @Bind(R.id.gv)GridView mGridView;
    private List<Map<String,Object>> data;
    private int image[]={
            R.drawable.wode1,R.drawable.qianbao2,
            R.drawable.qianbao3,R.drawable.qianbao4,
            R.drawable.qianbao4,R.drawable.qianbao5};
    private String mString[]={
            "旅游","培训课程",
            "工厂店","网上超市",
            "敬请期待","敬请期待"};
    private ActivityUtils activityUtils;

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
        activityUtils = new ActivityUtils(this);
        data=new ArrayList<>();
        for (int i = 0; i <image.length; i++) {
            //放入图片，文字
            Map<String,Object> map=new HashMap<>();
               map.put("image",image[i]);
               map.put("text",mString[i]);
            data.add(map);
        }

        SimpleAdapter simpleAdapter=new SimpleAdapter(getContext(),data,
                R.layout.wallet_item,new String[]{"image","text"},new int[]{R.id.iv_gv,R.id.tv_sub});
        mGridView.setAdapter(simpleAdapter);
        //item点击事件
        mGridView.setOnItemClickListener(this);
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
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 1) {
           activityUtils.startActivity(CourseActivity.class);
        }
    }
}
