package com.mrlin.miaosha.service;

import com.mrlin.miaosha.vo.output.GoodsVo;

import java.util.List;


public interface GoodsService {


     GoodsVo getGoodsVoByGoodsId(long goodsId);

     List<GoodsVo> listGoodsVo();

     int reduceStock(GoodsVo goods);
}
