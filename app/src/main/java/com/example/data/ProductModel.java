package com.example.data;

import android.text.Editable;

import java.io.Serializable;

public class ProductModel implements Serializable {
    public ProductModel(Integer id, String name, String linkImg, String description, Integer price, boolean deleteFlag, Integer quantity) {
        this.id = id;
        this.name = name;
        this.linkImg = linkImg;
        this.description = description;
        this.price = price;
        this.deleteFlag = deleteFlag;
        this.quantity = quantity;
    }

    private Integer id =1;
    private String name;
    private String linkImg;
    private String description;
    private Integer price;
    private boolean deleteFlag;
    private Integer quantity;

    public ProductModel( String name, String linkImg, String description, Integer price, boolean deleteFlag, int quantity) {
        this.name = name;
        this.linkImg = linkImg;
        this.description = description;
        this.price = price;
        this.deleteFlag = deleteFlag;
        this.quantity = quantity;
    }

    public ProductModel( Editable name, String linkImg, Editable description, Editable price, Integer isDelete, Editable quantity ) {
        this.name = name.toString();
        this.linkImg = linkImg;
        this.description = description.toString();
        this.price = Integer.valueOf(price.toString());
        this.deleteFlag = Boolean.valueOf(isDelete.toString());
        this.quantity = Integer.valueOf(quantity.toString());
    }

    public ProductModel() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkImg() {
        return linkImg;
    }

    public void setLinkImg(String linkImg) {
        this.linkImg = linkImg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", linkImg='" + linkImg + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", deleteFlag=" + deleteFlag +
                ", quantity=" + quantity +
                '}';
    }
}
