package com.example.main.shop.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.main.shop.Constans.User1;
import com.example.main.shop.HttpUtils.MyRequest;
import com.example.main.shop.HttpUtils.NetOkHttp;
import com.example.main.shop.R;
import com.example.main.shop.Utils.ActivityUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 推广页面
 */

public class SpreadActivity extends AppCompatActivity {
    @Bind(R.id.tv_Super)
    TextView mTvSuper;//我的上级
    @Bind(R.id.tv_rest)
    TextView restMoney;//钱包余额
    @Bind(R.id.et_inviteNum)
    EditText etCode;//输入邀请码
    @Bind(R.id.tv_code)
    TextView tvCode;//邀请码
    @Bind(R.id.tv_sureNum)TextView tvSure;//确认按键
    @Bind(R.id.tv_oneLevel)
    TextView mTvOneLevel;//一级成员
    @Bind(R.id.tv_secondLevel)
    TextView mTvSecondLevel;//二级成员
    @Bind(R.id.tv_thirdLevel)
    TextView mTvThirdLevel;//三级成员
    //三个佣金字体显示
    @Bind(R.id.tv_1)TextView tv1;
    @Bind(R.id.tv_2)TextView tv2;
    @Bind(R.id.tv_3)TextView tv3;
    //等级佣金
    @Bind(R.id.tv_oneLevelMoney)TextView oneLevelMoney;
    @Bind(R.id.tv_secondLevelMoney)TextView secondLevelMoney;
    @Bind(R.id.tv_thirdLevelMoney)TextView thirdLevelMoney;
    private ActivityUtils activityUtils;
    private static final String TAG = "SpreadActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spread);
        ButterKnife.bind(this);
        activityUtils = new ActivityUtils(this);
        getData();
    }

    private void getData() {
        String uid = User1.getInstance().getUid();
        Request request = MyRequest.getInstance().mySpread(uid);
        Call call = NetOkHttp.getInstance().getCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                try {
                    Log.d(TAG, "我的推广: ............................................."+string);
                    JSONObject jsonObject = new JSONObject(string);
                    final String money = jsonObject.getString("money");//余额
                    final String aSuper = jsonObject.getString("super");//上级
                    final String code = jsonObject.getString("code");//邀请码
                    final String onecount = jsonObject.getString("onecount");//一级数量
                    final String secondcount = jsonObject.getString("sendcount");//二级数量
                    final String thirdcount = jsonObject.getString("thirdcount");//三级数量
                    final String one_money = jsonObject.getString("one_money");//一级佣金
                    final String second_money = jsonObject.getString("second_money");//二级佣金
                    final String third_money = jsonObject.getString("third_money");//三级佣金
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            restMoney.setText(money);
                            tvCode.setText("邀请码:"+code);
                            if (aSuper.equals("")) {
                                mTvSuper.setText("未添加");
                            }else {
                                mTvSuper.setText(aSuper);
                                //隐藏邀请码输入框和确认按钮
                                etCode.setVisibility(View.INVISIBLE);
                                tvSure.setVisibility(View.INVISIBLE);
                            }
                            if (onecount.equals("0")) {
                                mTvOneLevel.setText("成为会员后可有佣金");
                                mTvSecondLevel.setText("成为会员后可有佣金");
                                mTvThirdLevel.setText("成为会员后可有佣金");
                            } else if (!(onecount.equals("0")) && secondcount.equals("0")) {
                                mTvOneLevel.setText(onecount + "人");
                                mTvSecondLevel.setText("成为会员后可有佣金");
                                mTvThirdLevel.setText("成为会员后可有佣金");
                                tv1.setVisibility(View.VISIBLE);
                                oneLevelMoney.setText(one_money+"元");
                            } else if (!(onecount.equals("0")) && !(secondcount.equals("0")) && thirdcount.equals("0")) {
                                mTvOneLevel.setText(onecount + "人");
                                mTvSecondLevel.setText(secondcount + "人");
                                mTvThirdLevel.setText("成为会员后可有佣金");
                                tv1.setVisibility(View.VISIBLE);
                                tv2.setVisibility(View.VISIBLE);
                                oneLevelMoney.setText(one_money+"元");
                                secondLevelMoney.setText(second_money+"元");
                            } else {
                                mTvOneLevel.setText(onecount + "人");
                                mTvSecondLevel.setText(secondcount + "人");
                                mTvThirdLevel.setText(thirdcount + "人");
                                tv1.setVisibility(View.VISIBLE);
                                tv2.setVisibility(View.VISIBLE);
                                tv3.setVisibility(View.VISIBLE);
                                oneLevelMoney.setText(one_money+"元");
                                secondLevelMoney.setText(second_money+"元");
                                thirdLevelMoney.setText(third_money+"元");
                            }
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //按键返回
    @OnClick({R.id.iv_back,R.id.getMoney})
    public void action(View v) {
       switch (v.getId()){
           case R.id.iv_back://按键返回
               finish();
               break;
           case R.id.getMoney://提现

               break;
           case R.id.tv_sureNum://添加上级
               String num = etCode.getText().toString().trim();
               if (num == null) {
                   activityUtils.showToast("请输入邀请码");
                   return;
               }
               String uid = User1.getInstance().getUid();
               Request request = MyRequest.getInstance().addUpLevel(uid, num);
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
                           String code1 = jsonObject.getString("code");
                           final String msg = jsonObject.getString("msg");
                           if (code1.equals("101")) {
                               runOnUiThread(new Runnable() {
                                   @Override
                                   public void run() {
                                       activityUtils.showToast(msg);
                                   }
                               });
                           }   if (code1.equals("102")) {
                               runOnUiThread(new Runnable() {
                                   @Override
                                   public void run() {
                                       activityUtils.showToast(msg);
                                   }
                               });
                           }   if (code1.equals("103")) {
                               runOnUiThread(new Runnable() {
                                   @Override
                                   public void run() {
                                       activityUtils.showToast(msg);
                                   }
                               });
                           }  if (code1.equals("104")) {
                               runOnUiThread(new Runnable() {
                                   @Override
                                   public void run() {
                                       activityUtils.showToast(msg);
                                   }
                               });
                           }
                       } catch (JSONException e) {
                           e.printStackTrace();
                       }
                   }
               });
               break;
       }
    }





}
