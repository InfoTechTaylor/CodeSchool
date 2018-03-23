/*
	Challenge: 
	Show the total amount of orders by
	year and country.  Data should be ordered 
	by year ascending and total descending.
	
	TotalSales    Year     Country
	41907.80      1996     USA
	37804.60      1996     Germany
	etc...
	
	Hint: Research the DatePart() function
*/

USE Northwind;

SELECT SUM(Order_Details.UnitPrice * Order_Details.quantity) AS TotalSales, DATE_FORMAT(OrderDate, "%Y") AS Year, Customers.Country
FROM Order_Details
	INNER JOIN Orders ON Orders.OrderID = Order_Details.OrderID
	INNER JOIN Customers ON Customers.CustomerID = Orders.CustomerID
GROUP BY Customers.Country
ORDER BY Year, TotalSales ASC;
    