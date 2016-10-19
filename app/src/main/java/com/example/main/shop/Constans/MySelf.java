package com.example.main.shop.Constans;

import java.util.List;

/**
 * Created by Administrator on 2016/10/18 0018.
 * 我的
 */

public class MySelf extends Result {
    /**
     *Result	返回结果fail失败suc成功
     Msg	提示信息
     Code	返回状态
     102	参数不能为空
     101	获取成功
     Info	信息
     Photo	用户头像
     user_name	用户名
     Like	爱好
     *
     */
    private List<UserInfo> info;

    public List<UserInfo> getInfo() {
        return info;
    }

    public void setInfo(List<UserInfo> info) {
        this.info = info;
    }
}
