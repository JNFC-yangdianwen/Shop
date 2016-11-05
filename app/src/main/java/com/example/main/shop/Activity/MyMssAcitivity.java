package com.example.main.shop.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.main.shop.Adapter.MssAdapter;
import com.example.main.shop.Constans.MyMsg;
import com.example.main.shop.HttpUtils.NetClient;
import com.example.main.shop.R;
import com.example.main.shop.Utils.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//我的消息，点击删除单条消息
public class MyMssAcitivity extends AppCompatActivity {
@Bind(R.id.lv)ListView mListView;
    private List<MyMsg> mData;
    private ActivityUtils activityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_mss_acitivity);
        mData=new ArrayList<>();
        ButterKnife.bind(this);
        activityUtils = new ActivityUtils(this);
        Call<MyMsg> mymsg = NetClient.getInstance().getMymsg();
        mymsg.enqueue(new Callback<MyMsg>() {
            @Override
            public void onResponse(Call<MyMsg> call, Response<MyMsg> response) {
                MyMsg msg = response.body();
                int code = msg.getCode();
                String s = msg.getMsg();
                if (code == 101) {
                    mData.add(msg);
                    return;
                }  if (code == 102) {
                    activityUtils.showToast(s);
                    return;
                }  if (code == 103) {
                    activityUtils.showToast(s);
                    return;
                }
            }
                @Override
                public void onFailure(Call<MyMsg> call, Throwable t) {
                    new Throwable(t.getMessage());
            }
        });
        MssAdapter mssAdapter=new MssAdapter(this,mData);
        mListView.setAdapter(mssAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == id) {
                    final AlertDialog.Builder builder=new AlertDialog.Builder(getParent());
                    builder.setTitle("是否删除本条消息");
                    builder.setNegativeButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mData.clear();
                        }
                    });
                    builder.setNeutralButton("", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                }
            }
        });
    }
    @OnClick(R.id.iv_back)
    public void back(){
        finish();
    }
}
