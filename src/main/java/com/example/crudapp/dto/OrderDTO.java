package com.example.crudapp.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {
    private Long id;
    private String orderStatus;
    private LocalDateTime orderDate;
    private UserDTO user;
    private List<OrderItemDTO> orderItems;
}