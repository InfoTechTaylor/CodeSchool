/*
	Find a list of all the Employees who have never found a Grant
*/

USE SWCCorp;

SELECT Employee.FirstName, Employee.LastName
FROM Employee
LEFT JOIN `grant` ON Employee.EmpID = `grant`.EmpID
WHERE `grant`.GrantID IS NULL;