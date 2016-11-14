package com.example.main.shop.Constans;

import java.util.List;

/**
 * Created by Administrator on 2016/11/7.
 */

public class Publish extends Result {

    /**
     * photo : Public/Uploads/581bf85c6eaaa.jpg
     * user_name : \"123\"
     * time : 2016-11-04 09:25:44
     * content : \"\"
     * picture : []
     * is_share : 1
     * click_count : 0
     */

    private List<InfoBean> info;

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public static class InfoBean {
        private String photo;
        private String user_name;
        private String time;
        private String content;
        private String is_share;
        private String click_count;
        private List<?> picture;

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

        public List<?> getPicture() {
            return picture;
        }

        public void setPicture(List<?> picture) {
            this.picture = picture;
        }
    }
}
