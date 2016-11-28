package com.example.main.shop.Constans;

/**
 * Created by Administrator on 2016/10/18 0018.
 * 课程的详情
 */

public class CourseInfo extends Result {

    /**
     * picture :
     * title : 英语课
     * money : 200.00
     * time : 55分钟
     * teacher : 张新玲
     * intro : 美式英语祝你不不
     * mobile : 15636984562
     * adress : 济南市
     */

    private InfoBean info;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean {
        private String picture;
        private String title;
        private String money;
        private String time;
        private String teacher;
        private String intro;
        private String mobile;
        private String adress;

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTeacher() {
            return teacher;
        }

        public void setTeacher(String teacher) {
            this.teacher = teacher;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAdress() {
            return adress;
        }

        public void setAdress(String adress) {
            this.adress = adress;
        }
    }
}
