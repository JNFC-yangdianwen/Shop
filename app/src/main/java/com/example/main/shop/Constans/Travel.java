package com.example.main.shop.Constans;

import java.util.List;

/**
 * Created by Administrator on 2016/10/11 0011.
 */

public class Travel {
    /**
     * result : suc
     * msg : 获取成功
     * code : 101
     * info : [{"id":"1","title":"英语课","picture":""}]
     */
    private String result;
    private String msg;
    private int code;
    /**
     * id : 1
     * title : 英语课
     * picture :
     */

    private List<InfoBean> info;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

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
