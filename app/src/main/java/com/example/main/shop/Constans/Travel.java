package com.example.main.shop.Constans;

import java.util.List;

/**
 * Created by Administrator on 2016/10/11 0011.
 */

public class Travel extends Result{
    /**
     * result : suc
     * msg : 获取成功
     * code : 101
     * info : [{"id":"1","title":"英语课","picture":""}]
     */
    /**
     * id : 1
     * title : 英语课
     * picture :
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
