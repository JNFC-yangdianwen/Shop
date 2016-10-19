package com.example.main.shop.Constans;

import java.util.List;

/**
 * Created by Administrator on 2016/10/18 0018.
 */

public class FriendList extends Result{
    /**
     * 出参	参数
     * Result 	返回结果fail失败suc成功
     * Msg	提示信息
     * Code	返回状态
     * 102	参数不能为空
     * 101	获取成功
     * Info	好友
     * user_name	用户名
     * Photo	用户头像
     * Id	用户id
     */
    public List<FriendInfo> getInfo() {
        return info;
    }

    public void setInfo(List<FriendInfo> info) {
        this.info = info;
    }

    private int code;
    private List<FriendInfo> info;

    class FriendInfo {
        private String user_name;
        private String photo;
        private int id;

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
