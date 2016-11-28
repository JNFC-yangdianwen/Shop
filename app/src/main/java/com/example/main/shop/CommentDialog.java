package com.example.main.shop;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.widget.EditText;

/**
 * Created by Administrator on 2016/11/15.
 */

public class CommentDialog {
 private Context context;
    private final AlertDialog dialog;
    private  EditText etComment;
    private final Window window;


    public CommentDialog(Context context) {
        this.context=context;
        dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setContentView(R.layout.comment);
        //输入框
    }

    public EditText getEdit() {
        etComment = (EditText) window.findViewById(R.id.et_comment);
        return etComment;
    }
}
