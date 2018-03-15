<%--
  Created by IntelliJ IDEA.
  User: n0250996
  Date: 3/14/2018
  Time: 9:26 PM
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
    <h2>Tip Amount</h2>
    <p>Amount: ${billAmount}</p>
    <p>Tip %: ${tipPercentage}</p>
    <p>Tip Amount: ${tipAmount}</p>
    <p>Total Bill: ${totalBill}</p>
    <p><a href="index.jsp">Calculate Another Tip</a></p>
</div>

</body>
</html>
