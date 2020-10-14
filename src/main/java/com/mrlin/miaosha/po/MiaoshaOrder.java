package com.mrlin.miaosha.po;

public class MiaoshaOrder {
    private Long id;
    private Long userld;
    private Long orderld;
    private Long goodsld;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserld() {
        return userld;
    }

    public void setUserld(Long userld) {
        this.userld = userld;
    }

    public Long getOrderld() {
        return orderld;
    }

    public void setOrderld(Long orderld) {
        this.orderld = orderld;
    }

    public Long getGoodsld() {
        return goodsld;
    }

    public void setGoodsld(Long goodsld) {
        this.goodsld = goodsld;
    }
}
