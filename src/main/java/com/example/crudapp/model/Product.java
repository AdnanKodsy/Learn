package com.example.crudapp.model;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int stockQuantity;
    private String category;
    private Date createdAt;

    public Product() {
    }

    public Product(String name, String description, BigDecimal price, int stockQuantity, String category,
            Date createdAt) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
        this.createdAt = createdAt;
    }

    public Product(Long id, String name, String description, BigDecimal price, int stockQuantity, String category,
            Date createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
        this.createdAt = createdAt;

    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                ", category='" + category + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}