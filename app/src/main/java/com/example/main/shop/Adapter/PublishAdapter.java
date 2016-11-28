package com.example.main.shop.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.main.shop.Activity.CommentActivity;
import com.example.main.shop.Constans.Dynamic;
import com.example.main.shop.Constans.Publish;
import com.example.main.shop.Constans.User1;
import com.example.main.shop.Fragment.FriendFragment;
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
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.renren.Renren;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/11/26.
 * 我的发布的适配器  类似朋友圈
 */

public class PublishAdapter extends BaseAdapter {
    //分享测试数据
    private String text = "这是我的分享测试数据！~我只是来酱油的！~请不要在意 好不好？？？？？";
    private String imageurl = "http://h.hiphotos.baidu.com/image/pic/item/ac4bd11373f082028dc9b2a749fbfbedaa641bca.jpg";
    private String title = "拍拍搜";
    private String url = "www.baidu.com";
    private List<Publish.InfoBean> mData;
    private LayoutInflater mInflater;
    private Context context;
    public Activity activity;
    private ImageView imageView;
    public static Publish.InfoBean bean;
    private TextView tvClick;

    public PublishAdapter(List<Publish.InfoBean> mData, Context context,Activity activity) {
        this.context = context;
        this.mData = mData;
        this.activity=activity;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Publish.InfoBean getItem(int postion) {
        return mData.get(postion);
    }

    @Override
    public long getItemId(int postion) {
        return postion;
    }

    @Override
    public View getView(final int postion, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.friend_item, null);
            viewHolder=new ViewHolder();
            viewHolder.ivUser = (ImageView) convertView.findViewById(R.id.iv_user);//头像
            viewHolder. tvUser = (TextView) convertView.findViewById(R.id.tv_user);//用户名
            viewHolder. tvTime = (TextView) convertView.findViewById(R.id.tv_time);//时间
            viewHolder.mss = (TextView) convertView.findViewById(R.id.tv_mss);//文本信息
            viewHolder. pic1 = (ImageView) convertView.findViewById(R.id.iv_pic1);//图片
            viewHolder. pic2 = (ImageView) convertView.findViewById(R.id.iv_pic2);//图片
            viewHolder. pic3 = (ImageView) convertView.findViewById(R.id.iv_pic3);//图片
            viewHolder.  tvComments = (TextView) convertView.findViewById(R.id.tvComments);
            viewHolder.  tvCount = (TextView) convertView.findViewById(R.id.tv_praiseCount);
            viewHolder. ivShare = (ImageView) convertView.findViewById(R.id.iv_share);//有奖的图片
            viewHolder. clikGood = (LinearLayout) convertView.findViewById(R.id.clickGood);//点赞
            viewHolder. clikComment = (LinearLayout) convertView.findViewById(R.id.clickSu);//评论
            viewHolder. clickShare = (LinearLayout) convertView.findViewById(R.id.clickShare);//分享
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder. clikGood.setOnClickListener(new ListViewButtonOnClickListener(postion));//设置点击事件，点赞
        viewHolder. clikComment.setOnClickListener(new ListViewButtonOnClickListener(postion));//评论
        viewHolder. clickShare.setOnClickListener(new ListViewButtonOnClickListener(postion));//分享
        imageView = (ImageView) convertView.findViewById(R.id.iv_clickGood);//点赞之后的图片
        tvClick = (TextView) convertView.findViewById(R.id.tv_click);
        //点赞信息
        if (mData.get(postion).getClick_count().equals("0")) {
            viewHolder.tvCount.setText("0人觉得很赞");
        } else {
            viewHolder.tvCount.setText(mData.get(postion).getClick_count() + "人觉得很赞");
        }
        if (mData.get(postion).getIs_share()=="1") {
            viewHolder.ivShare.setVisibility(View.VISIBLE);
        }
        ImageLoader.getInstance().displayImage(NetClient.IMAGE_URL + mData.get(postion).getPhoto(), viewHolder.ivUser);//设置头像
//        ImageLoader.getInstance().displayImage(NetClient.IMAGE_URL +mData.get(postion).getPicture().get(0),viewHolder.pic1);//设置图片
//        ImageLoader.getInstance().displayImage(NetClient.IMAGE_URL +mData.get(postion).getPicture().get(1),viewHolder.pic2);//设置图片
//        ImageLoader.getInstance().displayImage(NetClient.IMAGE_URL +mData.get(postion).getPicture().get(2),viewHolder.pic3);//设置图片
        viewHolder.  tvUser.setText(mData.get(postion).getUser_name());
        viewHolder.  tvTime.setText(mData.get(postion).getTime());
        viewHolder.    mss.setText(mData.get(postion).getContent());

        return convertView;
    }

