package com.mrlin.miaosha.po;

import java.util.Date;

public class OrderInfo {
    private Long id;
    private Long userld;
    private Long goodsld;
    private Long deliveryAddrld;
    private String goodsName;
    private Integer goodsCount;
    private Double goodsPrice;
    private Integer orderChannel;
    private Integer status;
    private Date createDate;
    private Date payDate;

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

    public Long getGoodsld() {
        return goodsld;
    }

    public void setGoodsld(Long goodsld) {
        this.goodsld = goodsld;
    }

    public Long getDeliveryAddrld() {
        return deliveryAddrld;
    }

    public void setDeliveryAddrld(Long deliveryAddrld) {
        this.deliveryAddrld = deliveryAddrld;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getOrderChannel() {
        return orderChannel;
    }

    public void setOrderChannel(Integer orderChannel) {
        this.orderChannel = orderChannel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }
}
