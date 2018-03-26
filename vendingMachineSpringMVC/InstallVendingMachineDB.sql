DROP DATABASE IF EXISTS VendingMachine;

CREATE DATABASE IF NOT EXISTS VendingMachine;


USE VendingMachine;
CREATE TABLE VendingMachineItem
(
	itemID int NOT NULL auto_increment,
    itemName VARCHAR(30) NOT NULL,
    itemCost DECIMAL(4,2) NOT NULL,
    itemQuantity int NOT NULL,
    PRIMARY KEY (itemID)
    
);

INSERT INTO VendingMachineItem (itemID, itemName, itemCost, itemQuantity)
VALUES(1, 'Chips', 1.00, 2),
(2, 'Skittles', .75, 4),
(3, 'Soda', 1.25, 3),
(4, 'Cookie', .75, 0);