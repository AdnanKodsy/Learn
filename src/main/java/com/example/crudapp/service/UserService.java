package com.example.crudapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crudapp.model.User;
import com.example.crudapp.repository.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;

    // Create or update user
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get user by ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Search users by name
    public List<User> searchUsersByName(String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }

    // Update user
    public Optional<User> updateUser(Long id, User userDetails) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            user.setAge(userDetails.getAge());
            return Optional.of(userRepository.save(user));
        }
        return Optional.empty();
    }

    // Delete user
    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }

    // Check if user exists
    public boolean userExists(Long id) {
        return userRepository.existsById(id);
    }

    // Get total user count
    public long getUserCount() {
        return userRepository.count();
    }

}
