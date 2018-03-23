/*
	Find the total sales by supplier 
	ordered from most to least.
*/

USE Northwind;

SELECT s.CompanyName, SUM(o.UnitPrice * o.quantity) AS TotalSales
FROM Suppliers s
	INNER JOIN Products p ON p.SupplierID = s.SupplierID
    INNER JOIN Order_Details o ON p.ProductID = o.ProductID
GROUP BY s.CompanyName
ORDER BY TotalSales DESC;

