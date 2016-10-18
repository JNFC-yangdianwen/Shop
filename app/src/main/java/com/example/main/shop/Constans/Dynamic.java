package com.example.main.shop.Constans;

import java.util.List;

/**
 * Created by Administrator on 2016/10/13 0013.
 * 朋友圈信息
 */
public class Dynamic {
    private String result;
    private String msg;
    private int code;
    private List<DynamicInfo> info;

    public String getResult() {
        return result;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    public List<DynamicInfo> getInfo() {
        return info;
    }

    public void setInfo(List<DynamicInfo> info) {
        this.info = info;
    }

    public class DynamicInfo{

        private int id;// 用户id
        private String photo;//用户头像
        private String user_name;//用户名
        private boolean is_share;//是否分享有奖，有奖为1，无奖为2
        private String content;//内容
        private String picture;//图片
        private String time;//创建时间
        private int click_count;//点赞数量
        private int comment_count;//评论内容
        private int count;//有奖数量
        private double money;//每个钱数

        public DynamicInfo(int id, boolean is_share, String content, String picture, int count, double money) {
            this.id = id;
            this.is_share = is_share;
            this.content = content;
            this.picture = picture;
            this.count = count;
            this.money = money;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public int getComment_count() {
            return comment_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        public int getClick_count() {
            return click_count;
        }

        public void setClick_count(int click_count) {
            this.click_count = click_count;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public boolean is_share() {
            return is_share;
        }

        public void setIs_share(boolean is_share) {
            this.is_share = is_share;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}

