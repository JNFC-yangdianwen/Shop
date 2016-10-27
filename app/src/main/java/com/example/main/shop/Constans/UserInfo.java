package com.example.main.shop.Constans;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/10/12 0012.
 * <p>
 * 个人信息
 */


public class UserInfo {

    private final SharedPreferences preferences;
    private static final String PREFS_NAME = "user_info";
    public  int uid;//用户id
    private String sex;//性别
    private String like;//爱好
    private String user_name;//用户名
    private String photo;//头像
    private  static UserInfo  userInfo;

    public  UserInfo(Context context) {
        this.preferences =  context.getApplicationContext().
                getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static UserInfo getInstance() {
        return userInfo;
    }
    public static  void init(Context context) {

        userInfo=new UserInfo(context);
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

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

}
