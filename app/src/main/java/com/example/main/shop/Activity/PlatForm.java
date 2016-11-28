package com.example.main.shop.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.example.main.shop.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlatForm extends AppCompatActivity {
@Bind(R.id.webView)WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plat_form);
        ButterKnife.bind(this);
        webView.loadUrl("file:///android_asset/index.htm");
    }
    @OnClick(R.id.iv_back)
    public void back(){
        finish();
    }
}
