package com.mrlin.miaosha.controller;

import com.mrlin.miaosha.common.Result;
import com.mrlin.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class SampleController {
    @Autowired
    private UserService userService;

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model){
        model.addAttribute("name","mrlin");
        return  "hello";
    }

    @RequestMapping("/getUser")
    @ResponseBody
    public Result  getUser(){
        return Result.success(userService.getById());
    }

    @RequestMapping("/addUser")
    @ResponseBody
    public Result  addUser(){
        userService.addUser();
        return Result.success();
    }


}
