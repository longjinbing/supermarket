package com.ljb.entity;

import com.ljb.util.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 系统用户实体
 * 表名 sys_user
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
@Entity
@Table(name="sys_user")
public class SysUser extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    public  SysUser(){

    }

	//用户名
	@Column(name = "username")
	private String username;
	//密码
	@Column(name = "password")
	private String password;
	//邮箱
	@Column(name = "email")
	private String email;
	//手机号
	@Column(name = "mobile")
	private String mobile;
	//状态  0：禁用   1：正常
	@Column(name = "status")
	private Integer status;


    /**
     * 设置：用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取：用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置：密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取：密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置：邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取：邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置：手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取：手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置：状态  0：禁用   1：正常
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：状态  0：禁用   1：正常
     */
    public Integer getStatus() {
        return status;
    }
}
