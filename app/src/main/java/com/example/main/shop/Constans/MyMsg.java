package com.example.main.shop.Constans;

import java.util.List;

/**
 * Created by Administrator on 2016/10/13 0013.
 * 我的消息
 */

public class MyMsg {
private  String result;
    private String msg;
    private int code;


    public String getResult() {
        return result;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    public List<MessageInfo> getInfo() {
        return info;
    }

    public void setInfo(List<MessageInfo> info) {
        this.info = info;
    }

    private List<MessageInfo> info;

    public class MessageInfo{
    private int id;//用户id
    private  String content;//消息内容
    private String time;//时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
}
