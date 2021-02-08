package com.tata.retailapp;

public class Product {
    private int productNumber;
    private float price;
    private float quantity;
    private float retailPrice;

    public float getRetailPrice() {
        return retailPrice;
    }

    Product(int productNumber, float price){
        this.productNumber = productNumber;
        this.price  = price;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
        this.retailPrice = quantity*this.price;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productNumber=" + productNumber +
                ", price=" + price +
                ", quantity=" + quantity +
                ", retailPrice=" + retailPrice +
                '}';
    }
}
