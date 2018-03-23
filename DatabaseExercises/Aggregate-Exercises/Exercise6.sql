/*
	Get the count of how many unique countries
	are represented by our suppliers.
*/

USE Northwind;

SELECT COUNT(DISTINCT Country) AS DistinctCountryCount
FROM Suppliers;