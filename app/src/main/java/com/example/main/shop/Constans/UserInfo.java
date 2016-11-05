package com.example.main.shop.Constans;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/10/12 0012.
 * <p>
 * 用于保存用户数据
 */
public class UserInfo {
    private final SharedPreferences preferences;
    private static final String PREFS_NAME = "user_info";
    private  static UserInfo  userInfo;
    private static final String KEY_UID= "uid";//id
    private String photo;
    private String sex;
    private String like;

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

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    private String user_name;
    private String sign;
    public  UserInfo(Context context) {
        preferences =  context.getApplicationContext().
                getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }
    public static UserInfo getInstance() {
        return userInfo;
    }
    public static  void init(Context context) {

        userInfo=new UserInfo(context);
    }

    public String getUid() {
        return preferences.getString(KEY_UID,"");
    }

    public void setUid(String uid) {
        preferences.edit().putString(KEY_UID,uid);
    }
}
