package com.example.main.shop.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.main.shop.Activity.AddFriend.ContactActivity;
import com.example.main.shop.Adapter.PeopleAdapter;
import com.example.main.shop.Constans.FriendList;
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
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/9/29 0029.
 * 会话列表
 */

public class ChatFragment extends Fragment {
    @Bind(R.id.lv)ListView listView;
    private static final String TAG = "ChatFragment";
    private ActivityUtils activityUtils;
    private List<FriendList.InfoBean> data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mss, container, false);
        return view;

    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        activityUtils = new ActivityUtils(this);
        data=new ArrayList<>();
        String uid = User1.getInstance().getUid();
        Request request = MyRequest.getInstance().getFrdList(uid);
        Call call = NetOkHttp.getInstance().getCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.d(TAG, "chatFragmentent: ......................................"+string);
                try {
                    FriendList.InfoBean friendList= new FriendList.InfoBean();
                    JSONObject jsonObject=new JSONObject(string);
                    JSONArray info = (JSONArray) jsonObject.get("info");
                    for (int i = 0; i < info.length(); i++) {
                        String user_name = info.getJSONObject(i).getString("user_name");
                        String photo = info.getJSONObject(i).getString("photo");
                        friendList.setPhoto(photo);
                        friendList.setUser_name(user_name);
                        data.add(friendList);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        PeopleAdapter peopleAdapter=new PeopleAdapter(data,getContext());
        listView.setAdapter(peopleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == id) {
                    RongIM.getInstance().startConversation(getActivity(), Conversation.ConversationType.PRIVATE,"9527","聊天");
                }
            }
        });
    }
    //添加朋友，通讯录
   @OnClick(R.id.iv_addFrd)
    public void addFriend(){
         //跳转至通讯录页面
       activityUtils.startActivity(ContactActivity.class);
       onDetach();
   }
}
