package com.uway.repertory.entity;

import com.uway.product.entity.Product;
import com.uway.product.entity.ProductType;
import org.apache.ibatis.annotation.myibatis.Table;

import java.io.Serializable;

/**
 *
 */
@Table(pkId={"id"} ,tabName = "t_s_entrepot",notColumn = {"product","productOfType"})
public class Entrepot implements Serializable {
    private int id;
    private int productId;
    private int productType;
    private String total;
    private String temp1;  /**进货价**/
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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
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

    public String getTemp1() {
        return temp1;
    }

    public void setTemp1(String temp1) {
        this.temp1 = temp1;
    }
}
