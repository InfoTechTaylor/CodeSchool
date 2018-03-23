/*
	Write a query to show every combination of employee and location.
*/

USE SWCCorp;

SELECT employee.FirstName, employee.LastName, location.city
FROM employee
CROSS JOIN location;