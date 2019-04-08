package com.uway.common.request;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;


/**
 * 请求对象request
 * 
 * @author db
 */
public class Request implements Serializable {
	
	private static final long serialVersionUID = 8029099338063271641L;

	/** 用户Id */
	private String userId;

	/** 客户端IP */
	private String ip;
	
	/**用户类型*/
	private Integer userType;

	/** 语言设置默认1 （1：中文、2：英文、3：韩文） */
	private Integer language;
	
	/**appKey*/
	private String appKey;
	
	/**appUserTable*/
	private String appUserTable;

	/** 参数params */
	private Map<String, String> headerMap = new HashMap<String, String>();

	/**  当前页  */
	//private Integer pageNum = 1;
	
	/**  每页显示多少条  */
	private Integer pageSize = 20;
	
	/**
	 * 翻页查询，起始行数
	 */
	private  Integer  startRow = 0; 	
	
	/** 设备型号 */
    private String	deviceModel;
    
    /** app版本号 */
	private String appVersion;
	
	private String mac;
	
	public final Integer getPageSize() {
		return pageSize;
	}

	public final void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public final String getUserId() {
		return userId;
	}

	public final void setUserId(String userId) {
		this.userId = userId;
	}

	public final String getIp() {
		return ip;
	}

	public final void setIp(String ip) {
		this.ip = ip;
	}

	public final Integer getLanguage() {
		return language;
	}

	public final void setLanguage(Integer language) {
		this.language = language;
	}

	public final Map<String, String> getHeaderMap() {
		return headerMap;
	}

	public final void setHeaderMap(Map<String, String> headerMap) {
		this.headerMap = headerMap;
	}

	 

	public final Integer getUserType() {
		return userType;
	}

	public final void setUserType(Integer userType) {
		this.userType = userType;
	}

	public final String getAppKey() {
		return appKey;
	}

	public final void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public final String getAppUserTable() {
		return appUserTable;
	}

	public final void setAppUserTable(String appUserTable) {
		this.appUserTable = appUserTable;
	}

	public final Integer getStartRow() {
		return startRow;
	}

	public final void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}
	
	public  final RowBounds getRowBounds(){
		return new RowBounds(startRow,pageSize);
	}

	public final String getDeviceModel() {
		return deviceModel;
	}

	public final void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public final String getAppVersion() {
		return appVersion;
	}

	public final void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public final String getMac() {
		return mac;
	}

	public final void setMac(String mac) {
		this.mac = mac;
	}	
	
	
}
