package com.ljb.entity;

import com.ljb.util.BaseEntity;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * 订单管理实体
 * 表名 store_order
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-15 18:06:33
 */
@Entity
@Table(name="store_order")
public class StoreOrder extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    public  StoreOrder(){

    }

	//订单号
	@Column(name = "order_sn")
	private String orderSn;
	//用户
	@Column(name = "user_id")
	private Integer userId;
	//状态
	@Column(name = "status")
	private Integer status;
	//结算价格
	@Column(name = "order_price")
	private BigDecimal orderPrice;
	//商品总价
	@Column(name = "goods_price")
	private BigDecimal goodsPrice;
	//总订单Id
	@Column(name = "parent_id")
	private Integer parentId;


    /**
     * 设置：订单号
     */
    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    /**
     * 获取：订单号
     */
    public String getOrderSn() {
        return orderSn;
    }

    /**
     * 设置：用户
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取：用户
     */
    public Integer getUserId() {
        return userId;
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
     * 设置：结算价格
     */
    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    /**
     * 获取：结算价格
     */
    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    /**
     * 设置：商品总价
     */
    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    /**
     * 获取：商品总价
     */
    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    /**
     * 设置：总订单Id
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取：总订单Id
     */
    public Integer getParentId() {
        return parentId;
    }
}
