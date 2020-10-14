package com.mrlin.miaosha.dao;

import com.mrlin.miaosha.po.MiaoshaUser;
import com.mrlin.miaosha.vo.output.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodsDao {
    @Select("select g.*,mg.miaosha_price,mg.stock_count,mg.start_date,mg.end_date from miaosha_goods mg left join goods g  on mg.goods_id = g.id ")
    List<GoodsVo> getGoodsList();
    @Select("select g.*,mg.miaosha_price,mg.stock_count,mg.start_date,mg.end_date from miaosha_goods mg left join goods g  on mg.goods_id = g.id  where g.goods_id=#{goodsId}")
    GoodsVo getGoods(@Param("goodsId") long goodsId);


}
