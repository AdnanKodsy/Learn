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

-- Insert sample data for orders (header info only)
INSERT INTO orders (user_id, order_status, order_date) VALUES
(1, 'SHIPPED', CURRENT_DATE),
(2, 'PENDING', CURRENT_DATE),
(3, 'PROCESSING', CURRENT_DATE),
(4, 'CANCELLED', CURRENT_DATE),
(1, 'DELIVERED', CURRENT_DATE - 1);

-- Insert sample data for order_items (line items within orders)
INSERT INTO order_items (order_id, product_id, quantity, unit_price, total_amount) VALUES
(1, 1, 1, 1200.00, 1200.00),         -- John's completed order: 1 laptop
(1, 5, 2, 25.00, 50.00),             -- John's completed order: also has 2 mice
(2, 2, 2, 800.00, 1600.00),          -- Jane's pending order: 2 smartphones
(3, 3, 1, 150.00, 150.00),           -- Bob's shipped order: 1 desk chair
(4, 4, 1, 90.00, 90.00),             -- Alice's cancelled order: 1 coffee maker
(5, 5, 3, 25.00, 75.00),             -- John's older completed order: 3 mice
(5, 3, 1, 150.00, 150.00);           -- John's older completed order: also has 1 chair