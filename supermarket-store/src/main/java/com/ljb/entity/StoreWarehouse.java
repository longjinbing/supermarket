package com.ljb.entity;

import com.ljb.util.BaseEntity;
import javax.persistence.*;
import java.util.List;

/**
 * 仓库管理实体
 * 表名 store_warehouse
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-15 18:06:12
 */
@Entity
@Table(name="store_warehouse")
public class StoreWarehouse extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    public  StoreWarehouse(){

    }

	//名称
	@Column(name = "name")
	private String name;
	//位置
	@Column(name = "remark")
	private String remark;


    /**
     * 设置：名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：位置
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取：位置
     */
    public String getRemark() {
        return remark;
    }
}
