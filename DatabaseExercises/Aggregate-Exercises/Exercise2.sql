/*
	Find the gross total (sum of quantity * unit price) for 
	all orders placed by B's Beverages and Chop-suey Chinese.
*/

USE Northwind;

SELECT SUM(order_details.Quantity * order_details.UnitPrice) AS 'Gross Total'
FROM order_details
	INNER JOIN Orders ON Orders.OrderID = Order_Details.OrderID
    INNER JOIN Customers ON Orders.CustomerID = Customers.CustomerID
WHERE Customers.CompanyName IN ('B\'s Beverages', 'Chop-suey Chinese');