package com.example.crudapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.example.crudapp.model.OrderItem;
import com.example.crudapp.repository.OrderItemRepository;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

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
    public boolean updateOrderItem(OrderItem orderItem) {
        return orderItemRepository.update(orderItem);
    }

    // Delete OrderItem by ID
    public boolean deleteOrderItemById(Long id) {
        return orderItemRepository.deleteById(id);
    }

    // Get all OrderItems for a specific Order
    public List<OrderItem> getOrderItemsByOrderId(Long orderId) {
        return orderItemRepository.getOrderItemsByOrderId(orderId);
    }
}
