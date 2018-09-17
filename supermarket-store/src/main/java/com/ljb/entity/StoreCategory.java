package com.ljb.entity;

import com.ljb.util.BaseEntity;
import javax.persistence.*;
import java.util.List;

/**
 * 商品分类实体
 * 表名 store_category
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-15 18:06:42
 */
@Entity
@Table(name="store_category")
public class StoreCategory extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    public  StoreCategory(){

    }

	//名称
	@Column(name = "name")
	private String name;
	//父Id
	@Column(name = "parent_id")
	private Integer parentId;
	//排序
	@Column(name = "order_num")
	private Integer orderNum;
	//图标
	@Column(name = "icon_url")
	private String iconUrl;
	//状态
	@Column(name = "status")
	private Integer status;
	//货架号
	@Column(name = "position_id")
	private String positionId;


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
     * 设置：父Id
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取：父Id
     */
    public Integer getParentId() {
        return parentId;
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
     * 设置：图标
     */
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    /**
     * 获取：图标
     */
    public String getIconUrl() {
        return iconUrl;
    }

    /**
     * 设置：状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置：货架号
     */
    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    /**
     * 获取：货架号
     */
    public String getPositionId() {
        return positionId;
    }
}
