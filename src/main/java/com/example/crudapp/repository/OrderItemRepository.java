package com.example.crudapp.repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.example.crudapp.model.OrderItem;

//@Repository
@Deprecated
public class OrderItemRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<OrderItem> orderItemRowMapper = (ResultSet rs, int rowNum) -> {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(rs.getLong("id"));
        orderItem.setQuantity(rs.getInt("quantity"));
        orderItem.setUnitPrice(rs.getBigDecimal("unit_price"));
        orderItem.setTotalAmount(rs.getBigDecimal("total_amount"));
        return orderItem;
    };

    // Create OrderItem
    public OrderItem save(OrderItem orderItem) {
        String sql = "INSERT INTO order_Items (order_id, product_id, quantity, unit_price, total_amount) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                orderItem.getQuantity(),
                orderItem.getUnitPrice(),
                orderItem.getTotalAmount());
        // Optionally, fetch and return the saved item (if you need the generated ID)
        return orderItem;
    }

    // Read all OrderItems
    public List<OrderItem> findAll() {
        String sql = "SELECT * FROM order_items";
        return jdbcTemplate.query(sql, orderItemRowMapper);
    }

    // Read OrderItem by ID
    public Optional<OrderItem> findById(Long id) {
        String sql = "SELECT * FROM order_items WHERE id = ?";
        List<OrderItem> items = jdbcTemplate.query(sql, orderItemRowMapper, id);
        return items.isEmpty() ? Optional.empty() : Optional.of(items.get(0));
    }

    // Update OrderItem
    public boolean update(OrderItem orderItem) {
        String sql = "UPDATE order_items SET order_id = ?, product_id = ?, quantity = ?, unit_price = ?, total_amount = ? WHERE id = ?";
        int rows = jdbcTemplate.update(sql,
                orderItem.getQuantity(),
                orderItem.getUnitPrice(),
                orderItem.getTotalAmount(),
                orderItem.getId());
        return rows > 0;
    }

    // Delete OrderItem
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM order_items WHERE id = ?";
        int rows = jdbcTemplate.update(sql, id);
        return rows > 0;
    }

    // Get all OrderItems by Order ID
    public List<OrderItem> getOrderItemsByOrderId(Long orderId) {
        String sql = "SELECT * FROM order_items WHERE order_id = ?";
        return jdbcTemplate.query(sql, orderItemRowMapper, orderId);
    }
}
