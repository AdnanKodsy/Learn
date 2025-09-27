-- =========================================
-- Original Users
-- =========================================
INSERT INTO users (name, email, age) VALUES
('John Doe', 'john.doe@example.com', 30),
('Jane Smith', 'jane.smith@example.com', 25),
('Bob Johnson', 'bob.johnson@example.com', 35),
('Alice Brown', 'alice.brown@example.com', 28);

-- Extended Users
INSERT INTO users (name, email, age) VALUES
('Charlie Davis', 'charlie.davis@example.com', 40),
('Emma Wilson', 'emma.wilson@example.com', 22),
('David Miller', 'david.miller@example.com', 31),
('Sophia Taylor', 'sophia.taylor@example.com', 27),
('Liam Martinez', 'liam.martinez@example.com', 45),
('Olivia Garcia', 'olivia.garcia@example.com', 33),
('Noah Anderson', 'noah.anderson@example.com', 29),
('Ava Thomas', 'ava.thomas@example.com', 38);

-- =========================================
-- Original Products
-- =========================================
INSERT INTO products (name, description, price, stock_quantity, category, created_at) VALUES
('Laptop', 'High performance laptop', 1200.00, 10, 'Electronics', CURRENT_TIMESTAMP),
('Smartphone', 'Latest model smartphone', 800.00, 25, 'Electronics', CURRENT_TIMESTAMP),
('Desk Chair', 'Ergonomic office chair', 150.00, 40, 'Furniture', CURRENT_TIMESTAMP),
('Coffee Maker', 'Automatic coffee machine', 90.00, 15, 'Appliances', CURRENT_TIMESTAMP),
('Wireless Mouse', 'Bluetooth mouse', 25.00, 100, 'Accessories', CURRENT_TIMESTAMP);

-- Extended Products
INSERT INTO products (name, description, price, stock_quantity, category, created_at) VALUES
('Tablet', '10-inch Android tablet', 400.00, 50, 'Electronics', CURRENT_TIMESTAMP),
('Headphones', 'Noise-cancelling headphones', 200.00, 30, 'Accessories', CURRENT_TIMESTAMP),
('Office Desk', 'Wooden office desk', 300.00, 20, 'Furniture', CURRENT_TIMESTAMP),
('Blender', 'High speed blender', 120.00, 18, 'Appliances', CURRENT_TIMESTAMP),
('Keyboard', 'Mechanical keyboard', 70.00, 60, 'Accessories', CURRENT_TIMESTAMP),
('Monitor', '27-inch 4K monitor', 350.00, 25, 'Electronics', CURRENT_TIMESTAMP),
('Bookshelf', '5-tier wooden bookshelf', 180.00, 15, 'Furniture', CURRENT_TIMESTAMP),
('Vacuum Cleaner', 'Bagless vacuum cleaner', 250.00, 12, 'Appliances', CURRENT_TIMESTAMP);

-- =========================================
-- Original Orders
-- =========================================
INSERT INTO orders (user_id, order_status, order_date) VALUES
(1, 'SHIPPED', CURRENT_DATE),
(2, 'PENDING', CURRENT_DATE),
(3, 'PROCESSING', CURRENT_DATE),
(4, 'CANCELLED', CURRENT_DATE),
(1, 'DELIVERED', CURRENT_DATE - 1);

-- Extended Orders
INSERT INTO orders (user_id, order_status, order_date) VALUES
(5, 'PENDING', CURRENT_DATE),
(6, 'PROCESSING', CURRENT_DATE - 2),
(7, 'SHIPPED', CURRENT_DATE - 3),
(8, 'DELIVERED', CURRENT_DATE - 5),
(5, 'CANCELLED', CURRENT_DATE - 1),
(6, 'DELIVERED', CURRENT_DATE - 7),
(7, 'PENDING', CURRENT_DATE),
(8, 'PROCESSING', CURRENT_DATE - 10),
(2, 'SHIPPED', CURRENT_DATE - 8),
(3, 'DELIVERED', CURRENT_DATE - 12);

-- =========================================
-- Original Order Items
-- =========================================
INSERT INTO order_items (order_id, product_id, quantity, unit_price, total_amount) VALUES
(1, 1, 1, 1200.00, 1200.00),        
(1, 5, 2, 25.00, 50.00),            
(2, 2, 2, 800.00, 1600.00),         
(3, 3, 1, 150.00, 150.00),          
(4, 4, 1, 90.00, 90.00),            
(5, 5, 3, 25.00, 75.00),            
(5, 3, 1, 150.00, 150.00);             
