package com.uway.system.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.annotation.myibatis.Table;

@Table(pkId = "userId",  tabName = "T_S_USER")
public class SysUser implements Serializable{

	private static final long serialVersionUID = 8221299618406979511L;

	//主键
	private Long userId; 

	//用户ID
	private String userName; 

	//排序
	private Integer userOrder; 

	/** 1:系统用户，2：关联用户 */
	private Integer userType; 

	//用户状态 ，1启用2禁止
	private Integer userStatus; 

	//加密密码
	private String userPwd; 

	//创建时间
	private Date createTime; 

	//更新时间
	private Date updateTime; 

	//真实姓名
	private String realName; 

	//邮箱地址
	private String email; 

	//手机号
	private String mobile; 

	//性别     1：男 ， 2：女
	private Integer sex; 
	/** 关联的用户ID */
    private Integer	relateUserId;

	public Long  getUserId(){
		return this.userId;
	}

	public void setUserId(Long userId){
		this.userId=userId;
	}

	public String  getUserName(){
		return this.userName;
	}

	public void setUserName(String userName){
		this.userName = userName == null ? null : userName.trim();
	}

	public Integer  getUserOrder(){
		return this.userOrder;
	}

	public void setUserOrder(Integer userOrder){
		this.userOrder=userOrder;
	}
	/** 1:系统用户，2：关联用户 */
	public Integer  getUserType(){
		return this.userType;
	}
	/** 1:系统用户，2：关联用户 */
	public void setUserType(Integer userType){
		this.userType=userType;
	}

	public Integer  getUserStatus(){
		return this.userStatus;
	}

	public void setUserStatus(Integer userStatus){
		this.userStatus=userStatus;
	}

	public String  getUserPwd(){
		return this.userPwd;
	}

	public void setUserPwd(String userPwd){
		this.userPwd = userPwd == null ? null : userPwd.trim();
	}

	public Date  getCreateTime(){
		return this.createTime;
	}

	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	public Date  getUpdateTime(){
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}

	public String  getRealName(){
		return this.realName;
	}

	public void setRealName(String realName){
		this.realName = realName == null ? null : realName.trim();
	}

	public String  getEmail(){
		return this.email;
	}

	public void setEmail(String email){
		this.email = email == null ? null : email.trim();
	}

	public String  getMobile(){
		return this.mobile;
	}

	public void setMobile(String mobile){
		this.mobile = mobile == null ? null : mobile.trim();
	}

	public Integer  getSex(){
		return this.sex;
	}

	public void setSex(Integer sex){
		this.sex=sex;
	}
	
	/** 关联的用户ID */
	public Integer getRelateUserId() {
		return relateUserId;
	}
	/** 关联的用户ID */
	public void setRelateUserId(Integer relateUserId) {
		this.relateUserId = relateUserId;
	}
}
