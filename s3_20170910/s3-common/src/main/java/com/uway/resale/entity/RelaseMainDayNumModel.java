 package com.uway.resale.entity;
 
 import java.io.Serializable;
 import org.apache.ibatis.annotation.myibatis.Table;
 
 @Table(pkId={"id"}, tabName="t_s_resaleMaindayNum")
 public class RelaseMainDayNumModel
   implements Serializable
 {
   private int id;
   private String timeIndex;
   private int num;
 
   public int getId()
   {
     return this.id;
   }
 
   public void setId(int id) {
    this.id = id;
   }
 
   public String getTimeIndex() {
    return this.timeIndex;
   }
 
   public void setTimeIndex(String timeIndex) {
   this.timeIndex = timeIndex;
   }
 
   public int getNum() {
    return this.num;
   }
 
   public void setNum(int num) {
     this.num = num;
   }
 }
