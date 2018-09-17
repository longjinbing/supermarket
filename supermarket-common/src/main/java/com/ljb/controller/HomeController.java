package com.ljb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/9/13<br>
 * 描述: <br>
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(){
        return "index";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/{module}/{page}.html")
    public String router(@PathVariable String module,@PathVariable String page){
        return module+"/"+page;
    }
}
