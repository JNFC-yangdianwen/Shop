package com.example.main.shop.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.main.shop.Activity.Publish.ReleaseMsgActivity;
import com.example.main.shop.Adapter.FriendAdapter;
import com.example.main.shop.Constans.Dynamic;
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
    private List<Dynamic.InfoBean> mData;
    private ActivityUtils mUtils;
    private View view;
    private EditText editText;
    public static FriendAdapter adapter;
    private List<String > mPicture;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.friend, container,false);
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
        mListview.setItemsCanFocus(true);//设置listview 的item子控件获取焦点
        mUtils = new ActivityUtils(this);
        mPicture=new ArrayList<>();
        mData=new ArrayList<>();
        getData();
        adapter = new FriendAdapter(mData,this.getContext(),this.getActivity());
        mListview.setAdapter(adapter);
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        mListview.requestLayout();
        adapter.notifyDataSetChanged();

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
                        Dynamic.InfoBean infoBean=new Dynamic.InfoBean();
                        String photo = jsonArray.getJSONObject(i).getString("photo");
                        String user_name =  jsonArray.getJSONObject(i).getString("user_name");
                        String time =  jsonArray.getJSONObject(i).getString("time");
                        String content =  jsonArray.getJSONObject(i).getString("content");//发布的内容，
                        String is_share = jsonArray.getJSONObject(i).getString("is_share");//是否有奖
                        String click_count = jsonArray.getJSONObject(i).getString("click_count");//点赞次数
                        String comment_count = jsonArray.getJSONObject(i).getString("comment_count");//评论数量
                        String id = jsonArray.getJSONObject(i).getString("id");
                        //图片是一个集合
                        JSONArray picture = jsonArray.getJSONObject(i).getJSONArray("picture");
                        for (int j = 0; j < picture.length(); j++) {
                            String pic= (String) picture.get(j);
                            mPicture.add(pic);
                        }
                        infoBean.setPicture(mPicture);
                        infoBean.setId(id);
                        infoBean.setUser_name(user_name);
                        infoBean.setPhoto(photo);
                        infoBean.setContent(content);
                        infoBean.setIs_share(is_share);
                        infoBean.setClick_count(click_count);
                        infoBean.setTime(time);
                        infoBean.setComment_count(comment_count);
                        mData.add(infoBean);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getData();
    }
    @OnClick(R.id.release)
    public void release(){
        mUtils.startActivity(ReleaseMsgActivity.class);
    }
}
