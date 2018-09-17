package com.ljb.controller;

import com.ljb.entity.SysUser;
import com.ljb.service.SysUserService;
import com.ljb.util.DateUtils;
import com.ljb.util.R;
import com.ljb.util.SHA256Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/9/12<br>
 * 描述: <br>
 */
@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "/login")
    public String login(){
        return "account/login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public R login(@RequestParam String username,@RequestParam String password,@RequestParam Boolean remember,@RequestParam(required = false) String url){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, SHA256Util.getSHA256Str(password));
        token.setRememberMe(remember);
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            return R.error("用户名或密码错误");
        } catch (IncorrectCredentialsException e) {
            return R.error("用户名或密码错误");
        } catch (LockedAccountException lae ) {
            return R.error("账户已锁定，请联系管理员");
        } catch (ExcessiveAttemptsException eae ) {
            return R.error("尝试登录次数超限，请稍后尝试");
        }
        return R.ok("登录成功");
    }


    @RequestMapping(value = "/register")
    public String register(){
        return "account/register";
    }

    @RequestMapping(value="/register",method=RequestMethod.POST)
    public @ResponseBody R Register(@RequestBody SysUser sysUser) {
        if(sysUserService.findByUsername(sysUser.getUsername())!=null) {
            return R.error("用户名已被使用");
        }
        sysUser.setStatus(1);
        sysUser.setCreateTime(DateUtils.currentTime());
        sysUser.setPassword(SHA256Util.getSHA256Str(sysUser.getPassword()));
        if(sysUserService.save(sysUser).getId()<=0)
            return R.error("注册失败");
        return R.ok("注册成功");
    }

    @RequestMapping(value="/logout",method=RequestMethod.GET)
    public String logout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return "redirect:/account/login";
    }


    @RequestMapping(value="/isexist",method=RequestMethod.GET)
    @ResponseBody
    public R isExist(@RequestParam String username) {
        if(sysUserService.findByUsername(username)==null) {
            return R.ok("√");
        }else{
            return R.error(1, "用户名已存在");
        }
    }

}
