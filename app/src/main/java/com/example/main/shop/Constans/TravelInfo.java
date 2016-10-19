package com.example.main.shop.Constans;

import java.util.List;

/**
 * Created by Administrator on 2016/10/18 0018.
 */

public class TravelInfo extends Result{
    /**
     * Result	返回结果fail失败suc 成功
     * Msg	提示信息
     * Code	返回状态
     * 102	参数不能为空
     * 101	获取成功
     * Info	详情
     * Picture	图片
     * Title	标题
     * Money	金额
     * Mobile	手机号
     * Address	地址
     * Intro 	介绍
     */
    private List<DataInfo> info;

    public List<DataInfo> getInfo() {
        return info;
    }

    class DataInfo {
        private String picture;
        private String title;
        private double money;
        private String mobile;
        private String address;
        private String intro;

        public String getPicture() {
            return picture;
        }

        public String getTitle() {
            return title;
        }

        public double getMoney() {
            return money;
        }

        public String getMobile() {
            return mobile;
        }

        public String getAddress() {
            return address;
        }

        public String getIntro() {
            return intro;
        }
    }
}
