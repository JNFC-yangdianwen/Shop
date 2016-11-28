package com.example.main.shop.Constans;

import java.util.List;

/**
 * Created by Administrator on 2016/10/18 0018.
 * 动态详情
 */

public class PostInfo extends Result{


    /**
     * uid : 2
     * user_photo :
     * user_name : 老张
     * time : 2016-09-23 17:01:00
     * content : 添加动态测试
     * picture : ["Public/Uploads/57e4ef4c5b7c4.jpg"]
     * click_count : 0
     * isclick : 0
     * data : []
     */

    private String uid;
    private String user_photo;
    private String user_name;
    private String time;
    private String content;
    private String click_count;
    private int isclick;
    private List<String> picture;
    private List<Content> data;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUser_photo() {
        return user_photo;
    }

    public void setUser_photo(String user_photo) {
        this.user_photo = user_photo;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getClick_count() {
        return click_count;
    }

    public void setClick_count(String click_count) {
        this.click_count = click_count;
    }

    public int getIsclick() {
        return isclick;
    }

    public void setIsclick(int isclick) {
        this.isclick = isclick;
    }

    public List<String> getPicture() {
        return picture;
    }

    public void setPicture(List<String> picture) {
        this.picture = picture;
    }

    public List<Content> getData() {
        return data;
    }

    public void setData(List<Content> data) {
        this.data = data;
    }
    public  static class Content{
//        send_id	发送人id
//        send_name	发送人姓名
//        send_photo	发送人头像
//        Content	评论内容
//        time	评论时间
        String send_id;
        String send_name;
        String send_photo;
        String content;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getSend_id() {
            return send_id;
        }

        public void setSend_id(String send_id) {
            this.send_id = send_id;
        }

        public String getSend_name() {
            return send_name;
        }

        public void setSend_name(String send_name) {
            this.send_name = send_name;
        }

        public String getSend_photo() {
            return send_photo;
        }

        public void setSend_photo(String send_photo) {
            this.send_photo = send_photo;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        String time;

    }
}
