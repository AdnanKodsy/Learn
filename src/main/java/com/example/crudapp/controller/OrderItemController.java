package com.example.crudapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crudapp.model.OrderItem;
import com.example.crudapp.service.OrderItemService;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    // Create OrderItem
    @PostMapping
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem orderItem) {
        OrderItem savedItem = orderItemService.saveOrderItem(orderItem);
        return ResponseEntity.ok(savedItem);
    }

    // Get all OrderItems
    @GetMapping
    public ResponseEntity<List<OrderItem>> getAllOrderItems() {
        List<OrderItem> items = orderItemService.getAllOrderItems();
        return ResponseEntity.ok(items);
    }

    // Get OrderItem by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable Long id) {
        Optional<OrderItem> item = orderItemService.getOrderItemById(id);
        return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update OrderItem
    /*
     * @PutMapping("/{id}")
     * public ResponseEntity<?> updateOrderItem(@PathVariable Long id, @RequestBody
     * OrderItem orderItem) {
     * orderItem.setId(id);
     * boolean updated = orderItemService.updateOrderItem(orderItem);
     * if (updated) {
     * return ResponseEntity.ok().build();
     * } else {
     * return ResponseEntity.notFound().build();
     * }
     * }
     */

    // Delete OrderItem by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderItem(@PathVariable Long id) {
        if (orderItemService.getOrderItemById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        orderItemService.deleteOrderItemById(id);
        return ResponseEntity.noContent().build();
    }

    // Get all OrderItems for a specific Order
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderItem>> getOrderItemsByOrderId(@PathVariable Long orderId) {
        List<OrderItem> items = orderItemService.getAllOrderItems();// recheck here
        return ResponseEntity.ok(items);
    }
}
