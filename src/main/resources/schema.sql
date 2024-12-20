DROP TABLE IF EXISTS orders;

CREATE TABLE orders (
    order_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer VARCHAR(255) NOT NULL,
    created_date DATETIME NOT NULL,
    status VARCHAR(50) NOT NULL,
    freight DECIMAL(10,2),
    ship_country VARCHAR(100),
    shipping_company VARCHAR(100)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
