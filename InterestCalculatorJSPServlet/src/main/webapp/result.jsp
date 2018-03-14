<%--
  Created by IntelliJ IDEA.
  User: n0250996
  Date: 3/13/2018
  Time: 2:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<DOCTYPE html>
<html>
<head>
    <title>Result</title>
</head>
<body>
<h1>Interest Results</h1>

<c:forEach begin="1" end="${numYears}" varStatus="loop">
    Year: ${loop.index}<br />
    Starting Balance: ${startingBalanceList[loop.index-1]}<br />
    Interest Earned: ${interestEarnedList[loop.index-1]}<br />
    Ending Balance: ${endPrincipleList[loop.index-1]}<br />
    <br />
</c:forEach>
<p><a href="index.jsp">Calculate More!</a></p>



</body>
</html>
