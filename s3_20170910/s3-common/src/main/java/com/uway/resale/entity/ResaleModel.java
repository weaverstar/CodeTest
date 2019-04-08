 package com.uway.resale.entity;
 
 import java.io.Serializable;
 import org.apache.ibatis.annotation.myibatis.Table;
 
 @Table(pkId={"resaleId"}, tabName="t_s_resale")
 public class ResaleModel
   implements Serializable
 {
   private int resaleId;
   private String startTime;
   private String productType;
   private String product;
   private double price;
   private String unit;
   private double productNum;
   private int operName;
   private String editTime;
   private int isDelete;
   private int mainId;
   private String temp1;
   private String temp2;
   private String temp3;
   private String temp4;
   private String temp5;
   private String temp6;
   private String temp7;
   private String temp8;
   private String temp9;
   private String temp10;
 
   public int getResaleId()
   {
/*  37 */     return this.resaleId;
   }
 
   public void setResaleId(int resaleId) {
/*  41 */     this.resaleId = resaleId;
   }
 
   public String getStartTime() {
/*  45 */     return this.startTime;
   }
 
   public void setStartTime(String startTime) {
/*  49 */     this.startTime = startTime;
   }
 
   public String getProductType() {
/*  53 */     return this.productType;
   }
 
   public void setProductType(String productType) {
/*  57 */     this.productType = productType;
   }
 
   public String getProduct() {
/*  61 */     return this.product;
   }
 
   public void setProduct(String product) {
/*  65 */     this.product = product;
   }
 
   public double getPrice() {
/*  69 */     return this.price;
   }
 
   public void setPrice(double price) {
/*  73 */     this.price = price;
   }
 
   public String getUnit() {
/*  77 */     return this.unit;
   }
 
   public void setUnit(String unit) {
/*  81 */     this.unit = unit;
   }
 
   public double getProductNum() {
/*  85 */     return this.productNum;
   }
 
   public void setProductNum(double productNum) {
/*  89 */     this.productNum = productNum;
   }
 
   public int getOperName() {
/*  93 */     return this.operName;
   }
 
   public void setOperName(int operName) {
/*  97 */     this.operName = operName;
   }
 
   public String getEditTime() {
/* 101 */     return this.editTime;
   }
 
   public void setEditTime(String editTime) {
/* 105 */     this.editTime = editTime;
   }
 
   public int getIsDelete() {
/* 109 */     return this.isDelete;
   }
 
   public void setIsDelete(int isDelete) {
/* 113 */     this.isDelete = isDelete;
   }
 
   public int getMainId() {
/* 117 */     return this.mainId;
   }
 
   public void setMainId(int mainId) {
/* 121 */     this.mainId = mainId;
   }
 
   public String getTemp1() {
/* 125 */     return this.temp1;
   }
 
   public void setTemp1(String temp1) {
/* 129 */     this.temp1 = temp1;
   }
 
   public String getTemp2() {
/* 133 */     return this.temp2;
   }
 
   public void setTemp2(String temp2) {
/* 137 */     this.temp2 = temp2;
   }
 
   public String getTemp3() {
/* 141 */     return this.temp3;
   }
 
   public void setTemp3(String temp3) {
/* 145 */     this.temp3 = temp3;
   }
 
   public String getTemp4() {
/* 149 */     return this.temp4;
   }
 
   public void setTemp4(String temp4) {
/* 153 */     this.temp4 = temp4;
   }
 
   public String getTemp5() {
/* 157 */     return this.temp5;
   }
 
   public void setTemp5(String temp5) {
/* 161 */     this.temp5 = temp5;
   }
 
   public String getTemp6() {
/* 165 */     return this.temp6;
   }
 
   public void setTemp6(String temp6) {
/* 169 */     this.temp6 = temp6;
   }
 
   public String getTemp7() {
/* 173 */     return this.temp7;
   }
 
   public void setTemp7(String temp7) {
/* 177 */     this.temp7 = temp7;
   }
 
   public String getTemp8() {
/* 181 */     return this.temp8;
   }
 
   public void setTemp8(String temp8) {
/* 185 */     this.temp8 = temp8;
   }
 
   public String getTemp9() {
/* 189 */     return this.temp9;
   }
 
   public void setTemp9(String temp9) {
/* 193 */     this.temp9 = temp9;
   }
 
   public String getTemp10() {
/* 197 */     return this.temp10;
   }
 
   public void setTemp10(String temp10) {
/* 201 */     this.temp10 = temp10;
   }
 }

/* Location:           E:\apache-tomcat-8.5.16\apache-tomcat-8.5.16\webapps2\s3-system\WEB-INF\lib\s3-common-1.0.1.jar
 * Qualified Name:     com.uway.resale.entity.ResaleModel
 * JD-Core Version:    0.6.0
 */