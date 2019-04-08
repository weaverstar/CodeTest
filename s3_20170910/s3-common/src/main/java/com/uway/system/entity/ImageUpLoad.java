package com.uway.system.entity;

import java.io.Serializable;
public class ImageUpLoad implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8784179649878854463L;

	private String imgDesc;//图片描述
	
	private String descColumn;//图片描述所在的字段
	
	private String tableName;//图片存储的表名
	//private String seqName;//序列名称
	
	//private byte imgData[];//文件二进制数据
	
	private boolean unique = false;//是否多张图片,多张图片则是主从表，1张图片就一张表值为true
	
	//private boolean lazy = false;//是否延迟加载,默认false
	private boolean success = true;
	private String errInfo;
	
	private String pk;//主键
	private String pkField;//主键属性
	
	private String fk;//外键列明
	private String fkField;//外键属性
	
	private String imgUrl;//图片地址
	private String urlColumn;//图片地址存储的字段
	
	private Long imgId; //图片id
	
	private Long relationId;//关联ID的值
	
	private String fileName;//上传文件的名称
	//private String imageFolder = "city";//图片存放的文件夹名称
	

	 

	public String getImgDesc() {
		return imgDesc;
	}

	public void setImgDesc(String imgDesc) {
		this.imgDesc = imgDesc;
	}

	public String getDescColumn() {
		return descColumn;
	}

	public void setDescColumn(String descColumn) {
		this.descColumn = descColumn;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	


	public String getFkField() {
		return fkField;
	}

	public void setFkField(String fkField) {
		this.fkField = fkField;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String getFk() {
		return fk;
	}

	public void setFk(String fk) {
		this.fk = fk;
	}

	public String getPkField() {
		return pkField;
	}

	public void setPkField(String pkField) {
		this.pkField = pkField;
	}

	public String getUrlColumn() {
		return urlColumn;
	}

	public void setUrlColumn(String urlColumn) {
		this.urlColumn = urlColumn;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Long getImgId() {
		return imgId;
	}

	public void setImgId(Long imgId) {
		this.imgId = imgId;
	}

	public Long getRelationId() {
		return relationId;
	}

	public void setRelationId(Long relationId) {
		this.relationId = relationId;
	}

	/*public byte[] getImgData() {
		return imgData;
	}

	public void setImgData(byte[] imgData) {
		this.imgData = imgData;
	}*/

	public boolean isUnique() {
		return unique;
	}

	public void setUnique(boolean unique) {
		this.unique = unique;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrInfo() {
		return errInfo;
	}

	public void setErrInfo(String errInfo) {
		this.errInfo = errInfo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	/*public String getUploadPath(){
		StringBuffer sb = new StringBuffer();
		sb.append("?fileName="+getFileName()+"&imageFolder="+getImageFolder());
		return sb.toString();
	}
	
	public String getDelImagePath(){
		StringBuffer sb = new StringBuffer();
		sb.append("?imgUrl="+getImgUrl()+"&imageFolder="+getImageFolder());
		return sb.toString();
	}*/
	
}
