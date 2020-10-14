package com.mrlin.miaosha.service;

import com.mrlin.miaosha.common.CodeMsg;
import com.mrlin.miaosha.po.MiaoshaUser;
import com.mrlin.miaosha.vo.input.LoginIVo;

import javax.servlet.http.HttpServletResponse;


public interface MiaoshaUserService {

     MiaoshaUser getById(String id);

     void addUser();

     /**
      * 登录验证
      * @return
      */
     Boolean login(HttpServletResponse response,LoginIVo loginIVo);

     /**
      * @Description //获取Token
      * @Date 18:07 2020/10/14
      * @Author ljm
      **/
     MiaoshaUser getByToken(HttpServletResponse response, String token);
}
