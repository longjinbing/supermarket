package com.ljb.entity;

import com.ljb.util.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 用户Token实体
 * 表名 token
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
@Entity
@Table(name="token")
public class Token extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    public  Token(){

    }

	//
	@Column(name = "user_id")
	private Integer userId;
	//token
	@Column(name = "token")
	private String token;
	//过期时间
	@Column(name = "expire_time")
	private Date expireTime;


    /**
     * 设置：
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取：
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置：token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 获取：token
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置：过期时间
     */
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * 获取：过期时间
     */
    public Date getExpireTime() {
        return expireTime;
    }
}
