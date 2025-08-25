package com.example.crudapp.repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.crudapp.model.Order;

//@Repository
@Deprecated
public class D_OrderRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Order> orderMapper = (ResultSet rs, int rowNum) -> {
        Order order = new Order();
        order.setId(rs.getLong("id"));
        order.setOrderStatus(rs.getObject(rowNum, Enum));
        order.setOrderDate(rs.getDate("order_date"));
        return order;
    };

    // Create Order
    public Order save(Order order) {
        if (order.getId() == null) {
            String sql = "INSERT INTO orders (user_id, order_status, order_date) VALUES (?, ?, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, order.getUserId());
                ps.setString(2, order.getOrderStatus());
                ps.setDate(3, new Date(order.getOrderDate().getTime()));
                return ps;
            }, keyHolder);
            order.setId(keyHolder.getKey().longValue());
            return order;
        } else {
            String sql = "UPDATE orders SET user_id = ?, order_status = ?, order_date = ? WHERE id = ?";
            jdbcTemplate.update(sql, order.getUserId(), order.getOrderStatus(),
                    new Date(order.getOrderDate().getTime()),
                    order.getId());
            return order;
        }
    }

    // Find all orders
    public List<Order> findAll() {
        String sql = "SELECT * FROM orders ORDER BY id";
        return jdbcTemplate.query(sql, orderMapper);
    }

    // Find order by ID
    public Optional<Order> findById(Long id) {
        String sql = "SELECT * FROM orders WHERE id = ?";
        List<Order> orders = jdbcTemplate.query(sql, orderMapper, id);
        return orders.isEmpty() ? Optional.empty() : Optional.of(orders.get(0));
    }

    // Delete order by ID
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM orders WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }

    // Count all orders
    public int getOrderCount() {
        String sql = "SELECT COUNT(*) FROM orders";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count != null ? count : 0;
    }

    // Orders By specific user
    public List<Order> getOrdersByUser(Long id) {
        String sql = "SELECT * FROM orders WHERE user_id = ?";
        return jdbcTemplate.query(sql, orderMapper, id);
    }

    // update Order Status
    public Order updateStatus(Long orderId, String status) {
        String sql = "UPDATE orders SET order_status = ? WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, status, orderId);
        if (rowsAffected > 0) {
            return findById(orderId).orElse(null);
        } else {
            return null;
        }
    }
}