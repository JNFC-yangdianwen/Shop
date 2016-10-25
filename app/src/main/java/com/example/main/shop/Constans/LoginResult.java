package com.example.main.shop.Constans;

/**
 * Created by Administrator on 2016/10/19 0019.
 */

public class LoginResult extends Result {
    private int uid;
    private int type;

    public void setType(int type) {
        this.type = type;
    }

    public int getUid() {
        return uid;
    }
    public int getType() {
        return type;
    }
}
