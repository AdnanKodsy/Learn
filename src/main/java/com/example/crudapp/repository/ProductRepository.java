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

import com.example.crudapp.model.Product;

//@Repository
@Deprecated
public class ProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Product> productMapper = (ResultSet rs, int rowNum) -> {
        Product product = new Product();
        product.setId(rs.getLong("id"));
        product.setName(rs.getString("name"));
        product.setDescription(rs.getString("description"));
        product.setPrice(rs.getBigDecimal("price"));
        product.setStockQuantity(rs.getInt("stock_quantity"));
        product.setCategory(rs.getString("category"));
        product.setCreatedAt(rs.getDate("created_at"));
        return product;
    };

    // Create Product
    public Product save(Product product) {
        if (product.getId() == null) {
            // Insert new product
            String sql = "INSERT INTO products (name, description, price, stock_quantity, category, created_at) VALUES (?, ?, ?, ?, ?, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, product.getName());
                ps.setString(2, product.getDescription());
                ps.setBigDecimal(3, product.getPrice());
                ps.setInt(4, product.getStockQuantity());
                ps.setString(5, product.getCategory());
                ps.setDate(6, new Date(product.getCreatedAt().getTime()));
                return ps;
            }, keyHolder);

            product.setId(keyHolder.getKey().longValue());
            return product;
        } else {
            // Update existing product
            String sql = "UPDATE products SET name = ?, description = ?, price = ?, stock_quantity = ?, category = ?, created_at = ? WHERE id = ?";
            jdbcTemplate.update(sql, product.getName(), product.getDescription(), product.getPrice(),
                    product.getStockQuantity(), product.getCategory(), new Date(product.getCreatedAt().getTime()),
                    product.getId());
            return product;
        }
    }

    // Find all products
    public List<Product> findAll() {
        String sql = "SELECT * FROM products ORDER BY id";
        return jdbcTemplate.query(sql, productMapper);
    }

    // Find product by ID
    public Optional<Product> findById(Long id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        List<Product> products = jdbcTemplate.query(sql, productMapper, id);
        return products.isEmpty() ? Optional.empty() : Optional.of(products.get(0));
    }

    // Find product by Name
    public List<Product> findByName(String name) {
        String sql = "SELECT * FROM products WHERE LOWER(name) LIKE LOWER(?) ORDER BY id";
        return jdbcTemplate.query(sql, productMapper, "%" + name + "%");
    }

    // Delete Product by ID
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM products WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }

    // Count all Products
    public long countProducts() {
        String sql = "SELECT COUNT(*) FROM products";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count != null ? count : 0;
    }
}
