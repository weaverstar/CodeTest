 package com.uway.wholeSale.entity;
 
 import java.io.Serializable;
 import org.apache.ibatis.annotation.myibatis.Table;
 
 @Table(pkId={"mainId"}, tabName="t_s_wholesalemain")
 public class WholeSaleMainModel
   implements Serializable
 {
   private int mainId;
   private String flowId;
   private String operator;
   private String operatorTime;
   private String customerId;
   private String shopId;
   private String payType;
   private String phone;
   private String city;
   private String town;
   private String note;
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
 
   public int getMainId()
   {
/*  38 */     return this.mainId;
   }
 
   public void setMainId(int mainId) {
/*  42 */     this.mainId = mainId;
   }
 
   public String getFlowId() {
/*  46 */     return this.flowId;
   }
 
   public void setFlowId(String flowId) {
/*  50 */     this.flowId = flowId;
   }
 
   public String getOperator() {
/*  54 */     return this.operator;
   }
 
   public void setOperator(String operator) {
/*  58 */     this.operator = operator;
   }
 
   public String getOperatorTime() {
/*  62 */     return this.operatorTime;
   }
 
   public void setOperatorTime(String operatorTime) {
/*  66 */     this.operatorTime = operatorTime;
   }
 
   public String getCustomerId() {
/*  70 */     return this.customerId;
   }
 
   public void setCustomerId(String customerId) {
/*  74 */     this.customerId = customerId;
   }
 
   public String getShopId() {
/*  78 */     return this.shopId;
   }
 
   public void setShopId(String shopId) {
/*  82 */     this.shopId = shopId;
   }
 
   public String getPayType() {
/*  86 */     return this.payType;
   }
 
   public void setPayType(String payType) {
/*  90 */     this.payType = payType;
   }
 
   public String getPhone() {
/*  94 */     return this.phone;
   }
 
   public void setPhone(String phone) {
/*  98 */     this.phone = phone;
   }
 
   public String getCity() {
/* 102 */     return this.city;
   }
 
   public void setCity(String city) {
/* 106 */     this.city = city;
   }
 
   public String getTown() {
/* 110 */     return this.town;
   }
 
   public void setTown(String town) {
/* 114 */     this.town = town;
   }
 
   public String getNote() {
/* 118 */     return this.note;
   }
 
   public void setNote(String note) {
/* 122 */     this.note = note;
   }
 
   public String getTemp1() {
/* 126 */     return this.temp1;
   }
 
   public void setTemp1(String temp1) {
/* 130 */     this.temp1 = temp1;
   }
 
   public String getTemp2() {
/* 134 */     return this.temp2;
   }
 
   public void setTemp2(String temp2) {
/* 138 */     this.temp2 = temp2;
   }
 
   public String getTemp3() {
/* 142 */     return this.temp3;
   }
 
   public void setTemp3(String temp3) {
/* 146 */     this.temp3 = temp3;
   }
 
   public String getTemp4() {
/* 150 */     return this.temp4;
   }
 
   public void setTemp4(String temp4) {
/* 154 */     this.temp4 = temp4;
   }
 
   public String getTemp5() {
/* 158 */     return this.temp5;
   }
 
   public void setTemp5(String temp5) {
/* 162 */     this.temp5 = temp5;
   }
 
   public String getTemp6() {
/* 166 */     return this.temp6;
   }
 
   public void setTemp6(String temp6) {
/* 170 */     this.temp6 = temp6;
   }
 
   public String getTemp7() {
/* 174 */     return this.temp7;
   }
 
   public void setTemp7(String temp7) {
/* 178 */     this.temp7 = temp7;
   }
 
   public String getTemp8() {
/* 182 */     return this.temp8;
   }
 
   public void setTemp8(String temp8) {
/* 186 */     this.temp8 = temp8;
   }
 
   public String getTemp9() {
/* 190 */     return this.temp9;
   }
 
   public void setTemp9(String temp9) {
/* 194 */     this.temp9 = temp9;
   }
 
   public String getTemp10() {
/* 198 */     return this.temp10;
   }
 
   public void setTemp10(String temp10) {
/* 202 */     this.temp10 = temp10;
   }
 }
