package com.mrlin.miaosha.controller;


import com.mrlin.miaosha.common.CodeMsg;
import com.mrlin.miaosha.common.Result;
import com.mrlin.miaosha.po.MiaoshaUser;
import com.mrlin.miaosha.po.OrderInfo;
import com.mrlin.miaosha.redis.RedisUtils;
import com.mrlin.miaosha.service.GoodsService;
import com.mrlin.miaosha.service.MiaoshaUserService;
import com.mrlin.miaosha.service.OrderService;
import com.mrlin.miaosha.vo.output.GoodsVo;
import com.mrlin.miaosha.vo.output.OrderDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	MiaoshaUserService userService;
	

	@Resource
	private RedisUtils redisUtils;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	GoodsService goodsService;
	
    @RequestMapping("/detail")
    @ResponseBody
    public Result<OrderDetailVo> info(Model model, MiaoshaUser user,
									  @RequestParam("orderId") long orderId) {
    	if(user == null) {
    		return Result.error(CodeMsg.SESSION_ERROR);
    	}
    	OrderInfo order = orderService.getOrderById(orderId);
    	if(order == null) {
    		return Result.error(CodeMsg.ORDER_NOT_EXIST);
    	}
    	long goodsId = order.getGoodsId();
    	GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
    	OrderDetailVo vo = new OrderDetailVo();
    	vo.setOrder(order);
    	vo.setGoods(goods);
    	return Result.success(vo);
    }
    
}
