package com.example.main.shop.Constans;

/**
 * Created by Administrator on 2016/10/17 0017.
 */

public class Result {

    /**
     * result : fail
     * msg : 参数不能为空
     * code : 102
     */

    private String result;
    private String msg;
    private int code;
    private int uid;
    private int type;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

