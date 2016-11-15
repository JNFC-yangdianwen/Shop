package com.example.main.shop.Constans;

/**
 * Created by Administrator on 2016/10/18 0018.
 */

public class FindFriend extends Result{

    /**
     * id : 9
     * photo : Public/Uploads/3.jpg
     * user_name : 00212
     * account : 15821403795
     */

    private InfoBean info;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean {
        private String id;
        private String photo;
        private String user_name;
        private String account;

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

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }
    }
}