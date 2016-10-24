package com.example.main.shop.Constans;

/**
 * Created by Administrator on 2016/10/24 0024.
 * 发表动态的类
 */

public class ReleaseDynamic {
    private int id;// 用户id
    private int is_share;//是否分享有奖，有奖为1，无奖为2
    private String content;//内容
    private String picture;//图片
    private int count;//有奖数量
    private double money_one;//每个钱数

    public ReleaseDynamic(int id, String content, String picture, int is_share, double money, int count) {
        this.id = id;
        this.content = content;
        this.picture = picture;
        this.is_share = is_share;
        this.money_one = money;
        this.count = count;
    }
}
