package com.example.main.shop.Constans;

import java.util.List;

/**
 * Created by Administrator on 2016/10/11 0011.
 */

public class Travel extends Result{

    /**
     * id : 2
     * title : 秦皇岛三日游
     * picture : Public/Uploads/58292a1d3985e.jpg
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
        private String title;
        private String picture;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }
    }
}
