package com.mrlin.miaosha.service.impl;

import com.mrlin.miaosha.common.CodeMsg;
import com.mrlin.miaosha.dao.MiaoshaDao;
import com.mrlin.miaosha.dao.OrderDao;
import com.mrlin.miaosha.exception.GlobalException;
import com.mrlin.miaosha.po.MiaoshaOrder;
import com.mrlin.miaosha.po.MiaoshaUser;
import com.mrlin.miaosha.po.OrderInfo;
import com.mrlin.miaosha.redis.RedisUtils;
import com.mrlin.miaosha.service.GoodsService;
import com.mrlin.miaosha.service.MiaoshaService;
import com.mrlin.miaosha.service.OrderService;
import com.mrlin.miaosha.vo.output.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service
public class MiaoshaServiceImpl implements MiaoshaService {
    @Resource
    private MiaoshaDao miaoshaDao;
    @Resource
    private RedisUtils redisUtils;

    @Autowired
    OrderService orderService;

    @Autowired
    GoodsService goodsService;

    @Override
    @Transactional
    public OrderInfo miaosha(MiaoshaUser user, GoodsVo goods) {
        //1、扣库存 返回值
        int value =  goodsService.reduceStock(goods);
        if(value!=1){
            throw new GlobalException(CodeMsg.MIAO_SHA_OVER) ;
        }

        //2、生产秒杀订单 和 订单

        return orderService.createOrder(user, goods);
    }
}
