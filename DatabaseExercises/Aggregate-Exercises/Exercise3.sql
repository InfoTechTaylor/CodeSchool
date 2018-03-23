/*
	Find the gross total of all orders (sum of quantity * unit price) 
	for each customer, order it in descending order by the total.
*/

USE Northwind;

SELECT Customers.CompanyName, SUM(quantity * unitPrice) AS 'Gross Total'
FROM Order_Details
	INNER JOIN Orders ON Orders.OrderID = Order_Details.OrderID
    INNER JOIN Customers ON Orders.CustomerID = Customers.CustomerID
GROUP BY CompanyName
ORDER BY 'Gross Total' DESC;