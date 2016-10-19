package com.example.main.shop.Constans;

import java.util.List;

/**
 * Created by Administrator on 2016/10/18 0018.
 */

public class Course extends Result {
    /**
     * \
     * <p>
     * Result	返回结果fail失败suc成功
     * Msg	返回提示信息
     * Code	返回状态
     * 101	获取成功
     * Info	课程
     * Id	课程id
     * Title	标题
     * Picture	图片
     */
    private List<CourseList> info;

    public List<CourseList> getInfo() {
        return info;
    }

    public void setInfo(List<CourseList> info) {
        this.info = info;
    }

    class CourseList {
        private int id;
        private String title;
        private String picture;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }
    }
}
