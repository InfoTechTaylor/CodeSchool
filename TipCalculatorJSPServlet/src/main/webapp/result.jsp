<%--
  Created by IntelliJ IDEA.
  User: n0250996
  Date: 3/13/2018
  Time: 8:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Tip Amount</h2>
<p>Amount: ${billAmount}</p>
<p>Tip %: ${tipPercentage}</p>
<p>Tip Amount: ${tipAmount}</p>
<p>Total Bill: ${totalBill}</p>
<p><a href="index.jsp">Calculate Another Tip</a></p>

</body>
</html>
