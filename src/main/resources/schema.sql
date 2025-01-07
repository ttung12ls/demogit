DROP TABLE IF EXISTS orderdetails;
DROP TABLE IF EXISTS orders;

CREATE TABLE orders (
  OrderID int NOT NULL AUTO_INCREMENT,
  Customer varchar(255) NOT NULL,
  OrderDate datetime NOT NULL,
  Freight decimal(10,2) DEFAULT '0.00',
  ShipCountry varchar(100) NOT NULL,
  ShippingCompany varchar(100) NOT NULL,
  PRIMARY KEY (OrderID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE orderdetails (
  DetailID int NOT NULL AUTO_INCREMENT,
  OrderID int DEFAULT NULL,
  Product varchar(255) DEFAULT NULL,
  Price decimal(10,2) DEFAULT NULL,
  Quantity int DEFAULT NULL,
  Sum decimal(10,2) GENERATED ALWAYS AS ((Quantity * Price)) STORED,
  PRIMARY KEY (DetailID),
  KEY OrderID (OrderID),
  CONSTRAINT orderdetails_ibfk_1 FOREIGN KEY (OrderID) REFERENCES orders (OrderID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
