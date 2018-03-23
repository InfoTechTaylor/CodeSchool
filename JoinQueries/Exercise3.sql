/*
	Get all the order information for any order where Chai was sold.
*/

USE Northwind;

SELECT 
    Orders.*
FROM
    Orders
        INNER JOIN
    Order_Details ON Orders.OrderID = Order_Details.OrderID
		INNER JOIN
	Products ON Order_Details.ProductID = Products.ProductID
WHERE Products.ProductName = 'Chai';