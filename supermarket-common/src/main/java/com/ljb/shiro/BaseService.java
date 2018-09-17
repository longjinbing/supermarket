package com.ljb.shiro;

public class BaseService {
	
	
	public BaseService() {

	}

	public Integer userId() {
		return (Integer) ShiroUtils.getSysUserId();
	}
}
