USE Northwind;

-- 1
/*
	Get a list of each employee first name and lastname
	and the territory names they are associated with
*/
SELECT e.FirstName, e.LastName, t.TerritoryDescription
FROM Employees e
	INNER JOIN EmployeeTerritories et ON e.EmployeeID = et.EmployeeID
	INNER JOIN Territories t ON et.TerritoryID = t.TerritoryID;
-- 2
/*
	Get the Company Name, Order Date, and each order details 
	Product name for USA customers only.
*/
SELECT c.CompanyName, o.OrderDate, p.ProductName
FROM Customers c
	INNER JOIN Orders o ON c.CustomerID = o.CustomerID
	INNER JOIN order_details od ON o.OrderID = od.OrderID
	INNER JOIN Products p ON od.ProductID = p.ProductID
WHERE c.Country = 'USA';
 
-- 3
/*
	Get all the order information for any order where Chai was sold.
*/
SELECT o.*
FROM Products p
	INNER JOIN order_details od ON od.ProductID = p.ProductID
	INNER JOIN Orders o ON o.OrderID = od.OrderID
WHERE p.ProductName = 'Chai';



USE SWCCorp;

-- 4
/*
	Write a query to show every combination of employee and location.
*/
SELECT e.FirstName, e.LastName, l.City
FROM Employee e CROSS JOIN Location l;

-- 5
/*
	Find a list of all the Employees who have never found a Grant
*/
SELECT e.FirstName, e.LastName
FROM Employee e
	LEFT JOIN `Grant` g ON e.EmpID = g.EmpID
WHERE g.GrantID IS NULL;


