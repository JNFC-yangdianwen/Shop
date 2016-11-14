package com.example.main.shop.Constans;

/**
 * Created by Administrator on 2016/11/7.
 */

public class User1  {
    private String uid;
    private String like;
    private String photo;
private static User1 user1;
    public static User1 getInstance() {
        if (user1 == null) {
            user1=new User1();
        }
        return user1;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
