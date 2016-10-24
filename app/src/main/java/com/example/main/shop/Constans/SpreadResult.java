package com.example.main.shop.Constans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/10/22 0022.
 */

public class SpreadResult extends Result  {
/**
 * Result	返回结果fail失败suc成功
 Msg	提示信息
 Code	返回状态
 102	参数不能为空
 101	获取成功
 Money	佣金金额
 Code	邀请码
 Super	我的上级
 Onecount	一级数量
 Sendcount	二级数量
 Thirdcount	三级数量

 */
    private double money;
//    private String code;
    @SerializedName("super")
    private String HighLevel;

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

//    @Override
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }

    public String getHighLevel() {
        return HighLevel;
    }

    public void setHighLevel(String highLevel) {
        HighLevel = highLevel;
    }

    public int getOnecount() {
        return onecount;
    }

    public void setOnecount(int onecount) {
        this.onecount = onecount;
    }

    public int getSendcount() {
        return sendcount;
    }

    public void setSendcount(int sendcount) {
        this.sendcount = sendcount;
    }

    public int getThirdcount() {
        return thirdcount;
    }

    public void setThirdcount(int thirdcount) {
        this.thirdcount = thirdcount;
    }

    private  int onecount;
    private  int sendcount;
    private int thirdcount;

}
