package com.example.main.shop.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.example.main.shop.R;

/**
 * Created by Administrator on 2016/9/29 0029.
 * 下拉加载更多的布局
 */

public class FooterView extends View {
    public FooterView(Context context) {
        super(context);
    }


    public FooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.footer, null);
    }

}
