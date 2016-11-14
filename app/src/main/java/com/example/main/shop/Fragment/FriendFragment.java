package com.example.main.shop.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.main.shop.Activity.Publish.ReleaseMsgActivity;
import com.example.main.shop.Adapter.LvAdapter;
import com.example.main.shop.Constans.Publish;
import com.example.main.shop.Constans.User1;
import com.example.main.shop.HttpUtils.MyRequest;
import com.example.main.shop.HttpUtils.NetOkHttp;
import com.example.main.shop.R;
import com.example.main.shop.Utils.ActivityUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.ShareSDK;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/9/29 0029.
 * 朋友圈的fragment
 */
public class FriendFragment extends Fragment {
    private String AppKey="17bfcb6cd2ea8";
    @Bind(R.id.lv_frd)ListView mListview;//朋友圈的listview
    private List<Publish.InfoBean> mData;
    private ActivityUtils mUtils;

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
        ButterKnife.bind(this,view);
        mUtils = new ActivityUtils(this);
        mData=new ArrayList<>();
//        // 获取朋友圈信息
//        Call<Dynamic> dynamicCall = NetClient.getInstance().getDynamic(User1.getInstance().getUid());
//        dynamicCall.enqueue(new Callback<Dynamic>() {
//            @Override
//            public void onResponse(Call<Dynamic> call, Response<Dynamic> response) {
//                Dynamic body = response.body();
//                int code = body.getCode();
//                String msg = body.getMsg();
//                /**
//                 *   如果返回码为101表明获取朋友圈的信息成功
//                 *   如果返回码为102表明参数为空
//                 */
//                if (code == 101) {
//                    Dynamic dynamic = response.body();
//                    List<Dynamic.InfoBean> infos = dynamic.getInfo();
////                    mData.addAll(infos);
//                    return;
//                }
//                if (code == 102) {
//                    mUtils.showToast(msg);
//                    return;
//                }
//            }
//            @Override
//            public void onFailure(Call<Dynamic> call, Throwable t) {
//                new Throwable(t.getMessage());
//            }
//        });
////        FriendAdapter friendAdapter=new FriendAdapter(mData,getContext());
////        mListview.setAdapter(friendAdapter);
//        //add a FooterView R
        getData();
        LvAdapter lvAdapter=new LvAdapter(getContext(),R.layout.friend_item,getActivity(),mData);
        mListview.setAdapter(lvAdapter);

    }

    private void getData() {
        String uid = User1.getInstance().getUid();
        Request frd = MyRequest.getInstance().getFrd(uid);
        Call call = NetOkHttp.getInstance().getCall(frd);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                try {
                    JSONObject jsonObject=new JSONObject(string);
                    JSONArray jsonArray = (JSONArray) jsonObject.get("info");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Publish.InfoBean infoBean=new Publish.InfoBean();
                        String photo = (String) jsonArray.getJSONObject(i).getString("photo");
                        String user_name = (String) jsonArray.getJSONObject(i).getString("user_name");
                        String time = (String) jsonArray.getJSONObject(i).getString("time");
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String content = (String) jsonArray.getJSONObject(i).getString("content");
                        infoBean.setUser_name(user_name);
                        infoBean.setPhoto(photo);
                        infoBean.setContent(content);
//                        infoBean.setPicture(List<?> picture);
                        infoBean.setTime(time);
                        mData.add(infoBean);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        getData();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化Sdk
        ShareSDK.initSDK(getContext(),AppKey);
        getData();
    }
    @OnClick(R.id.release)
    public void release(){
        mUtils.startActivity(ReleaseMsgActivity.class);
    }
}
