package com.ljb.entity;

import com.ljb.util.BaseEntity;
import javax.persistence.*;
import java.util.List;

/**
 * 会员管理实体
 * 表名 store_user
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-15 18:06:02
 */
@Entity
@Table(name="store_user")
public class StoreUser extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    public  StoreUser(){

    }

	//姓名
	@Column(name = "name")
	private String name;
	//家庭住址
	@Column(name = "address")
	private String address;
	//电话
	@Column(name = "phone")
	private String phone;
	//QQ号码
	@Column(name = "qq")
	private String qq;
	//会员等级
	@Column(name = "level")
	private String level;
	//会员状态
	@Column(name = "status")
	private Integer status;

    /**
     * 设置：姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：家庭住址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取：家庭住址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置：电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取：电话
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

    /**
     * 设置：会员等级
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * 获取：会员等级
     */
    public String getLevel() {
        return level;
    }

    /**
     * 设置：会员状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：会员状态
     */
    public Integer getStatus() {
        return status;
    }

}
