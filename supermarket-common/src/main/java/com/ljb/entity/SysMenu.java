package com.ljb.entity;

import com.ljb.util.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 菜单管理实体
 * 表名 sys_menu
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
@Entity
@Table(name="sys_menu")
public class SysMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    public  SysMenu(){

    }

	//父菜单ID，一级菜单为0
	@Column(name = "parent_id")
	private Integer parentId;
	//菜单名称
	@Column(name = "name")
	private String name;
	//菜单URL
	@Column(name = "url")
	private String url;
	//授权(多个用逗号分隔，如：user:list,user:create)
	@Column(name = "perms")
	private String perms;
	//类型   0：目录   1：菜单   2：按钮
	@Column(name = "type")
	private Integer type;
	//菜单图标
	@Column(name = "icon")
	private String icon;
	//排序
	@Column(name = "order_num")
	private Integer orderNum;
	//
	@Column(name = "status")
	private Integer status;


    /**
     * 设置：父菜单ID，一级菜单为0
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取：父菜单ID，一级菜单为0
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置：菜单名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：菜单名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：菜单URL
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取：菜单URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置：授权(多个用逗号分隔，如：user:list,user:create)
     */
    public void setPerms(String perms) {
        this.perms = perms;
    }

    /**
     * 获取：授权(多个用逗号分隔，如：user:list,user:create)
     */
    public String getPerms() {
        return perms;
    }

    /**
     * 设置：类型   0：目录   1：菜单   2：按钮
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取：类型   0：目录   1：菜单   2：按钮
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置：菜单图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取：菜单图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置：排序
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 获取：排序
     */
    public Integer getOrderNum() {
        return orderNum;
    }

    /**
     * 设置：
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：
     */
    public Integer getStatus() {
        return status;
    }
}
