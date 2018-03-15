<%--
  Created by IntelliJ IDEA.
  User: n0250996
  Date: 3/14/2018
  Time: 8:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
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

    <p><a href="index.jsp">Calculate More!</a></p>
</div>
</body>
</html>
