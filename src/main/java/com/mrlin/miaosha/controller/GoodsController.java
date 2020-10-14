package com.mrlin.miaosha.controller;

import com.mrlin.miaosha.po.MiaoshaUser;
import com.mrlin.miaosha.service.GoodsService;
import com.mrlin.miaosha.vo.output.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Description:
 * @Author: ljm
 * @Date: 2020/10/14 16:32
 * @Version: 1.0
 */
@RequestMapping("/goods")
@Controller
public class GoodsController {
   @Autowired
   private GoodsService goodsService;

    @RequestMapping("/toList")
    public String list(Model model, MiaoshaUser user) {
        model.addAttribute("user", user);

       List<GoodsVo> goodsList = goodsService.getGoodsList();
        model.addAttribute("goodsList", goodsList);
        return "goods_list";
    }

    @RequestMapping("/toDetail/{goodsId}")
    public String toDetail(Model model, MiaoshaUser user, @PathVariable("goodsId") long goodsId) {
        model.addAttribute("user", user);

        GoodsVo goods = goodsService.getGoods(goodsId);
        model.addAttribute("goods", goods);

        long startAt = goods.getStartDate().getTime();
        long endAt = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();

        int miaoshaStatus = 0;
        int remainSeconds = 0;
        if(now < startAt ) {//秒杀还没开始，倒计时
            miaoshaStatus = 0;
            remainSeconds = (int)((startAt - now )/1000);
        }else  if(now > endAt){//秒杀已经结束
            miaoshaStatus = 2;
            remainSeconds = -1;
        }else {//秒杀进行中
            miaoshaStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("miaoshaStatus", miaoshaStatus);
        model.addAttribute("remainSeconds", remainSeconds);

        return "goods_detail";
    }

}
