package com.application.onlineshopecommerce;

import java.io.Serializable;

public class product implements Serializable {
    private final int id;
    private final String title;
    private final String author;
    private final double price;
    private final String description;

    public product(int id, String title, String author, double price, String description) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.description = description;
    }

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }
}