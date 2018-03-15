<%--
  Created by IntelliJ IDEA.
  User: n0250996
  Date: 3/14/2018
  Time: 8:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lucky Sevens</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1>Lucky Sevens</h1>
    <hr/>
    <div class="navbar">
        <ul class="nav nav-tabs">
            <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/hello/sayhi">Hello Controller</a></li>
        </ul>
    </div>
    <h1>Lucky Seven Results!</h1>

    <p>You bet $${startingBet}</p>
    <p>You are broke after ${resultObj.totalNumberOfRolls} rolls</p>
    <p>You should have quit after ${resultObj.numberOfRollsAtMaxAmount} rolls when you had $${resultObj.maxAmount}</p>
    <p><a href="index.jsp">Play Again!</a></p>
</div>

</body>
</html>
