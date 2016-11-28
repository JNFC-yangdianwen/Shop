package com.example.main.shop.Constans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/10/18 0018.
 */

public class MySpread {
    /**
     * result : suc
     * msg : 获取成功
     * code : 15821403795
     * money : 0.00
     * super :
     * onecount : 0
     * sendcount : 0
     * thirdcount : 0
     * one_money : 0
     * second_money : 0
     * third_money : 0
     */

    private String result;
    private String msg;
    private String code;
    private String money;
    @SerializedName("super")
    private String superX;
    private String onecount;
    private int sendcount;
    private int thirdcount;
    private int one_money;
    private int second_money;
    private int third_money;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getSuperX() {
        return superX;
    }

    public void setSuperX(String superX) {
        this.superX = superX;
    }

    public String getOnecount() {
        return onecount;
    }

    public void setOnecount(String onecount) {
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

    public int getOne_money() {
        return one_money;
    }

    public void setOne_money(int one_money) {
        this.one_money = one_money;
    }

    public int getSecond_money() {
        return second_money;
    }

    public void setSecond_money(int second_money) {
        this.second_money = second_money;
    }

    public int getThird_money() {
        return third_money;
    }

    public void setThird_money(int third_money) {
        this.third_money = third_money;
    }
}
