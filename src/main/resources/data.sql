-- Insert sample data
INSERT INTO users (name, email, age) VALUES
('John Doe', 'john.doe@example.com', 30),
('Jane Smith', 'jane.smith@example.com', 25),
('Bob Johnson', 'bob.johnson@example.com', 35),
('Alice Brown', 'alice.brown@example.com', 28);

-- Insert sample data for products
INSERT INTO products (name, description, price, stock_quantity, category, created_at) VALUES
('Laptop', 'High performance laptop', 1200.00, 10, 'Electronics', CURRENT_TIMESTAMP),
('Smartphone', 'Latest model smartphone', 800.00, 25, 'Electronics', CURRENT_TIMESTAMP),
('Desk Chair', 'Ergonomic office chair', 150.00, 40, 'Furniture', CURRENT_TIMESTAMP),
('Coffee Maker', 'Automatic coffee machine', 90.00, 15, 'Appliances', CURRENT_TIMESTAMP),
('Wireless Mouse', 'Bluetooth mouse', 25.00, 100, 'Accessories', CURRENT_TIMESTAMP);

-- Insert sample data for orders
INSERT INTO orders (user_id, product_id, quantity, total_amount, order_status, order_date) VALUES
(1, 1, 1, 1200.00, 'COMPLETED', CURRENT_DATE),
(2, 2, 2, 1600.00, 'PENDING', CURRENT_DATE),
(3, 3, 1, 150.00, 'SHIPPED', CURRENT_DATE),
(4, 4, 1, 90.00, 'CANCELLED', CURRENT_DATE),
(1, 5, 3, 75.00, 'COMPLETED', CURRENT_DATE);