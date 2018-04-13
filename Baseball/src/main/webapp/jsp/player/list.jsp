<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: n0250996
  Date: 4/13/2018
  Time: 9:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Player List</title>
</head>
<body>
        <table>
        <c:forEach items="${viewModel.players}" var="player">
            <tr>
                <td><a href="/player/show?id=${player.id}">${player.firstName} ${player.lastName}</a></td>
                <td><a href="/player/edit?id=${player.id}">edit</a></td>
            </tr>
        </c:forEach>
    </table>

        <!-- links to same page with different offsets -->
    <c:forEach items="${viewModel.pageNumbers}" var="pageNumber">
        <!-- do math for page number cause our page utils are wrong -->
        <a href="/player/list?offset=${(pageNumber - 1) * 5}">${pageNumber}</a>
    </c:forEach>

<br />
<a href="/player/create">Create New Player</a>

</body>
</html>
