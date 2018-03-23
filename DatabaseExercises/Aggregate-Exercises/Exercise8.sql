/*
	Show the number of orders placed by customers 
	from fewest to most provided the customer has 
	a minimum of 4 orders.
*/

USE Northwind;

SELECT Customers.CompanyName, COUNT(Orders.OrderID) AS TotalOrders
FROM Customers
INNER JOIN Orders ON Customers.customerID = Orders.customerID
GROUP BY Customers.CompanyName
HAVING COUNT(Orders.OrderID) >= 4
ORDER BY TotalOrders;