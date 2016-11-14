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
    public static class MessageInfo{
    private String id;//消息id
    private  String message;//消息内容
    private String time;//时间
    public String getId() {
        return id;
    }

        public String getMessage() {
            return message;
        }

        public String getTime() {
        return time;
    }
}
}
