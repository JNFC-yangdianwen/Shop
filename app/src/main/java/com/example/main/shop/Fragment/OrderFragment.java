package com.example.main.shop.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.main.shop.Adapter.OrderAdapter;
import com.example.main.shop.Constans.Order;
import com.example.main.shop.Constans.User1;
import com.example.main.shop.HttpUtils.MyRequest;
import com.example.main.shop.HttpUtils.NetOkHttp;
import com.example.main.shop.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/10/11 0011.
 * 订单fragment
 */

public class OrderFragment extends Fragment {
    private   List<Order.InfoBean> data;
    private static final String TAG = "TravelOrder";
    @Bind(R.id.travel_lv)ListView listView;
    private   OrderAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.travel, container, false);
        return view;
    }   public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        data=new ArrayList<>();
        ButterKnife.bind(this,view);
        getData("1");
        adapter = new OrderAdapter(getContext(),data);
        listView.setAdapter(adapter);
    }
    //获取网络数据
    private void getData(String type) {
        String uid = User1.getInstance().getUid();
        Request request = MyRequest.getInstance().myOrder(uid, type);
        Log.d(TAG, "getData: 。。。。。。。。。。。。。。。。。。。。。。。。。。"+type);
        Call call = NetOkHttp.getInstance().getCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.d(TAG, "onResponse: ......................................................"+string);
                try {
                    JSONObject jsonObject=new JSONObject(string);
                    JSONArray info = jsonObject.getJSONArray("info");
                    for (int i = 0; i < info.length(); i++) {
                        Order.InfoBean orderInfo=new Order.InfoBean();
                        String title = info.getJSONObject(i).getString("title");//标题
                        String picture = info.getJSONObject(i).getString("picture");//图片
                        String order_no = info.getJSONObject(i).getString("order_no");//订单状态
                        String type   =   info.getJSONObject(i).getString("type");
                        String id =  info.getJSONObject(i).getString("id");//课程或订单id
                        Log.d(TAG, "onResponse: ..............."+"标题"+title+"订单号："+order_no);
                        orderInfo.setId(id);
                        orderInfo.setTitle(title);
                        orderInfo.setPicture(picture);
                        orderInfo.setTitle(title);
                        orderInfo.setOrder_no(order_no);
                        orderInfo.setType(type);
                        data.add(orderInfo);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
