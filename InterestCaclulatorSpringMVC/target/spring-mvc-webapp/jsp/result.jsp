<%--
  Created by IntelliJ IDEA.
  User: n0250996
  Date: 3/14/2018
  Time: 8:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <h1>Interest Calculator</h1>
    <hr/>
    <div class="navbar">
        <ul class="nav nav-tabs">
            <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/hello/sayhi">Hello Controller</a></li>
        </ul>
    </div>

    <h1>Interest Results</h1>

    <c:forEach begin="1" end="${numYears}" varStatus="loop">
        Year: ${loop.index}<br />
        Starting Balance: ${startingBalanceList[loop.index-1]}<br />
        Interest Earned: ${interestEarnedList[loop.index-1]}<br />
        Ending Balance: ${endPrincipleList[loop.index-1]}<br />
        <br />
    </c:forEach>
    <p><a href="index.jsp">Calculate More!</a></p>

</div>

</body>
</html>
