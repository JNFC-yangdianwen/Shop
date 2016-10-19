package com.example.main.shop.Constans;

import java.util.List;

/**
 * Created by Administrator on 2016/10/18 0018.
 * 订单
 */

public class Order extends Result {
    /**
     * Result	返回结果fail失败suc成功
     * Msg	提示信息
     * Code	返回状态
     * 102	参数不能为空
     * 101	获取成功
     * Info	我的订单
     * Title	标题
     * Picture	图片
     * order_no	订单号
     * Type 	订单状态  2下单中  1支付成功  3已完成
     * Id	课程或订单id
     */

    private List<OrderInfo> info;

    public List<OrderInfo> getInfo() {
        return info;
    }

    class OrderInfo {
        private String title;
        private String picre;
        private String order_no;
        private int type;
        private int id;
        public String getTitle() {
            return title;
        }
        public String getPicre() {
            return picre;
        }
        public String getOrder_no() {
            return order_no;
        }
        public int getType() {
            return type;
        }
        public int getId() {
            return id;
        }
    }

}
