<%--
  Created by IntelliJ IDEA.
  User: n0250996
  Date: 4/19/2018
  Time: 9:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Super Sightings: Error</title>
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
                    <li role="presentation" class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/">Home</a></li>
                    <li role="presentation" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/person/createList">People</a></li>
                    <li role="presentation" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/location/createList">Locations</a></li>
                    <li role="presentation" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/sighting/createList">Sightings</a></li>
                    <li role="presentation" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/organization/createList">Organizations</a></li>
                    <li role="presentation" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/power/createList">Powers</a></li>
                </ul>
            </div>

        </div>
    </div>

    <div class="mt-5">
       <h1>An error has occurred...</h1>
        <h3>${errorMessage}</h3>
    </div>
</div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</body>
</html>
