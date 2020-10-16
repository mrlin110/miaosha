package com.mrlin.miaosha.service.impl;
import com.mrlin.miaosha.dao.GoodsDao;
import com.mrlin.miaosha.dao.MiaoshaUserDao;
import com.mrlin.miaosha.redis.RedisUtils;
import com.mrlin.miaosha.service.GoodsService;
import com.mrlin.miaosha.vo.output.GoodsVo;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;


@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private MiaoshaUserDao miaoshaUserDao;

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private GoodsDao goodsDao;



    @Override
    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
      return goodsDao.getGoodsVoByGoodsId(goodsId);
    }

    @Override
    public List<GoodsVo> listGoodsVo() {
        return goodsDao.listGoodsVo();
    }

    @Override
    public int reduceStock(GoodsVo goods) {
        return goodsDao.reduceStock(goods.getId());
    }


}
