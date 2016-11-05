package com.example.main.shop.Constans;

import java.util.List;

/**
 * Created by Administrator on 2016/10/28.
 */

public class UserList extends Result {
    public List<UserListInfo> getInfo() {
        return info;
    }

    private List<UserListInfo> info;
    public static class UserListInfo {
        private int uid;
        private String username;
        private String like;

        public int getUid() {
            return uid;
        }

        public String getUsername() {
            return username;
        }

        public String getLike() {
            return like;
        }
    }
}
