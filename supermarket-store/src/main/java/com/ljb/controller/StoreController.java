package com.ljb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/9/16<br>
 * 描述: <br>
 */
@Controller
@RequestMapping("store")
public class StoreController {

    @RequestMapping("/scan/{type}")
    public String scan(@PathVariable Integer type, Model model){
        model.addAttribute("type", type);
        return "shop/scan";
    }

}
