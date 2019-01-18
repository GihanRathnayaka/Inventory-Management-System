package com.revivatea.entity;

public class ProductCategory extends SuperEntity {
    private String productCateID;
    private String name;
    private String description;

    public ProductCategory() {
    }

    public ProductCategory(String productCateID, String name, String description) {
        this.productCateID = productCateID;
        this.name = name;
        this.description = description;
    }


    public String getProductCateID() {
        return productCateID;
    }

    public void setProductCateID(String productCateID) {
        this.productCateID = productCateID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "productCateID='" + productCateID + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
