package com.ljb.util;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/8/30<br>
 * 描述: <br>
 */
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //创建者ID
    @Column(name="create_id")
    private Integer createId;
    //创建时间
    @DateTimeFormat
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false,name = "create_time")
    @org.hibernate.annotations.CreationTimestamp
    private Date createTime;
    //更新者ID
    @Column(name="update_id")
    private Integer updateId;
    //更新时间
    @DateTimeFormat
    @Column(updatable = true,name = "update_time")
    @org.hibernate.annotations.UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    //逻辑删除
    @Column(name = "is_delete")
    private Integer isDelete;

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
