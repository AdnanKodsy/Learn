package com.example.crudapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crudapp.model.Order;
import com.example.crudapp.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order saveOrder(Order order){
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id){
      return orderRepository.findById(id);
    }

    public boolean deleteById(Long id){
        return orderRepository.deleteById(id);
    }

    public int orderCount(){
        return orderRepository.getOrderCount();
    }

}
