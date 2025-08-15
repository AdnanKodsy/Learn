package com.example.crudapp.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {

    private Long userId;
    private List<OrderItemRequest> orderItems;

    public OrderRequest() {
    }

    public OrderRequest(Long userId, List<OrderItemRequest> orderItems) {
        this.userId = userId;
        this.orderItems = orderItems;
    }
}
