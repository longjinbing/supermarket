package com.ljb.entity;

import com.ljb.util.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 角色与部门对应关系实体
 * 表名 sys_role_dept
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
@Entity
@Table(name="sys_role_dept")
public class SysRoleDept extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    public  SysRoleDept(){

    }

	//角色
	@Column(name = "role_id")
	private Integer roleId;
	//部门
	@Column(name = "dept_id")
	private Integer deptId;


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
     * 设置：部门
     */
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    /**
     * 获取：部门
     */
    public Integer getDeptId() {
        return deptId;
    }
}
