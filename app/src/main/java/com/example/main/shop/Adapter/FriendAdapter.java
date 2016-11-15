package com.example.main.shop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.main.shop.Constans.Publish;
import com.example.main.shop.Constans.User1;
import com.example.main.shop.Constans.UserInfo;
import com.example.main.shop.HttpUtils.MyRequest;
import com.example.main.shop.HttpUtils.NetClient;
import com.example.main.shop.HttpUtils.NetOkHttp;
import com.example.main.shop.R;
import com.example.main.shop.ShareDialog;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/9/29 0029.
 * 朋友圈列表适配器
 */

public class FriendAdapter extends BaseAdapter {
    //测试数据
    private String text = "这是我的分享测试数据！~我只是来酱油的！~请不要在意 好不好？？？？？";
    private String imageurl = "http://h.hiphotos.baidu.com/image/pic/item/ac4bd11373f082028dc9b2a749fbfbedaa641bca.jpg";
    private String title = "拍拍搜";
    private String url = "www.baidu.com";
    private List<Publish.InfoBean> mData;
    private LayoutInflater mInflater;
    private Context context;
    private TextView count;

    public FriendAdapter(List<Publish.InfoBean> mData, Context context) {
        this.context = context;
        this.mData = mData;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int postion) {
        return mData.get(postion);
    }

    @Override
    public long getItemId(int postion) {
        return postion;
    }

    @Override
    public View getView(int postion, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.friend_item, null);
        }
        ImageView ivUser = (ImageView) convertView.findViewById(R.id.iv_user);//头像
        TextView tvUser = (TextView) convertView.findViewById(R.id.tv_user);//用户名
        TextView tvTime = (TextView) convertView.findViewById(R.id.tv_time);//时间
        TextView mss = (TextView) convertView.findViewById(R.id.tv_mss);//文本信息
        ImageView pic = (ImageView) convertView.findViewById(R.id.iv_pic);//图片
        count = (TextView) convertView.findViewById(R.id.tv_praiseCount);
        LinearLayout clikGood = (LinearLayout) convertView.findViewById(R.id.clickGood);//点赞
        LinearLayout clikPraise = (LinearLayout) convertView.findViewById(R.id.clickSu);//评论
        LinearLayout clickShare = (LinearLayout) convertView.findViewById(R.id.clickShare);//分享
        clikGood.setOnClickListener(new ListViewButtonOnClickListener(postion));//设置点击事件，点赞
        clikPraise.setOnClickListener(new ListViewButtonOnClickListener(postion));//评论
        clickShare.setOnClickListener(new ListViewButtonOnClickListener(postion));//分享
        ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_clickGood);
        ImageLoader.getInstance().displayImage(NetClient.IMAGE_URL + UserInfo.getInstance().getPhoto(), ivUser);//设置头像
//        ImageLoader.getInstance().displayImage(mData.get(postion).getPicture().get(postion).toString(),pic);//设置图片
        tvUser.setText(mData.get(postion).getUser_name());
        tvTime.setText(mData.get(postion).getTime());
        mss.setText(mData.get(postion).getContent());
        if (mData.get(postion).getClick_count() == null) {
            count.setText("0人觉得很赞");
        } else {
            count.setText(mData.get(postion).getClick_count() + "人觉得很赞");
        }
        return convertView;
    }


    class ListViewButtonOnClickListener implements View.OnClickListener {
        private int position;// 记录ListView中Button所在的Item的位置

        public ListViewButtonOnClickListener(int position) {
            this.position = position;
        }
        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
          switch (v.getId()){
              case R.id.clickGood:
                  Toast.makeText(context, "点赞", Toast.LENGTH_SHORT).show();
                  count.setText("1人点赞");
                  break;
              case R.id.clickSu:
                  Toast.makeText(context, "评论", Toast.LENGTH_SHORT).show();

                  break;
              case R.id.clickShare:
                  ShareSDK.initSDK(context);
                  final ShareDialog shareDialog=new ShareDialog(context);
                  shareDialog.setCancelButtonOnClickListener(new View.OnClickListener() {

                      @Override
                      public void onClick(View v) {
                          shareDialog.dismiss();

                      }
                  });
                  shareDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                      @Override
                      public void onItemClick(AdapterView<?> arg0, View arg1,
                                              int arg2, long arg3) {
                          HashMap<String, Object> item = (HashMap<String, Object>) arg0.getItemAtPosition(arg2);
                          if (item.get("ItemText").equals("QQ好友")) {
                              Platform.ShareParams sp = new Platform.ShareParams();
                              sp.setTitle("测试分享的标题");
                              sp.setTitleUrl("http://sharesdk.cn"); // 标题的超链接
                              sp.setText("测试分享的文本");
                              sp.setImageUrl("http://f1.sharesdk.cn/imgs/2014/05/21/oESpJ78_533x800.jpg");//分享网络图片
                              Platform qq = ShareSDK.getPlatform(QQ.NAME);
                              qq.setPlatformActionListener(new PlatformActionListener() {
                                  // 设置分享事件回调
                                  @Override
                                  public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                                       //分享成功
                                      String uid = User1.getInstance().getUid();
                                      Request request = MyRequest.getInstance().shareBack(uid, String.valueOf(position));
                                      Call call = NetOkHttp.getInstance().getCall(request);
                                      call.enqueue(new Callback() {
                                          @Override
                                          public void onFailure(Call call, IOException e) {
                                              //请求失败
                                          }

                                          @Override
                                          public void onResponse(Call call, Response response) throws IOException {
                                               //响应成功
                                              String string = response.body().string();
                                              try {
                                                  JSONObject jsonObject=new JSONObject(string);
                                                  int code = jsonObject.getInt("code");
                                                  if (code == 101) {

                                                      return;
                                                  }if (code == 102) {
                                                      return;
                                                  }
                                              } catch (JSONException e) {
                                                  e.printStackTrace();
                                              }

                                          }
                                      });
                                  }

                                  @Override
                                  public void onError(Platform platform, int i, Throwable throwable) {
                                      //分享失败
                                  }

                                  @Override
                                  public void onCancel(Platform platform, int i) {
                                       //取消分享

                                  }
                              });
                              // 执行分享
                              qq.share(sp);
                          } else {
                              Toast.makeText(context,"您点中了" + item.get("ItemText"), Toast.LENGTH_LONG).show();
                          }
                          shareDialog.dismiss();

                      }
                  });

                  break;
          }
        }
    }
}