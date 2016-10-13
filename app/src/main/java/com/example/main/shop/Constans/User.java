package com.example.main.shop.Constans;

/**
 * Created by Administrator on 2016/10/10 0010.
 * 用户实体类
 */

public class User {

    private String mobile;//手机号
    private String pwd;//密码
    private String code;//验证码
    private String invite_code;//邀请码

    //获取验证码时调用该构造函数
    public User(String mobile,int type) {
        this.mobile = mobile;
    }
 //注册时调用该构造函数
    public User(String mobile, String pwd, String code, String inve_code) {
        this.mobile = mobile;
        this.pwd = pwd;
        this.code = code;
        this.invite_code = invite_code;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInvite_code() {
        return invite_code;
    }

    public void setInvite_code(String invite_code) {
        this.invite_code = invite_code;
    }
}
