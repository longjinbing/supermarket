package com.ljb.entity;

import com.ljb.util.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 短信日志实体
 * 表名 sys_sms_log
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-09-12 17:05:07
 */
@Entity
@Table(name="sys_sms_log")
public class SysSmsLog extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    public  SysSmsLog(){

    }

	//操作人
	@Column(name = "user_id")
	private Integer userId;
	//必填参数。发送内容（1-500 个汉字）UTF-8编码
	@Column(name = "content")
	private String content;
	//必填参数。手机号码。多个以英文逗号隔开
	@Column(name = "mobile")
	private String mobile;
	//必填参数。用户签名
	@Column(name = "sign")
	private String sign;
	//必填参数。固定值 pt
	@Column(name = "type")
	private String type;
	//可选参数。扩展码，用户定义扩展码，只能为数字
	@Column(name = "extno")
	private String extno;
	//1成功 0失败
	@Column(name = "send_status")
	private Integer sendStatus;
	//发送编号
	@Column(name = "send_id")
	private String sendId;
	//无效号码数
	@Column(name = "invalid_num")
	private Integer invalidNum;
	//成功提交数
	@Column(name = "success_num")
	private Integer successNum;
	//黑名单数
	@Column(name = "black_num")
	private Integer blackNum;
	//返回消息
	@Column(name = "return_msg")
	private String returnMsg;


    /**
     * 设置：操作人
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取：操作人
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置：必填参数。发送内容（1-500 个汉字）UTF-8编码
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取：必填参数。发送内容（1-500 个汉字）UTF-8编码
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置：必填参数。手机号码。多个以英文逗号隔开
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取：必填参数。手机号码。多个以英文逗号隔开
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置：必填参数。用户签名
     */
    public void setSign(String sign) {
        this.sign = sign;
    }

    /**
     * 获取：必填参数。用户签名
     */
    public String getSign() {
        return sign;
    }

    /**
     * 设置：必填参数。固定值 pt
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取：必填参数。固定值 pt
     */
    public String getType() {
        return type;
    }

    /**
     * 设置：可选参数。扩展码，用户定义扩展码，只能为数字
     */
    public void setExtno(String extno) {
        this.extno = extno;
    }

    /**
     * 获取：可选参数。扩展码，用户定义扩展码，只能为数字
     */
    public String getExtno() {
        return extno;
    }

    /**
     * 设置：1成功 0失败
     */
    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }

    /**
     * 获取：1成功 0失败
     */
    public Integer getSendStatus() {
        return sendStatus;
    }

    /**
     * 设置：发送编号
     */
    public void setSendId(String sendId) {
        this.sendId = sendId;
    }

    /**
     * 获取：发送编号
     */
    public String getSendId() {
        return sendId;
    }

    /**
     * 设置：无效号码数
     */
    public void setInvalidNum(Integer invalidNum) {
        this.invalidNum = invalidNum;
    }

    /**
     * 获取：无效号码数
     */
    public Integer getInvalidNum() {
        return invalidNum;
    }

    /**
     * 设置：成功提交数
     */
    public void setSuccessNum(Integer successNum) {
        this.successNum = successNum;
    }

    /**
     * 获取：成功提交数
     */
    public Integer getSuccessNum() {
        return successNum;
    }

    /**
     * 设置：黑名单数
     */
    public void setBlackNum(Integer blackNum) {
        this.blackNum = blackNum;
    }

    /**
     * 获取：黑名单数
     */
    public Integer getBlackNum() {
        return blackNum;
    }

    /**
     * 设置：返回消息
     */
    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    /**
     * 获取：返回消息
     */
    public String getReturnMsg() {
        return returnMsg;
    }
}
