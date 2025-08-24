package com.example.crudapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crudapp.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    List<User> findByNameContainingIgnoreCase(String name);
    Optional<User> findByEmail(String email);
}