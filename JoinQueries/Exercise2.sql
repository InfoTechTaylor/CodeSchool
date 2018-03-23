/*
	Get the Company Name, Order Date, and each order details 
	Product name for USA customers only.
*/

USE Northwind;

SELECT 
    Customers.CompanyName,
    Orders.OrderDate,
    Products.ProductName
FROM
    Customers
        INNER JOIN
    Orders ON Customers.CustomerID = Orders.CustomerID
		INNER JOIN
	Order_Details ON Orders.OrderID = Order_Details.OrderID
		INNER JOIN
	Products ON Order_Details.ProductID = Products.ProductID
WHERE Customers.Country = 'USA';