package com.ljb.entity;

import com.ljb.util.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 角色管理实体
 * 表名 sys_role
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
@Entity
@Table(name="sys_role")
public class SysRole extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    public  SysRole(){

    }

	//角色名称
	@Column(name = "name")
	private String name;
	//备注
	@Column(name = "remark")
	private String remark;
	//部门ID
	@Column(name = "dept_id")
	private Integer deptId;


    /**
     * 设置：角色名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：角色名称
     */
    public String getName() {
        return name;
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
     * 设置：部门ID
     */
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    /**
     * 获取：部门ID
     */
    public Integer getDeptId() {
        return deptId;
    }
}
