package com.example.crudapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crudapp.model.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {

    List<Order> findByUser_Id(Long userId);
}
