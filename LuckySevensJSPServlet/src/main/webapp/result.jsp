<%--
  Created by IntelliJ IDEA.
  User: n0250996
  Date: 3/13/2018
  Time: 6:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Result</title>
</head>
<body>
<h1>Lucky Seven Results!</h1>

<p>You bet $${startingBet}</p>
<p>You are broke after ${resultObj.totalNumberOfRolls} rolls</p>
<p>You should have quit after ${resultObj.numberOfRollsAtMaxAmount} rolls when you had $${resultObj.maxAmount}</p>

<p><a href="index.jsp">Play Again!</a></p>

</body>
</html>
