<%--
  Created by IntelliJ IDEA.
  User: n0250996
  Date: 3/13/2018
  Time: 10:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Result</title>
</head>
<body>
<h1>Result</h1>

<p>You entered: </p>
<p>Width: ${floorWidth}</p>
<p>Length: ${floorLength}</p>
<p>Cost Per Square Foot: ${costPerSquareFoot}</p>

<h3>Totals:</h3>
<p>Total Material Cost: $${costOfMaterial}</p>
<p>Total Time of Labor: ${totalTimeRequired} hours</p>
<p>Total Time Charged: ${totalTimeCharged} hours</p>
<p>Total Labor Cost: $${laborCost}</p>
<p>Total Cost: $${totalCost}</p>

<p><a href="/FlooringCalculatorServlet">Calculate More!</a></p>

</body>
</html>
