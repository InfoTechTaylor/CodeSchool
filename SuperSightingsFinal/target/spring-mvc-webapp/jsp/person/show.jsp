<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: n0250996
  Date: 4/16/2018
  Time: 8:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show Person</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<div id="nav">
    <div class="container mt-5">
        <h1>Super Sightings</h1>
        <hr/>
        <div id="navbar">
            <ul class="nav nav-tabs">
                <li role="presentation" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/home/latest">Home</a></li>
                <li role="presentation" class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/person/createList">People</a></li>
                <li role="presentation" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/location/createList">Locations</a></li>
                <li role="presentation" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/sighting/createList">Sightings</a></li>
                <li role="presentation" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/organization/createList">Organizations</a></li>
                <li role="presentation" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/power/createList">Powers</a></li>
            </ul>
        </div>

    </div>
</div>

<div class="container mt-5">
    <h2>${viewModel.name}</h2>
    <p>Type: ${viewModel.type}</p>
    <p>Description: ${viewModel.description}</p>
    <p>Organizations: <c:forEach var="org" items="${viewModel.organizations}">
                            <a href="/organization/show?id=${org.id}"><c:out value="${org.name}; " /></a>
                        </c:forEach>
    </p>
    <p>Powers: <c:forEach var="power" items="${viewModel.powers}">
                    <c:out value="${power.name}; " />
            </c:forEach></p>
    <a class="btn btn-secondary" href="/person/createList" >Return</a>
</div>

</body>
</html>
