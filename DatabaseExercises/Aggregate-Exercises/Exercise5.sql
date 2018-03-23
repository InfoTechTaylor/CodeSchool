/*
	Get the count of how many employees 
	report to someone else in the company 
	without using a WHERE clause.
*/

USE Northwind;

SELECT COUNT(*) AS EmployeeCount
FROM Employees e1
INNER JOIN Employees e2 on e1.EmployeeID = e2.EmployeeID;

