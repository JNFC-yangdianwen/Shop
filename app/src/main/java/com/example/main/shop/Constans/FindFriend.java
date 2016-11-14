package com.example.main.shop.Constans;

import java.util.List;

/**
 * Created by Administrator on 2016/10/18 0018.
 */

public class FindFriend extends Result{
    /*
        Result	返回结果fail失败suc成功
        Msg	提示信息
        Code	返回状态
        102	参数不能为空
        101	查找成功/您查找的用户不存在
        Info	数据
        Id	用户id
        Photo	用户头像
        user_name	用户名
        Account 账号
    */
    public List<FriendInfo> getInfo() {
        return info;
    }
    public void setInfo(List<FriendInfo> info) {
        this.info = info;
    }
    private List<FriendInfo> info;
   public static class FriendInfo {
        private String id;
        private String photo;
        private String user_name;
        private String account;
        public String getId() {
            return id;
        }
        public String getPhoto() {
            return photo;
        }
        public String getUser_name() {
            return user_name;
        }
        public String getAccount() {
            return account;
        }

    }
}