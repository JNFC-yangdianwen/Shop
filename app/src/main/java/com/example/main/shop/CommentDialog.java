package com.example.main.shop;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.widget.ListView;

/**
 * Created by Administrator on 2016/11/15.
 */

public class CommentDialog {
 private Context context;
    private final AlertDialog dialog;
    private final ListView listView;

    public CommentDialog(Context context) {
        this.context=context;
        dialog = new AlertDialog.Builder(context).create();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setContentView(R.layout.comment);
        listView = (ListView) window.findViewById(R.id.lv);


    }
}
