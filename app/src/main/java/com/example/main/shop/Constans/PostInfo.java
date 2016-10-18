package com.example.main.shop.Constans;

import java.util.List;

/**
 * Created by Administrator on 2016/10/18 0018.
 * 动态详情
 */

public class PostInfo {

    /**
     * result : fail
     * msg : 参数不能为空
     * code : 102
     * user_photo	用户头像
     * user_name	用户名
     * Time	发布时间
     * Content	内容
     * Picture	图片
     * click_count	点赞数量
     */

    private String result;
    private String msg;
    private int code;
    private String user_photo;
    private String user_name;
    private String time;
    private String content;
    private String picture;
    private int click_count;
    private List<InfoData> info;

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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getClick_count() {
        return click_count;
    }

    public void setClick_count(int click_count) {
        this.click_count = click_count;
    }


    public List<InfoData> getInfo() {
        return info;
    }

    public void setInfo(List<InfoData> info) {
        this.info = info;
    }

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

//    内容
//    send_id	发送人id
//    send_name	发送人姓名
//    send_photo	发送人头像
//    Content	评论内容
//    time	评论时间

    class InfoData {
        private int send_id;
        private String send_name;
        private String send_photo;
        private String content;
        private String time;

        public int getSend_id() {
            return send_id;
        }

        public void setSend_id(int send_id) {
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

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