    class ListViewButtonOnClickListener implements View.OnClickListener {
        private int position;// 记录ListView中Button所在的Item的位置
        public ListViewButtonOnClickListener(int position) {
            this.position = position;
        }
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.clickGood://动态点赞进行网络请求
                    String uid = User1.getInstance().getUid();
                    Dynamic.InfoBean item = FriendFragment.adapter.getItem(position);
                    final String itemId = item.getId();
                    Request request = MyRequest.getInstance().clickGood(uid,itemId);
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
                                final String msg = jsonObject.getString("msg");
                                String code = jsonObject.getString("code");
                                if (code.equals("101")) {
                                    activity.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                                            imageView.setImageResource(R.drawable.shouye5);
                                            tvClick.setTextColor(Color.parseColor("#ec3030"));
                                        }
                                    });
                                    return;
                                } if (code.equals("102")) {
                                    activity.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    return;
                                } if (code.equals("103")) {
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
                    break;
                case R.id.clickSu:
                    bean = mData.get(position);
                    activity.startActivity(new Intent(activity, CommentActivity.class));
                    break;
                case R.id.clickShare:
                    //初始化sharesdk
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
                            Platform.ShareParams sp = new Platform.ShareParams();
                            sp.setTitle("测试分享的标题");
                            sp.setTitleUrl("http://sharesdk.cn"); // 标题的超链接
                            sp.setText("测试分享的文本");
                            sp.setImageUrl("http://f1.sharesdk.cn/imgs/2014/05/21/oESpJ78_533x800.jpg");//分享网络图片
                            if (item.get("ItemText").equals("QQ好友")) {
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
                            } else if (item.get("ItemText").equals("微信好友")){
                                Toast.makeText(context, "微信好友", Toast.LENGTH_SHORT).show();
                                Wechat.ShareParams shareParams=new Wechat.ShareParams();
                                shareParams.setTitle("12345");
                                shareParams.setShareType(1);
                                Platform wechat = ShareSDK.getPlatform(context,Wechat.NAME);
                                wechat.setPlatformActionListener(new PlatformActionListener() {
                                    @Override
                                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                                    }
                                    @Override
                                    public void onError(Platform platform, int i, Throwable throwable) {
                                    }
                                    @Override
                                    public void onCancel(Platform platform, int i) {
                                    }
                                });
                                wechat.share(shareParams);
                            }else if (item.get("ItemText").equals("朋友圈")){
                                OnekeyShare onekeyShare =new OnekeyShare();
                                onekeyShare.setPlatform(Wechat.NAME);
                                onekeyShare.setTitle("1323");
                                onekeyShare.setCallback(new PlatformActionListener() {
                                    @Override
                                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                                    }
                                    @Override
                                    public void onError(Platform platform, int i, Throwable throwable) {
                                    }
                                    @Override
                                    public void onCancel(Platform platform, int i) {
                                    }
                                });
                                onekeyShare.show(context);
                                Toast.makeText(context, "微信好友", Toast.LENGTH_SHORT).show();
                            }else if (item.get("ItemText").equals("QQ空间")){
                                Platform qZone = ShareSDK.getPlatform(QZone.NAME);
                                qZone.setPlatformActionListener(new PlatformActionListener() {
                                    @Override
                                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                                    }

                                    @Override
                                    public void onError(Platform platform, int i, Throwable throwable) {
                                    }
                                    @Override
                                    public void onCancel(Platform platform, int i) {
                                    }
                                });
                                qZone.share(sp);
                            }else if (item.get("ItemText").equals("新浪微博")){
                                Platform sinaWeibo = ShareSDK.getPlatform(SinaWeibo.NAME);
                                sinaWeibo.setPlatformActionListener(new PlatformActionListener() {
                                    @Override
                                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                                    }
                                    @Override
                                    public void onError(Platform platform, int i, Throwable throwable) {
                                    }
                                    @Override
                                    public void onCancel(Platform platform, int i) {
                                    }
                                });
                                sinaWeibo.share(sp);
                            }else if (item.get("ItemText").equals("人人网")){
                                OnekeyShare onekeyShare=new OnekeyShare();
                                onekeyShare.setPlatform(Renren.NAME);
                                onekeyShare.show(context);
                                Platform renren = ShareSDK.getPlatform(context,Renren.NAME);
                                Platform.ShareParams shareParams=new Platform.ShareParams();
                                shareParams.imagePath="http://f1.sharesdk.cn/imgs/2014/05/21/oESpJ78_533x800.jpg";
                                shareParams.text="003213123";
                                renren.setPlatformActionListener(new PlatformActionListener() {
                                    @Override
                                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                                    }
                                    @Override
                                    public void onError(Platform platform, int i, Throwable throwable) {
                                    }
                                    @Override
                                    public void onCancel(Platform platform, int i) {
                                    }
                                });
                                renren.share(sp);
                            }
                            shareDialog.dismiss();
                        }
                    });
                    break;
            }
        }
    }
    private class  ViewHolder {
        private    ImageView ivUser;//用户头像
        private   ImageView pic1;//图片
        private   ImageView pic2;  // 图片
        private   ImageView pic3;//图片
        private  TextView tvUser;//用户名
        private  TextView tvTime;//时间
        private     TextView mss;//neir内容
        private    LinearLayout clikGood; //点赞
        private   LinearLayout clikComment;  //评论
        private   LinearLayout clickShare;//分享
        private TextView tvCount;
        private TextView tvComments;
        private ImageView ivShare;//分享有奖的图片
    }

}
