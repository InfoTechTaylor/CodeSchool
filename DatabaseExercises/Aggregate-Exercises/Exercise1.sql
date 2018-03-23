/*
	Find the average freight paid for orders 
	placed by companies in the USA
*/

USE Northwind;

SELECT AVG(Freight) AS 'Average Freight'
FROM Orders
	INNER JOIN Customers ON Orders.CustomerID = Customers.CustomerID
WHERE Customers.Country = 'USA';
