package com.mrlin.miaosha.po;

import java.util.Date;

public class MiaoshaGoods {
    private Long id;
    private Long goodsld;
    private Integer stockCount;
    private Date startDate;
    private Date  endDate;

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsld() {
        return goodsld;
    }

    public void setGoodsld(Long goodsld) {
        this.goodsld = goodsld;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
