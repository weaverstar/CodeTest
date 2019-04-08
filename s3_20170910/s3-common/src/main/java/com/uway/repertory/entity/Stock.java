package com.uway.repertory.entity;

import com.uway.product.entity.Product;
import com.uway.product.entity.ProductType;
import org.apache.ibatis.annotation.myibatis.Table;

import java.io.Serializable;

/**
 *
 */
@Table(pkId={"id"} ,tabName = "t_s_stock",notColumn = {"product","productOfType"})
public class Stock implements Serializable {
    private int id;
    private int productId;
    private int productType;
    private String createTime;
    private String note;
    private String price;
    private int total;
    private String rental;
    private String operator;
    private int pay_status;

    private Product product;
    private ProductType productOfType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductType() {
        return productType;
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getRental() {
        return rental;
    }

    public void setRental(String rental) {
        this.rental = rental;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getPay_status() {
        return pay_status;
    }

    public void setPay_status(int pay_status) {
        this.pay_status = pay_status;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductType getProductOfType() {
        return productOfType;
    }

    public void setProductOfType(ProductType productOfType) {
        this.productOfType = productOfType;
    }
}
