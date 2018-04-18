<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Home</title>
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
                	<li role="presentation" class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/home/latest">Home</a></li>
                	<li role="presentation" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/person/createList">People</a></li>
                    <li role="presentation" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/location/createList">Locations</a></li>
                    <li role="presentation" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/sighting/createList">Sightings</a></li>
                    <li role="presentation" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/organization/createList">Organizations</a></li>
                    <li role="presentation" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/power/createList">Powers</a></li>
                </ul>    
            </div>

        </div>
    </div>

        <div class="container mt-5">
            <div id="about" class="mt-5">
                <h2>About</h2>
                <p>Super Sightings is the official website for tracking all super heroes, villians, and extraordinary
                    people! In addition to tracking sightings of these super people, we are also the most reliable source
                    of all super organizations and powers. </p>
            </div>
            <h2>Latest Sightings</h2>

            <table class="table table-bordered mt-2">
                <thead class="table-dark">
                    <tr>
                        <th>Date</th>
                        <th>Location</th>
                        <th>Description</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${viewModel.sightings}" var="sighting">
                    <tr>
                        <td>${sighting.date}</td>
                        <td>${sighting.locationName}</td>
                        <td>${sighting.description}</td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
            <div class="container mb-5">
            <c:forEach items="${viewModel.pageNumbers}" var="pageNumber">
                <!-- do math for page number cause our page utils are wrong -->
                <a href="/home/latest?offset=${(pageNumber-1) * 5}">${pageNumber}</a>
            </c:forEach>
            </div>
            <%--<div class=" container row">--%>
                <%--<a class="btn btn-sm btn-light">Previous</a>--%>
                <%--<div class="float-right">--%>
                <%--<a class="btn btn-sm btn-light">Next</a>--%>
                <%--</div>--%>
            <%--</div>--%>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

