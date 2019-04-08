/*     */ package com.uway.wholeSale.entity;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.ibatis.annotation.myibatis.Table;
/*     */ 
/*     */ @Table(pkId={"wholeSaleId"}, tabName="t_s_wholeSale")
/*     */ public class WholeSaleModel
/*     */   implements Serializable
/*     */ {
/*     */   private int wholeSaleId;
/*     */   private String startTime;
/*     */   private String productType;
/*     */   private String product;
/*     */   private double price;
/*     */   private String unit;
/*     */   private double productNum;
/*     */   private int operName;
/*     */   private String editTime;
/*     */   private int isDelete;
/*     */   private int mainId;
/*     */   private String temp1;
/*     */   private String temp2;
/*     */   private String temp3;
/*     */   private String temp4;
/*     */   private String temp5;
/*     */   private String temp6;
/*     */   private String temp7;
/*     */   private String temp8;
/*     */   private String temp9;
/*     */   private String temp10;
/*     */ 
/*     */   public int getWholeSaleId()
/*     */   {
/*  38 */     return this.wholeSaleId;
/*     */   }
/*     */ 
/*     */   public void setWholeSaleId(int wholeSaleId) {
/*  42 */     this.wholeSaleId = wholeSaleId;
/*     */   }
/*     */ 
/*     */   public String getStartTime() {
/*  46 */     return this.startTime;
/*     */   }
/*     */ 
/*     */   public void setStartTime(String startTime) {
/*  50 */     this.startTime = startTime;
/*     */   }
/*     */ 
/*     */   public String getProductType() {
/*  54 */     return this.productType;
/*     */   }
/*     */ 
/*     */   public void setProductType(String productType) {
/*  58 */     this.productType = productType;
/*     */   }
/*     */ 
/*     */   public String getProduct() {
/*  62 */     return this.product;
/*     */   }
/*     */ 
/*     */   public void setProduct(String product) {
/*  66 */     this.product = product;
/*     */   }
/*     */ 
/*     */   public double getPrice() {
/*  70 */     return this.price;
/*     */   }
/*     */ 
/*     */   public void setPrice(double price) {
/*  74 */     this.price = price;
/*     */   }
/*     */ 
/*     */   public String getUnit() {
/*  78 */     return this.unit;
/*     */   }
/*     */ 
/*     */   public void setUnit(String unit) {
/*  82 */     this.unit = unit;
/*     */   }
/*     */ 
/*     */   public double getProductNum() {
/*  86 */     return this.productNum;
/*     */   }
/*     */ 
/*     */   public void setProductNum(double productNum) {
/*  90 */     this.productNum = productNum;
/*     */   }
/*     */ 
/*     */   public int getOperName() {
/*  94 */     return this.operName;
/*     */   }
/*     */ 
/*     */   public void setOperName(int operName) {
/*  98 */     this.operName = operName;
/*     */   }
/*     */ 
/*     */   public String getEditTime() {
/* 102 */     return this.editTime;
/*     */   }
/*     */ 
/*     */   public void setEditTime(String editTime) {
/* 106 */     this.editTime = editTime;
/*     */   }
/*     */ 
/*     */   public int getIsDelete() {
/* 110 */     return this.isDelete;
/*     */   }
/*     */ 
/*     */   public void setIsDelete(int isDelete) {
/* 114 */     this.isDelete = isDelete;
/*     */   }
/*     */ 
/*     */   public int getMainId() {
/* 118 */     return this.mainId;
/*     */   }
/*     */ 
/*     */   public void setMainId(int mainId) {
/* 122 */     this.mainId = mainId;
/*     */   }
/*     */ 
/*     */   public String getTemp1() {
/* 126 */     return this.temp1;
/*     */   }
/*     */ 
/*     */   public void setTemp1(String temp1) {
/* 130 */     this.temp1 = temp1;
/*     */   }
/*     */ 
/*     */   public String getTemp2() {
/* 134 */     return this.temp2;
/*     */   }
/*     */ 
/*     */   public void setTemp2(String temp2) {
/* 138 */     this.temp2 = temp2;
/*     */   }
/*     */ 
/*     */   public String getTemp3() {
/* 142 */     return this.temp3;
/*     */   }
/*     */ 
/*     */   public void setTemp3(String temp3) {
/* 146 */     this.temp3 = temp3;
/*     */   }
/*     */ 
/*     */   public String getTemp4() {
/* 150 */     return this.temp4;
/*     */   }
/*     */ 
/*     */   public void setTemp4(String temp4) {
/* 154 */     this.temp4 = temp4;
/*     */   }
/*     */ 
/*     */   public String getTemp5() {
/* 158 */     return this.temp5;
/*     */   }
/*     */ 
/*     */   public void setTemp5(String temp5) {
/* 162 */     this.temp5 = temp5;
/*     */   }
/*     */ 
/*     */   public String getTemp6() {
/* 166 */     return this.temp6;
/*     */   }
/*     */ 
/*     */   public void setTemp6(String temp6) {
/* 170 */     this.temp6 = temp6;
/*     */   }
/*     */ 
/*     */   public String getTemp7() {
/* 174 */     return this.temp7;
/*     */   }
/*     */ 
/*     */   public void setTemp7(String temp7) {
/* 178 */     this.temp7 = temp7;
/*     */   }
/*     */ 
/*     */   public String getTemp8() {
/* 182 */     return this.temp8;
/*     */   }
/*     */ 
/*     */   public void setTemp8(String temp8) {
/* 186 */     this.temp8 = temp8;
/*     */   }
/*     */ 
/*     */   public String getTemp9() {
/* 190 */     return this.temp9;
/*     */   }
/*     */ 
/*     */   public void setTemp9(String temp9) {
/* 194 */     this.temp9 = temp9;
/*     */   }
/*     */ 
/*     */   public String getTemp10() {
/* 198 */     return this.temp10;
/*     */   }
/*     */ 
/*     */   public void setTemp10(String temp10) {
/* 202 */     this.temp10 = temp10;
/*     */   }
/*     */ }

/* Location:           E:\apache-tomcat-8.5.16\apache-tomcat-8.5.16\webapps2\s3-system\WEB-INF\lib\s3-common-1.0.1.jar
 * Qualified Name:     com.uway.wholeSale.entity.WholeSaleModel
 * JD-Core Version:    0.6.0
 */