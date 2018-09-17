package com.ljb.entity;

import com.ljb.util.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户与角色实体
 * 表名 sys_user_role
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
@Entity
@Table(name="sys_user_role")
public class SysUserRole extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    public  SysUserRole(){

    }

	//用户ID
	@Column(name = "user_id")
	private Integer userId;
	//角色ID
	@Column(name = "role_id")
	private Integer roleId;


    /**
     * 设置：用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取：用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置：角色ID
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取：角色ID
     */
    public Integer getRoleId() {
        return roleId;
    }
}
