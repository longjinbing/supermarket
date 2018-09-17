package com.ljb.entity;

import com.ljb.util.BaseEntity;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * 商品管理实体
 * 表名 store_goods
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-15 18:06:42
 */
@Entity
@Table(name="store_goods")
public class StoreGoods extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    public  StoreGoods(){

    }

	//分类ID
	@Column(name = "category_id")
	private Integer categoryId;
	//商品编号
	@Column(name = "goods_sn")
	private String goodsSn;
	//标题
	@Column(name = "title")
	private String title;
	//商品描述
	@Column(name = "goods_remark")
	private String goodsRemark;
	//商品主图
	@Column(name = "pic_url")
	private String picUrl;
	//销售量
	@Column(name = "number")
	private Integer number;
	//特点
	@Column(name = "remark")
	private String remark;
	//价格
	@Column(name = "price")
	private BigDecimal price;
	//市场价格
	@Column(name = "market_price")
	private BigDecimal marketPrice;
	//
	@Column(name = "goods_desc")
	private String goodsDesc;


    /**
     * 设置：分类ID
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取：分类ID
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * 设置：商品编号
     */
    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn;
    }

    /**
     * 获取：商品编号
     */
    public String getGoodsSn() {
        return goodsSn;
    }

    /**
     * 设置：标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取：标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置：商品描述
     */
    public void setGoodsRemark(String goodsRemark) {
        this.goodsRemark = goodsRemark;
    }

    /**
     * 获取：商品描述
     */
    public String getGoodsRemark() {
        return goodsRemark;
    }

    /**
     * 设置：商品主图
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    /**
     * 获取：商品主图
     */
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * 设置：销售量
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * 获取：销售量
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 设置：特点
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取：特点
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置：价格
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取：价格
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置：市场价格
     */
    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    /**
     * 获取：市场价格
     */
    public BigDecimal getMarketPrice() {
        return marketPrice;
    }


    /**
     * 设置：
     */
    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    /**
     * 获取：
     */
    public String getGoodsDesc() {
        return goodsDesc;
    }
}
