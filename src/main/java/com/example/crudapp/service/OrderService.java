package com.example.crudapp.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.crudapp.dto.OrderDTO;
import com.example.crudapp.dto.OrderItemDTO;
import com.example.crudapp.dto.OrderItemRequest;
import com.example.crudapp.dto.OrderRequest;
import com.example.crudapp.dto.UserDTO;
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

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    private OrderDTO mapToDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setOrderStatus(order.getOrderStatus().name());
        dto.setOrderDate(order.getOrderDate());

        // map user
        UserDTO userDTO = new UserDTO();
        userDTO.setId(order.getUser().getId());
        userDTO.setName(order.getUser().getName());
        userDTO.setEmail(order.getUser().getEmail());
        dto.setUser(userDTO);

        // map order items
        List<OrderItemDTO> itemDTOs = order.getOrderItems().stream()
                .map(item -> {
                    OrderItemDTO itemDTO = new OrderItemDTO();
                    itemDTO.setId(item.getId());
                    itemDTO.setProductId(item.getProduct().getId()); // adjust if different
                    itemDTO.setQuantity(item.getQuantity());
                    return itemDTO;
                })
                .toList();

        dto.setOrderItems(itemDTOs);

        return dto;
    }

    public Optional<OrderDTO> getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(this::mapToDTO);
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
