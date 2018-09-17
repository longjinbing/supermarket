package com.ljb.entity;

import com.ljb.util.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 角色与菜单实体
 * 表名 sys_role_menu
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
@Entity
@Table(name="sys_role_menu")
public class SysRoleMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    public  SysRoleMenu(){

    }

	//角色
	@Column(name = "role_id")
	private Integer roleId;
	//菜单
	@Column(name = "menu_id")
	private Integer menuId;


    /**
     * 设置：角色
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取：角色
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置：菜单
     */
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    /**
     * 获取：菜单
     */
    public Integer getMenuId() {
        return menuId;
    }
}
