package com.example.main.shop.Constans;

import java.util.List;

/**
 * Created by Administrator on 2016/10/13 0013.
 * 我的消息
 */

public class MyMsg extends Result{
    public List<MessageInfo> getInfo() {
        return info;
    }
    private List<MessageInfo> info;
    public class MessageInfo{
    private int id;//用户id
    private  String content;//消息内容
    private String time;//时间
    public int getId() {
        return id;
    }
    public String getContent() {
        return content;
    }
    public String getTime() {
        return time;
    }
}
}
