package com.mrlin.miaosha.service;


import com.mrlin.miaosha.vo.output.GoodsVo;

import java.util.List;

public interface GoodsService {


     List<GoodsVo> getGoodsList();

     GoodsVo getGoods(long goodsId);
}
