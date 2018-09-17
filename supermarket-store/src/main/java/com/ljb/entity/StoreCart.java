package com.ljb.entity;

import com.ljb.util.BaseEntity;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * 下单处理实体
 * 表名 store_cart
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-15 18:06:33
 */
@Entity
@Table(name="store_cart")
public class StoreCart extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    public  StoreCart(){

    }

	//会员Id
	@Column(name = "user_id")
	private Integer userId;
	//商品Id
	@Column(name = "goods_id")
	private Integer goodsId;
	//产品名称
	@Column(name = "goods_name")
	private String goodsName;
	//市场价
	@Column(name = "market_price")
	private BigDecimal marketPrice;
	//零售价格
	@Column(name = "price")
	private BigDecimal price;
	//数量
	@Column(name = "number")
	private Integer number;
	//商品图片
	@Column(name = "pic_url")
	private String picUrl;

	private Integer type;

	@Column(name="goods_sn")
	private String goodsSn;


    /**
     * 设置：会员Id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取：会员Id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置：商品Id
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取：商品Id
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * 设置：产品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * 获取：产品名称
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 设置：市场价
     */
    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    /**
     * 获取：市场价
     */
    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    /**
     * 设置：零售价格
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取：零售价格
     */
    public BigDecimal getPrice() {
        return price;
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
     * 设置：商品图片
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    /**
     * 获取：商品图片
     */
    public String getPicUrl() {
        return picUrl;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getGoodsSn() {
        return goodsSn;
    }

    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn;
    }
}
