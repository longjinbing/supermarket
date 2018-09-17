package com.ljb.entity;

import com.ljb.util.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 全球地址实体
 * 表名 sys_region
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
@Entity
@Table(name="sys_region")
public class SysRegion extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    public  SysRegion(){

    }

	//
	@Column(name = "parent_id")
	private Integer parentId;
	//
	@Column(name = "name")
	private String name;
	//
	@Column(name = "type")
	private Integer type;
	//
	@Column(name = "agency_id")
	private Integer agencyId;


    /**
     * 设置：
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取：
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置：
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取：
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置：
     */
    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    /**
     * 获取：
     */
    public Integer getAgencyId() {
        return agencyId;
    }
}
