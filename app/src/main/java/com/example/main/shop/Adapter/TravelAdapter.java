package com.example.main.shop.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.main.shop.Constans.Travel;
import com.example.main.shop.Constans.User1;
import com.example.main.shop.HttpUtils.MyRequest;
import com.example.main.shop.HttpUtils.NetClient;
import com.example.main.shop.HttpUtils.NetOkHttp;
import com.example.main.shop.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/11/17.
 */

public class TravelAdapter extends BaseAdapter {
    private List<Travel.InfoBean> mData;
    private LayoutInflater inflater;
    private Context context;
    private  Activity activity;
    public TravelAdapter(List<Travel.InfoBean> mData, Context context ,Activity activity ) {
        this.mData = mData;
        this.activity=activity;
        this.context=context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mData.size();
    }
    @Override
    public Travel.InfoBean getItem(int position) {
        return mData.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView=    inflater.inflate(R.layout.travel_item,null);
        }
        TextView title = (TextView) convertView.findViewById(R.id.travelTile);
        Button btn = (Button) convertView.findViewById(R.id.buy);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid = User1.getInstance().getUid();
                Request request = MyRequest.getInstance().buyTravel(uid, mData.get(position).getId());
                Call call = NetOkHttp.getInstance().getCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String string = response.body().string();
                        try {
                            JSONObject jsonObject=new JSONObject(string);
                            String code = jsonObject.getString("code");
                            final String msg = jsonObject.getString("msg");
                            if (code.equals("101")) {
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                                    }
                                });

                                return;
                            }   if (code.equals("102")) {
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                                    }
                                });
                                return;
                            }   if (code.equals("103")) {
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                                    }
                                });
                                return;
                            }  if (code.equals("104")) {
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                                    }
                                });
                                return;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        ImageView picture = (ImageView) convertView.findViewById(R.id.iv);
        title.setText(mData.get(position).getTitle());
        ImageLoader.getInstance().displayImage(NetClient.IMAGE_URL+mData.get(position).getPicture(),picture);
        return convertView;
    }
}
