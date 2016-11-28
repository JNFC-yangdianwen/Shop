package com.example.main.shop.Constans;

import java.util.List;

/**
 * Created by Administrator on 2016/11/7.
 *
 * 我的发布实体类
 */

public class Publish extends Result {


    /**
     * id : 55
     * photo :
     * user_name : 123
     * time : 2016-11-08 09:09:16
     * content : 1243564
     * picture : []
     * is_share : 2
     * click_count : 2
     * isclick : 1
     */

    private List<InfoBean> info;

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public static class InfoBean {
        private String id;
        private String photo;
        private String user_name;
        private String time;
        private String content;
        private String is_share;
        private String click_count;
        private int isclick;
        private List<String> picture;

        public String getId() {
            return id;
        }

        public void setId(String id) {
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

        public String getIs_share() {
            return is_share;
        }

        public void setIs_share(String is_share) {
            this.is_share = is_share;
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
    }
}
