 package com.uway.resale.entity;
 
 import java.io.Serializable;
 import org.apache.ibatis.annotation.myibatis.Table;
 
 @Table(pkId={"mainId"}, tabName="t_s_resalemain")
 public class ResaleMainModel
   implements Serializable
 {
   private int mainId;
   private String flowId;
   private String operator;
   private String operatTime;
   private String customerId;
   private String shopId;
   private String payType;
   private String phone;
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
     return this.mainId;
   }
 
   public void setMainId(int mainId) {
     this.mainId = mainId;
   }
 
   public String getFlowId() {
     return this.flowId;
   }
 
   public void setFlowId(String flowId) {
    this.flowId = flowId;
   }
 
   public String getOperator() {
     return this.operator;
   }
 
   public void setOperator(String operator) {
    this.operator = operator;
   }
 
   public String getOperatTime() {
     return this.operatTime;
   }
 
   public void setOperatTime(String operatTime) {
     this.operatTime = operatTime;
   }
 
   public String getCustomerId() {
    return this.customerId;
   }
 
   public void setCustomerId(String customerId) {
    this.customerId = customerId;
   }
 
   public String getShopId() {
     return this.shopId;
   }
 
   public void setShopId(String shopId) {
   this.shopId = shopId;
   }
 
   public String getPayType() {
    return this.payType;
   }
 
   public void setPayType(String payType) {
   this.payType = payType;
   }
 
   public String getPhone() {
    return this.phone;
   }
 
   public void setPhone(String phone) {
     this.phone = phone;
   }
 
   public String getTemp1() {
    return this.temp1;
   }
 
   public void setTemp1(String temp1) {
    this.temp1 = temp1;
   }
 
   public String getTemp2() {
   return this.temp2;
   }
 
   public void setTemp2(String temp2) {
    this.temp2 = temp2;
   }
 
   public String getTemp3() {
     return this.temp3;
   }
 
   public void setTemp3(String temp3) {
    this.temp3 = temp3;
   }
 
   public String getTemp4() {
    return this.temp4;
   }
 
   public void setTemp4(String temp4) {
    this.temp4 = temp4;
   }
 
   public String getTemp5() {
    return this.temp5;
   }
 
   public void setTemp5(String temp5) {
    this.temp5 = temp5;
   }
 
   public String getTemp6() {
    return this.temp6;
   }
 
   public void setTemp6(String temp6) {
     this.temp6 = temp6;
   }
 
   public String getTemp7() {
    return this.temp7;
   }
 
   public void setTemp7(String temp7) {
    this.temp7 = temp7;
   }
 
   public String getTemp8() {
    return this.temp8;
   }
 
   public void setTemp8(String temp8) {
    this.temp8 = temp8;
   }
 
   public String getTemp9() {
    return this.temp9;
   }
 
   public void setTemp9(String temp9) {
   this.temp9 = temp9;
   }
 
   public String getTemp10() {
    return this.temp10;
   }
 
   public void setTemp10(String temp10) {
     this.temp10 = temp10;
   }
 }
