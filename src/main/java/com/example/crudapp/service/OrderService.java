package com.example.crudapp.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.crudapp.dto.OrderItemRequest;
import com.example.crudapp.dto.OrderRequest;
import com.example.crudapp.model.Order;
import com.example.crudapp.model.OrderItem;
import com.example.crudapp.model.OrderStatus;
import com.example.crudapp.model.Product;
import com.example.crudapp.repository.OrderRepo;
import com.example.crudapp.repository.ProductRepo;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepository;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private ProductRepo productRepository;

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    @Transactional
    public Order createCompleteOrder(OrderRequest request) {
        Order newOrder = new Order();
        newOrder.setId(request.getUserId());
        newOrder.setOrderStatus(OrderStatus.PENDING);
        newOrder.setOrderDate(LocalDateTime.now());

        Order savedOrder = orderRepository.save(newOrder);

        for (OrderItemRequest itemReq : request.getOrderItems()) {
            Optional<Product> productOpt = productRepository.findById(itemReq.getProductId());

            if (productOpt.isEmpty()) {
                throw new RuntimeException("product not found with ID " + itemReq.getProductId());
            }

            Product product = productOpt.get();

            if (product.getStockQuantity() < itemReq.getQuantity()) {
                throw new RuntimeException("Insufficient stock for: " + product.getName());
            }
            OrderItem orderItem = new OrderItem();
            BigDecimal itemsTotal = product.getPrice().multiply(BigDecimal.valueOf(itemReq.getQuantity()));

            orderItem.setOrder(savedOrder);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemReq.getQuantity());
            orderItem.setUnitPrice(product.getPrice());
            orderItem.setTotalAmount(itemsTotal);

            orderItemService.saveOrderItem(orderItem);

            product.setStockQuantity(product.getStockQuantity() - itemReq.getQuantity());
            productRepository.save(product);
        }
        return savedOrder;

    }

}
