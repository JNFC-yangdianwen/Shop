package com.example.main.shop.Constans;

import java.util.List;

/**
 * Created by Administrator on 2016/10/18 0018.
 * 提现账户
 */

public class CashAccount extends Result {

    private List<ZhiFuBao> zhifubao;
    private List<WeiXin> wexin;
    private List<Bank> bank;
    public List<ZhiFuBao> getZhifubao() {
        return zhifubao;
    }

    public void setZhifubao(List<ZhiFuBao> zhifubao) {
        this.zhifubao = zhifubao;
    }

    public List<WeiXin> getWexin() {
        return wexin;
    }

    public void setWexin(List<WeiXin> wexin) {
        this.wexin = wexin;
    }

    public List<Bank> getBank() {
        return bank;
    }

    public void setBank(List<Bank> bank) {
        this.bank = bank;
    }



    class Card {
        private String user_name;
        private String card;

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getCard() {
            return card;
        }

        public void setCard(String card) {
            this.card = card;
        }
    }

    class ZhiFuBao extends Card {

    }

    class WeiXin extends Card {

    }

    class Bank extends Card {
        private String bank;

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }
    }
}
