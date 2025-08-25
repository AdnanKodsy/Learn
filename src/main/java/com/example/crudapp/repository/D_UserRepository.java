package com.example.crudapp.repository;

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

import com.example.crudapp.model.User;

//@Repository
@Deprecated
public class D_UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Row mapper for User
    private final RowMapper<User> userRowMapper = (ResultSet rs, int rowNum) -> {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setAge(rs.getInt("age"));
        return user;
    };

    // Create user
    public User save(User user) {
        if (user.getId() == null) {
            // Insert new user
            String sql = "INSERT INTO users (name, email, age) VALUES (?, ?, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getName());
                ps.setString(2, user.getEmail());
                ps.setInt(3, user.getAge());
                return ps;
            }, keyHolder);

            user.setId(keyHolder.getKey().longValue());
            return user;
        } else {
            // Update existing user
            String sql = "UPDATE users SET name = ?, email = ?, age = ? WHERE id = ?";
            jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getAge(), user.getId());
            return user;
        }
    }

    // Find all users
    public List<User> findAll() {
        String sql = "SELECT * FROM users ORDER BY id";
        return jdbcTemplate.query(sql, userRowMapper);
    }

    // Find user by ID
    public Optional<User> findById(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        List<User> users = jdbcTemplate.query(sql, userRowMapper, id);
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }

    // Find users by name (partial match)
    public List<User> findByNameContaining(String name) {
        String sql = "SELECT * FROM users WHERE LOWER(name) LIKE LOWER(?) ORDER BY id";
        return jdbcTemplate.query(sql, userRowMapper, "%" + name + "%");
    }

    // Delete user by ID
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }

    // Check if user exists by ID
    public boolean existsById(Long id) {
        String sql = "SELECT COUNT(*) FROM users WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

    // Count total users
    public long count() {
        String sql = "SELECT COUNT(*) FROM users";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count != null ? count : 0;
    }
}
