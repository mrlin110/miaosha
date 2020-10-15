package com.mrlin.miaosha.service;

import com.mrlin.miaosha.po.MiaoshaUser;
import com.mrlin.miaosha.vo.input.GoodsVo;
import com.mrlin.miaosha.vo.input.LoginIVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


public interface GoodsService {


     GoodsVo getGoodsVoByGoodsId(long goodsId);

     List<GoodsVo> listGoodsVo();
}
