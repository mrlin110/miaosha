package com.mrlin.miaosha.controller;

import com.mrlin.miaosha.common.Constant;
import com.mrlin.miaosha.common.Result;
import com.mrlin.miaosha.po.MiaoshaUser;
import com.mrlin.miaosha.redis.RedisUtils;
import com.mrlin.miaosha.service.GoodsService;
import com.mrlin.miaosha.vo.output.GoodsDetailVo;
import com.mrlin.miaosha.vo.output.GoodsVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    GoodsService goodsService;

    @Resource
    private RedisUtils redisUtils;

    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;

//    @RequestMapping("/to_list")
//    public String list(Model model, MiaoshaUser user) {
//        model.addAttribute("user", user);
//        //查询商品列表
//        List<GoodsVo> goodsList = goodsService.listGoodsVo();
//        model.addAttribute("goodsList", goodsList);
//        return "goods_list";
//    }

    /**
     * 页面缓存
     * @param model
     * @param user
     * @return
     */
    @RequestMapping(value = "/to_list" ,produces = "text/html")
    @ResponseBody
    public String list(Model model, MiaoshaUser user, HttpServletRequest request,
                       HttpServletResponse response) {
        model.addAttribute("user", user);


        //1.从redis缓存中查询
        String listHtml = (String) redisUtils.get(Constant.RedisKey.GOODS_LIST_KEY);
        if(StringUtils.isNotEmpty(listHtml)){
            return  listHtml;
        }
        //查询商品列表
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        model.addAttribute("goodsList", goodsList);
        //页面缓存的核心东西,渲染页面
        IWebContext ctx =new WebContext(request,response,
                request.getServletContext(),request.getLocale(),model.asMap());
        //配置对应页面
        listHtml  = thymeleafViewResolver.getTemplateEngine().process("goods_list",ctx);
        //3.将手动渲染后的html存入redis缓存
        if(StringUtils.isNotEmpty(listHtml)){
            redisUtils.set(Constant.RedisKey.GOODS_LIST_KEY,listHtml,Constant.RedisKey.HTML_EXPIRE);
        }
        return listHtml;
    }



    @RequestMapping(value = "/to_detail2/{goodsId}" , produces = "text/html")
    @ResponseBody
    public String detail2(Model model,MiaoshaUser user,
                         @PathVariable("goodsId")long goodsId, HttpServletRequest request,
                         HttpServletResponse response) {
        model.addAttribute("user", user);

        //1.从redis缓存中查询
        String listHtml = (String) redisUtils.get(Constant.RedisKey.GOODS_DETAIL_KEY+goodsId+":");
        if(StringUtils.isNotEmpty(listHtml)){
            return  listHtml;
        }

        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
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

        //页面缓存的核心东西,渲染页面
        IWebContext ctx =new WebContext(request,response,
                request.getServletContext(),request.getLocale(),model.asMap());
        //配置对应页面
        listHtml  = thymeleafViewResolver.getTemplateEngine().process("goods_detail",ctx);
        //3.将手动渲染后的html存入redis缓存
        if(StringUtils.isNotEmpty(listHtml)){
            redisUtils.set(Constant.RedisKey.GOODS_DETAIL_KEY+goodsId+":",listHtml,Constant.RedisKey.HTML_EXPIRE);
        }
        return listHtml;
    }

    @RequestMapping(value="/detail/{goodsId}")
    @ResponseBody
    public Result<GoodsDetailVo> detail(HttpServletRequest request, HttpServletResponse response, Model model, MiaoshaUser user,
                                        @PathVariable("goodsId")long goodsId) {
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
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
        GoodsDetailVo vo = new GoodsDetailVo();
        vo.setGoods(goods);
        vo.setUser(user);
        vo.setRemainSeconds(remainSeconds);
        vo.setMiaoshaStatus(miaoshaStatus);
        return Result.success(vo);
    }


}
