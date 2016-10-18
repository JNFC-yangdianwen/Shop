package com.example.main.shop.Constans;

import java.util.List;

/**
 * Created by Administrator on 2016/10/18 0018.
 * 课程的详情
 */

public class CourseInfo {
    /**
     * 出参	参数
     * Result	返回结果fail失败suc成功
     * Msg	提示信息
     * Code	返回状态
     * 102	参数不能为空
     * 101	获取成功
     * Info	课程
     * Picture	图片
     * Title	标题
     * Money	金额
     * Time	课程时间
     * Teacher	讲师
     * Intro	介绍
     * Mobile	联系电话
     * Address	地址
     */
    private String result;
    private String msg;
    private int code;
    private List<CourseData> info;

    class CourseData {
        private String title;
        private String picture;
        private String time;
        private String teacher;
        private String intro;
        private String mobile;
        private String address;
    }
}
