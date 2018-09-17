package com.ljb.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ljb.entity.SysUser;
import com.ljb.shiro.ShiroUtils;

public class AbstractController {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected String getSysUserName() {
		return getSysUser().getUsername();
	}

    protected SysUser getSysUser() {
        return new ShiroUtils().getSysUser();
    }

    protected Integer getSysUserId() {
    	logger.info("获取的用户id："+getSysUser().getId());
        return getSysUser().getId();
    }
}
