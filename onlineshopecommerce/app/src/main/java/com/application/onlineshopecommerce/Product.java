package com.application.onlineshopecommerce;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String name;
    private String brand;
    private int price;
    private String description;

    public Product(int id, String name, String brand, int price, String description) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
