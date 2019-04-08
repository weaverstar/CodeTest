package com.uway.product.entity;

import org.apache.ibatis.annotation.myibatis.Table;

import java.io.Serializable;

/**
 *
 */
@Table(pkId={"id"} ,tabName = "t_s_product",notColumn = {"productType"})
public class Product implements Serializable {
    private int id;
    private String productName;
    private String price;
    private int typeId;
    private int statusId;
    private String note;
    private String createTime;
    private String factory;
    private String tPower;
    private String higher;

    private ProductType productType;

    /**预留字段**/
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTemp1() {
        return temp1;
    }

    public void setTemp1(String temp1) {
        this.temp1 = temp1;
    }

    public String getTemp2() {
        return temp2;
    }

    public void setTemp2(String temp2) {
        this.temp2 = temp2;
    }

    public String getTemp3() {
        return temp3;
    }

    public void setTemp3(String temp3) {
        this.temp3 = temp3;
    }

    public String getTemp4() {
        return temp4;
    }

    public void setTemp4(String temp4) {
        this.temp4 = temp4;
    }

    public String getTemp5() {
        return temp5;
    }

    public void setTemp5(String temp5) {
        this.temp5 = temp5;
    }

    public String getTemp6() {
        return temp6;
    }

    public void setTemp6(String temp6) {
        this.temp6 = temp6;
    }

    public String getTemp7() {
        return temp7;
    }

    public void setTemp7(String temp7) {
        this.temp7 = temp7;
    }

    public String getTemp8() {
        return temp8;
    }

    public void setTemp8(String temp8) {
        this.temp8 = temp8;
    }

    public String getTemp9() {
        return temp9;
    }

    public void setTemp9(String temp9) {
        this.temp9 = temp9;
    }

    public String getTemp10() {
        return temp10;
    }

    public void setTemp10(String temp10) {
        this.temp10 = temp10;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String gettPower() {
        return tPower;
    }

    public void settPower(String tPower) {
        this.tPower = tPower;
    }

    public String getHigher() {
        return higher;
    }

    public void setHigher(String higher) {
        this.higher = higher;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}
