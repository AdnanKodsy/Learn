package com.example.crudapp.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {
    private Long id;
    private Long userId;
    private Long productId;
    private int quantity;
    private BigDecimal totalAmount;
    private String orderStatus;
    private Date orderDate;

    public Order() {}

    public Order(Long userId, Long productId, int quantity, BigDecimal totalAmount, String orderStatus,Date orderDate) {
        
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
    }
        public Order(Long id, Long userId, Long productId, int quantity, BigDecimal totalAmount, String orderStatus,Date orderDate) {
        
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", totalAmount=" + totalAmount +
                ", orderStatus=" + orderStatus + '\'' +
                ", orderDate=" + orderDate +
                "}";
        }
    }
