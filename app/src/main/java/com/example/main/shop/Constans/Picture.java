package com.example.main.shop.Constans;

import java.util.List;

/**
 * Created by Administrator on 2016/10/13 0013.
 * 轮播图
 */

public class Picture {
    private String result;
    private String msg;
    private int code;
    private List<PictureInfo> info;

    class PictureInfo {
        private String info;

        public String getInfo() {
            return info;
        }
    }
}
