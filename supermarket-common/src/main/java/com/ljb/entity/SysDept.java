package com.ljb.entity;

import com.ljb.util.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 部门管理实体
 * 表名 sys_dept
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
@Entity
@Table(name="sys_dept")
public class SysDept extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    public  SysDept(){

    }

	//上级部门
	@Column(name = "parent_id")
	private Integer parentId;
	//部门名称
	@Column(name = "name")
	private String name;
	//排序
	@Column(name = "order_num")
	private Integer orderNum;
	//是否删除
	@Column(name = "is_delete")
	private Integer isDelete;


    /**
     * 设置：上级部门
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取：上级部门
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置：部门名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：部门名称
     */
    public String getName() {
        return name;
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
     * 设置：是否删除
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 获取：是否删除
     */
    public Integer getIsDelete() {
        return isDelete;
    }
}
