package com.ljb.service;

import com.ljb.entity.SysUser;

import java.util.List;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/9/14<br>
 * 描述: <br>
 */
public interface ShiroService {

    SysUser findByUsername(String username);

    SysUser findByUsernameAndPassword(String username,String password);

    List<String> roles(Integer userId);

    List<String> permissions(Integer userId);


}
