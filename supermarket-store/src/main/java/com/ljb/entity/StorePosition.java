package com.ljb.entity;

import com.ljb.util.BaseEntity;
import javax.persistence.*;
import java.util.List;

/**
 * 货架管理实体
 * 表名 store_position
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-15 18:06:21
 */
@Entity
@Table(name="store_position")
public class StorePosition extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    public  StorePosition(){

    }

	//货架名称
	@Column(name = "name")
	private String name;
	//货架位置
	@Column(name = "remark")
	private String remark;
	//货架编号
	@Column(name = "position_sn")
	private String positionSn;


    /**
     * 设置：货架名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：货架名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：货架位置
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取：货架位置
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置：货架编号
     */
    public void setPositionSn(String positionSn) {
        this.positionSn = positionSn;
    }

    /**
     * 获取：货架编号
     */
    public String getPositionSn() {
        return positionSn;
    }
}
