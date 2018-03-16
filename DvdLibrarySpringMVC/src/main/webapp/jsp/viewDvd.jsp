<%--
  Created by IntelliJ IDEA.
  User: n0250996
  Date: 3/15/2018
  Time: 8:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Dvd Library</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div id="dvdInfoDiv" class="container mt-5">
    <h1 id="dvdInfoH1"><c:out value="${dvd.title}" /></h1>
    <hr class="col-lg-12 col-sm-12" />
    <div>
        <p class="col-sm-2 col-lg-2">Release Year: <c:out value="${dvd.releaseYear}"/></p>
        <p class="col-sm-2 col-lg-2">Director: <c:out value="${dvd.director}"/></p>
        <p class="col-sm-2 col-lg-2">Rating: <c:out value="${dvd.rating}"/></p>
        <p class="col-sm-2 col-lg-2">Notes: <c:out value="${dvd.notes}"/></p>
        <a class="btn btn-outline-primary col-sm-1 col-lg-1 mr-3" href="${pageContext.request.contextPath}/">Back</a>
    </div>
</div>

<!-- Placed at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>
