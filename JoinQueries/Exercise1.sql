/*
	Get a list of each employee first name and lastname
	and the territory names they are associated with
*/

USE Northwind;
SELECT 
    Employees.FirstName,
    Employees.LastName,
    Territories.TerritoryDescription
FROM
    Employees
        INNER JOIN
    EmployeeTerritories ON employees.EmployeeID = employeeterritories.EmployeeID
        INNER JOIN
    Territories ON employeeterritories.TerritoryID = territories.TerritoryID;