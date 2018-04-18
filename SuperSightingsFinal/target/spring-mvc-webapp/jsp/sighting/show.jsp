<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: n0250996
  Date: 4/16/2018
  Time: 8:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show Sighting</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/custom.css" rel="stylesheet">
</head>
<body>
<div class="container mb-5 pl-5 pr-5 pb-5 rounded">
<div id="nav">
    <div class="mt-5">
        <div  class="pt-5">
            <h1>Super Sightings</h1>
        </div>
        <hr/>
        <div id="navbar">
            <ul class="nav nav-tabs">
                <li role="presentation" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/">Home</a></li>
                <li role="presentation" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/person/createList">People</a></li>
                <li role="presentation" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/location/createList">Locations</a></li>
                <li role="presentation" class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/sighting/createList">Sightings</a></li>
                <li role="presentation" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/organization/createList">Organizations</a></li>
                <li role="presentation" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/power/createList">Powers</a></li>
            </ul>
        </div>

    </div>
</div>

<div class="mt-5">
    <h2>Sighting Profile</h2>
    <p>Date: ${viewModel.date} </p>
    <p>Location: <a href="/location/show?id=${viewModel.locationId}">${viewModel.locationName}</a></p>
    <p>Persons spotted: <c:forEach var="person" items="${personsList}">
        <a href="/person/show?id=${person.id}"> <c:out value="${person.name};"/></a>
    </c:forEach></p>
    <p>Description: <c:out value="${viewModel.description}"/></p>
    <a class="btn btn-secondary" href="/sighting/createList" >Return</a>
</div>
</div>
</body>
</html>
