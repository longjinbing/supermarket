package com.ljb.exception;

public class AccountException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2115764874318021171L;
	
	private String msg;
	
	public AccountException(String msg) {
		super(msg);
		this.msg=msg;
	}
	
	public String getMsg() {
		return this.msg;
	}
}
