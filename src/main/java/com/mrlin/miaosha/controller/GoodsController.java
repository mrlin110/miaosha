package com.mrlin.miaosha.controller;

import com.mrlin.miaosha.po.MiaoshaUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @Author: ljm
 * @Date: 2020/10/14 16:32
 * @Version: 1.0
 */
@RequestMapping("/goods")
@Controller
public class GoodsController {

    @RequestMapping("/to_list")
    public String list(Model model, MiaoshaUser user) {
        model.addAttribute("user", user);
        return "goods_list";
    }

}
