package com.ljb.entity;

import com.ljb.util.BaseEntity;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * 商品入库实体
 * 表名 store_inwarehouse
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-15 18:06:42
 */
@Entity
@Table(name="store_inwarehouse")
public class StoreInwarehouse extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    public  StoreInwarehouse(){

    }

	//商品id
	@Column(name = "goods_id")
	private Integer goodsId;
	//所在仓库
	@Column(name = "warehouse_id")
	private Integer warehouseId;
	//数量
	@Column(name = "number")
	private Integer number;
	//进货价格
	@Column(name = "price")
	private BigDecimal price;
	//供应商
	@Column(name = "supplier_id")
	private Integer supplierId;


    /**
     * 设置：商品id
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取：商品id
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * 设置：所在仓库
     */
    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    /**
     * 获取：所在仓库
     */
    public Integer getWarehouseId() {
        return warehouseId;
    }

    /**
     * 设置：数量
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * 获取：数量
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 设置：进货价格
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取：进货价格
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置：供应商
     */
    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    /**
     * 获取：供应商
     */
    public Integer getSupplierId() {
        return supplierId;
    }
}
