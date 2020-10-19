package com.mrlin.miaosha.service;


import com.mrlin.miaosha.po.MiaoshaOrder;
import com.mrlin.miaosha.po.MiaoshaUser;
import com.mrlin.miaosha.po.OrderInfo;
import com.mrlin.miaosha.vo.output.GoodsVo;

public interface OrderService {


    MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(long id, long goodsId);

    OrderInfo createOrder(MiaoshaUser user, GoodsVo goods);

    OrderInfo getOrderById(long orderId);
}
