package com.mrlin.miaosha.controller;

import com.mrlin.miaosha.common.CodeMsg;
import com.mrlin.miaosha.common.Result;
import com.mrlin.miaosha.service.MiaoshaUserService;
import com.mrlin.miaosha.vo.input.LoginIVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@RequestMapping("/login")
@Controller
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private MiaoshaUserService miaoshaUserService;
    /**
     * 跳转html
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/doLogin")
    @ResponseBody
    public Result<Boolean> doLogin(HttpServletResponse response, @Validated LoginIVo loginIVo){
        return  Result.success( miaoshaUserService.login(response,loginIVo));
    }

}
