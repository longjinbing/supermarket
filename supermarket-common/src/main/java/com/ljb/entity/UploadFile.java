package com.ljb.entity;

import com.ljb.util.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 上传文件实体
 * 表名 upload_file
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
@Entity
@Table(name="upload_file")
public class UploadFile extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    public  UploadFile(){

    }

	//路径
	@Column(name = "url")
	private String url;
	//大小
	@Column(name = "size")
	private Integer size;
	//类型
	@Column(name = "type")
	private String type;
	//格式
	@Column(name = "ext")
	private String ext;


    /**
     * 设置：路径
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取：路径
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置：大小
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * 获取：大小
     */
    public Integer getSize() {
        return size;
    }

    /**
     * 设置：类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取：类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置：格式
     */
    public void setExt(String ext) {
        this.ext = ext;
    }

    /**
     * 获取：格式
     */
    public String getExt() {
        return ext;
    }
}
