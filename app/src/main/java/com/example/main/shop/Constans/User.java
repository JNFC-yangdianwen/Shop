package com.example.main.shop.Constans;

/**
 * Created by Administrator on 2016/10/10 0010.
 */

public class User {
    private String telNumber;//手机号
    private String password;//密码
    private String code;//验证码
    private String inviteCode;//邀请码
    public String getPassword() {
        return password;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

}
