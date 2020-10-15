package com.mrlin.miaosha.service.impl;

import cn.hutool.core.util.IdUtil;
import com.mrlin.miaosha.common.CodeMsg;
import com.mrlin.miaosha.common.Constant;
import com.mrlin.miaosha.dao.GoodsDao;
import com.mrlin.miaosha.dao.MiaoshaUserDao;
import com.mrlin.miaosha.exception.GlobalException;
import com.mrlin.miaosha.po.MiaoshaUser;
import com.mrlin.miaosha.po.User;
import com.mrlin.miaosha.redis.RedisUtils;
import com.mrlin.miaosha.service.GoodsService;
import com.mrlin.miaosha.service.MiaoshaUserService;
import com.mrlin.miaosha.util.MD5Util;
import com.mrlin.miaosha.vo.input.GoodsVo;
import com.mrlin.miaosha.vo.input.LoginIVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsDao goodsDao;

    @Resource
    private RedisUtils redisUtils;



    @Override
    public List<GoodsVo> listGoodsVo() {
        return goodsDao.listGoodsVo();
    }


    @Override
    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
        return goodsDao.getGoodsVoByGoodsId(goodsId);
    }
}
