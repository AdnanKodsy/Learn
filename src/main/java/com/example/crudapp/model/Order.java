package com.example.crudapp.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {
    private Long id;
    private Long userId;
    private String orderStatus;
    private Date orderDate;

    public Order() {
    }

    public Order(Long userId, String orderStatus, Date orderDate) {

        this.userId = userId;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
    }

    public Order(Long id, Long userId, String orderStatus, Date orderDate) {

        this.id = id;
        this.userId = userId;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", orderStatus=" + orderStatus + '\'' +
                ", orderDate=" + orderDate +
                "}";
    }
}
