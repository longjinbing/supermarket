package com.ljb.entity;

import com.ljb.util.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 系统配置信息表实体
 * 表名 sys_config
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
@Entity
@Table(name="sys_config")
public class SysConfig extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    public  SysConfig(){

    }

	//key
	@Column(name = "item")
	private String item;
	//value
	@Column(name = "value")
	private String value;
	//状态   0：隐藏   1：显示
	@Column(name = "status")
	private Integer status;
	//备注
	@Column(name = "remark")
	private String remark;
	//
	@Column(name = "is_delete")
	private Integer isDelete;


    /**
     * 设置：key
     */
    public void setItem(String item) {
        this.item = item;
    }

    /**
     * 获取：key
     */
    public String getItem() {
        return item;
    }

    /**
     * 设置：value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取：value
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置：状态   0：隐藏   1：显示
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：状态   0：隐藏   1：显示
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置：备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取：备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置：
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 获取：
     */
    public Integer getIsDelete() {
        return isDelete;
    }
}
