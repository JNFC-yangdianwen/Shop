package com.example.main.shop;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * Created by Administrator on 2016/11/15.
 */

public class ShareDialog {

    private AlertDialog dialog;
    private GridView gridView;
    private RelativeLayout cancelButton;
    private SimpleAdapter saImageItems;
    private int[] image={R.drawable.fen1,R.drawable.fen2,R.drawable.fen3,R.drawable.fen4,R.drawable.fen5,R.drawable.fen6};
    private String[] name={"微信好友","朋友圈","QQ好友","QQ空间","新浪微博","人人网"};

    public ShareDialog(Context context){

        dialog=new android.app.AlertDialog.Builder(context).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setContentView(R.layout.share_dialog);
        gridView=(GridView) window.findViewById(R.id.share_gridView);
        cancelButton=(RelativeLayout) window.findViewById(R.id.share_cancel);
        List<HashMap<String, Object>> shareList=new ArrayList<HashMap<String,Object>>();
        for(int i=0;i<image.length;i++){
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage", image[i]);//添加图像资源的ID
            map.put("ItemText", name[i]);//按序号做ItemText
            shareList.add(map);
        }
        saImageItems =new SimpleAdapter(context, shareList, R.layout.share_item, new String[] {"ItemImage","ItemText"}, new int[] {R.id.share_icon,R.id.textView1});
        gridView.setAdapter(saImageItems);
    }

    public void setCancelButtonOnClickListener(OnClickListener Listener){
        cancelButton.setOnClickListener(Listener);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        gridView.setOnItemClickListener(listener);
    }
    /**
     * 关闭对话框
     */
    public void dismiss() {
        dialog.dismiss();
    }
}
