DROP database if exists flooringDB;

CREATE DATABASE IF NOT EXISTS flooringDB;

USE flooringDB;

CREATE TABLE tax
(
	taxId int NOT NULL auto_increment,
    state VARCHAR(2) NOT NULL,
    taxRate DECIMAL(4,2),
    PRIMARY KEY(taxId)
);

CREATE TABLE product
(
	productId int NOT NULL auto_increment,
    productType VARCHAR(20) NOT NULL,
    materialCostPerSquareFoot DECIMAL(7,2) NOT NULL,
    laborCostPerSquareFoot DECIMAL(7,2) NOT NULL,
    PRIMARY KEY (productId)
);

CREATE TABLE customer_order
(
	orderNumber VARCHAR(128) NOT NULL,
    orderDate DATE NOT NULL,
    customerName VARCHAR(60) NOT NULL,
    taxId int NOT NULL,
    productId int NOT NULL,
    area DECIMAL NOT NULL,
    totalMaterialCost DECIMAL NOT NULL,
    totalLaborCost DECIMAL NOT NULL,
    totalTax DECIMAL NOT NULL,
    totalCost DECIMAL NOT NULL,
    PRIMARY KEY (orderNumber)
);

ALTER TABLE customer_order
ADD CONSTRAINT fk_customer_order_tax
FOREIGN KEY (taxId) REFERENCES tax(taxId);

ALTER TABLE customer_order
ADD CONSTRAINT fk_customer_order_product
FOREIGN KEY (productId) REFERENCES product(productId);






DROP database if exists flooringDB_test;

CREATE DATABASE IF NOT EXISTS flooringDB_test;

USE flooringDB_test;

CREATE TABLE tax
(
	taxId int NOT NULL auto_increment,
    state VARCHAR(2) NOT NULL,
    taxRate DECIMAL(4,2),
    PRIMARY KEY(taxId)
);

CREATE TABLE product
(
	productId int NOT NULL auto_increment,
    productType VARCHAR(20) NOT NULL,
    materialCostPerSquareFoot DECIMAL(7,2) NOT NULL,
    laborCostPerSquareFoot DECIMAL(7,2) NOT NULL,
    PRIMARY KEY (productId)
);

CREATE TABLE customer_order
(
	orderNumber VARCHAR(128) NOT NULL,
    orderDate DATE NOT NULL,
    customerName VARCHAR(60) NOT NULL,
    taxId int NOT NULL,
    productId int NOT NULL,
    area DECIMAL NOT NULL,
    totalMaterialCost DECIMAL NOT NULL,
    totalLaborCost DECIMAL NOT NULL,
    totalTax DECIMAL NOT NULL,
    totalCost DECIMAL NOT NULL,
    PRIMARY KEY (orderNumber)
);

ALTER TABLE customer_order
ADD CONSTRAINT fk_customer_order_tax
FOREIGN KEY (taxId) REFERENCES tax(taxId);

ALTER TABLE customer_order
ADD CONSTRAINT fk_customer_order_product
FOREIGN KEY (productId) REFERENCES product(productId);

USE flooringdb_test;
SELECT * FROM Product;