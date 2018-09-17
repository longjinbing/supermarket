package com.ljb.entity;

import com.ljb.util.BaseEntity;
import javax.persistence.*;
import java.util.List;

/**
 * 供应商管理实体
 * 表名 store_supplier
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-15 18:06:01
 */
@Entity
@Table(name="store_supplier")
public class StoreSupplier extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    public  StoreSupplier(){

    }

	//供应商名称
	@Column(name = "name")
	private String name;
	//联系人
	@Column(name = "username")
	private String username;
	//地址
	@Column(name = "address")
	private String address;
	//联系电话
	@Column(name = "phone")
	private String phone;
	//QQ号码
	@Column(name = "qq")
	private String qq;


    /**
     * 设置：供应商名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：供应商名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：联系人
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取：联系人
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置：地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取：地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置：联系电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取：联系电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置：QQ号码
     */
    public void setQq(String qq) {
        this.qq = qq;
    }

    /**
     * 获取：QQ号码
     */
    public String getQq() {
        return qq;
    }

}
