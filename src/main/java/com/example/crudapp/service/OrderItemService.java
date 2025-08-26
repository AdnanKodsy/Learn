package com.example.crudapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crudapp.model.OrderItem;
import com.example.crudapp.repository.OrderItemRepo;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepo orderItemRepository;

    // Create OrderItem
    public OrderItem saveOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    // Get all OrderItems
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    // Get OrderItem by ID
    public Optional<OrderItem> getOrderItemById(Long id) {
        return orderItemRepository.findById(id);
    }

    // Update OrderItem
    public OrderItem updateOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    // Delete OrderItem by ID
    public void deleteOrderItemById(Long id) {
        orderItemRepository.deleteById(id);
    }

}
