package com.example.main.shop.Constans;

/**
 * Created by Administrator on 2016/10/18 0018.
 * 我的
 */

public class MySelf extends Result  {
    /**
     * photo :
     * user_name : 风神
     * like : 旅游
     */
    private InfoBean info;
    public InfoBean getInfo() {
        return info;
    }
    public static class InfoBean {
        private String photo;
        private String user_name;
        private String like;
        public String getPhoto() {
            return photo;
        }
        public String getUser_name() {
            return user_name;
        }
        public String getLike() {
            return like;
        }
    }
}
