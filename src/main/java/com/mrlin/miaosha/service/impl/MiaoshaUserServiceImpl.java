package com.mrlin.miaosha.service.impl;

import cn.hutool.core.util.IdUtil;
import com.mrlin.miaosha.common.CodeMsg;
import com.mrlin.miaosha.common.Constant;
import com.mrlin.miaosha.dao.MiaoshaUserDao;
import com.mrlin.miaosha.exception.GlobalException;
import com.mrlin.miaosha.po.MiaoshaUser;
import com.mrlin.miaosha.po.User;
import com.mrlin.miaosha.redis.RedisUtils;
import com.mrlin.miaosha.service.MiaoshaUserService;
import com.mrlin.miaosha.service.UserService;
import com.mrlin.miaosha.util.MD5Util;
import com.mrlin.miaosha.vo.input.LoginIVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class MiaoshaUserServiceImpl implements MiaoshaUserService {

    @Resource
    private MiaoshaUserDao miaoshaUserDao;

    @Resource
    private RedisUtils redisUtils;

    @Override
    public MiaoshaUser getById(String mobile) {
        return    miaoshaUserDao.getById(mobile);
    }

    @Override
    public void addUser() {
        User user =new User(2,"bbc");
        redisUtils.set("user1",user);
    }
    @Override
    public MiaoshaUser getByToken(HttpServletResponse response, String token) {
        if(StringUtils.isEmpty(token)) {
            return null;
        }
        MiaoshaUser user = (MiaoshaUser)redisUtils.get(Constant.RedisKey.TOKEN_KEY+token);
        //延长有效期
        if(user != null) {
            //重新生成Cookie
            addCookie(response,token,user);
        }
        return user;
    }
    @Override
    public Boolean login(HttpServletResponse response,LoginIVo loginIVo) {
        MiaoshaUser miaoshaUser =  miaoshaUserDao.getById(loginIVo.getMobile());
        if(ObjectUtils.isEmpty(miaoshaUser)){
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST) ;
        }
        String salt = miaoshaUser.getSalt();
        String pwd = miaoshaUser.getPwd();
        String pwddb = MD5Util.formPassToDBPass(loginIVo.getPassword(),salt);
        if(pwd.equals(pwddb)){
            //校验成功
            String simpleUUID = IdUtil.simpleUUID();
            addCookie(response,simpleUUID,miaoshaUser);

            return  true;
        }else{
            throw new GlobalException(CodeMsg.PASSWORD_ERROR) ;
        }
    }
    private void addCookie(HttpServletResponse response,String simpleUUID,MiaoshaUser user) {

        redisUtils.set(Constant.RedisKey.TOKEN_KEY+simpleUUID,user,Constant.RedisKey.TOKEN_EXPIRE);
        Cookie cookie = new Cookie(Constant.COOKI_NAME_TOKEN, simpleUUID);
        cookie.setMaxAge(Constant.RedisKey.TOKEN_EXPIRE);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
