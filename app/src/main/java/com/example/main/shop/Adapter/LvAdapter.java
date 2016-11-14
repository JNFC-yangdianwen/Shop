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

import com.example.main.shop.Constans.Publish;
import com.example.main.shop.HttpUtils.NetClient;
import com.example.main.shop.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/11/9.
 */

public class LvAdapter extends BaseAdapter {
    private Context context;

    private Activity activity;

    private List<Publish.InfoBean> mData;

    private int layoutResId;// ListView每个Item的布局文件

    public LvAdapter(Context context, int layoutResId, Activity activity, List<Publish.InfoBean> data) {
        this.context = context;
        this.layoutResId = layoutResId;
        mData =data;
        this.activity = activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewItemHolder viewItemHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutResId,
                    null);
            viewItemHolder = new ViewItemHolder();
            viewItemHolder.ivPhoto= (ImageView) convertView.findViewById(R.id.iv_user);//头像
            viewItemHolder.tvName = (TextView) convertView
                    .findViewById(R.id.tv_user);//用户名
            viewItemHolder.tvContent = (TextView) convertView
                    .findViewById(R.id.tv_mss);//内容
           viewItemHolder.tvDate = (TextView) convertView.findViewById(R.id.tv_time);//日期

            convertView.setTag(viewItemHolder);
        } else {
            viewItemHolder = (ViewItemHolder) convertView.getTag();
        }
        ImageLoader.getInstance().displayImage(NetClient.IMAGE_URL+mData.get(position).getPhoto(),viewItemHolder.ivPhoto);
        viewItemHolder.tvName.setText(mData.get(position).getUser_name());
        viewItemHolder.tvDate.setText(mData.get(position).getTime());
        viewItemHolder.tvContent.setText(mData.get(position).getContent());
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mData.get(position);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        if (null == mData) {
            return 0;
        }
        return mData.size();
    }



    /**
     * 清除所有数据
     */
    public void clear() {
        mData.clear();
    }

    class ViewItemHolder {
        TextView tvName;//用户名
        TextView tvDate;//日期
        TextView tvContent;//内容
        ImageView ivPhoto;//头像
        ImageView ivAgree;
        ImageView ivComment;
        TextView tvComment;
        ImageView ivAgreeShow;
        TextView tvAgreeShow;
        Button btnComment;
        TextView tvComments;
    }

//    class ListViewButtonOnClickListener implements View.OnClickListener {
//        private int position;// 记录ListView中Button所在的Item的位置
//
//        public ListViewButtonOnClickListener(int position) {
//            this.position = position;
//        }
//
//        @Override
//        public void onClick(View v) {
//            switch (v.getId()) {
//                case R.id.ivAgree:
//                    ImageView ivAgree = (ImageView) v;
//
//                    List<String> agreeShow = model.getAgreeShow();
//                    if (null == agreeShow || agreeShow.size() <= 0) {
//                        agreeShow = new ArrayList<String>();
//                    }
//                    if (model.isAgree()) {
//                        agreeShow.remove("我");
//                        ivAgree.setImageResource(R.drawable.qzone_picviewer_bottom_unpraise_icon);
//                    } else {
//                        agreeShow.add("我");
//                        ivAgree.setImageResource(R.drawable.qzone_picviewer_bottom_praise_icon);
//                    }
//                    model.setAgree(!model.isAgree());
//                    model.setAgreeShow(agreeShow);
//                    listViewData.set(position, model);
//                    notifyDataSetChanged();
//                    Toast.makeText(context, "你点了赞", Toast.LENGTH_SHORT).show();
//                    break;
//                case R.id.ivComment:
//                case R.id.tvComment:
//                case R.id.btnComment:
//                    InputMethodManager imm = (InputMethodManager) v.getContext()
//                            .getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
//                    Model model1 = listViewData.get(position);
//                    String nikename = model1.getName();
//                    activity.findViewById(R.id.etComment).setVisibility(
//                            View.VISIBLE);
//                    activity.findViewById(R.id.btnSendComment).setVisibility(
//                            View.VISIBLE);
//                    ((EditText) activity.findViewById(R.id.etComment)).setHint("@"
//                            + nikename);
//                    activity.findViewById(R.id.etComment).setFocusable(true);
//                    activity.findViewById(R.id.btnSendComment).setOnClickListener(
//                            new ListViewButtonOnClickListener(position));
//                    break;
//                case R.id.btnSendComment:
//                    Model mdl = listViewData.get(position);
//                    List<String> commentsList = mdl.getComments();
//                    String commentString = ((EditText) activity
//                            .findViewById(R.id.etComment)).getEditableText()
//                            .toString();
//                    if (null == commentsList || commentsList.size() <= 0) {
//                        commentsList = new ArrayList<String>();
//                    }
//                    commentsList.add(commentString);
//                    mdl.setComments(commentsList);
//                    listViewData.set(position, mdl);
//                    notifyDataSetChanged();
//                    ((EditText) activity.findViewById(R.id.etComment)).setText("");
//                    activity.findViewById(R.id.etComment).setVisibility(View.GONE);
//                    activity.findViewById(R.id.btnSendComment).setVisibility(
//                            View.GONE);
//                    InputMethodManager imm2 = (InputMethodManager) v.getContext()
//                            .getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm2.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
//                    break;
//                default:
//                    break;
//            }
//        }
//    }
}


