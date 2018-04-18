<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: n0250996
  Date: 4/15/2018
  Time: 6:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Power</title>
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
                <li role="presentation" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/people/list">People</a></li>
                <li role="presentation" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/jsp/location/createList.jsp">Locations</a></li>
                <li role="presentation" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/jsp/sighting/sightingCreateList.jsp">Sightings</a></li>
                <li role="presentation" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/orgs/list">Organizations</a></li>
                <li role="presentation" class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/power/createList">Powers</a></li>
            </ul>
        </div>

    </div>
</div>
<div class="container mt-5">
<h2>Edit Power</h2>
<sf:form id="editPowerForm" action="/power/edit" method="POST"  modelAttribute="commandModel" novalidate="novalidate" >
    <div class="form-group row col-sm-12 col-lg-12 mb-2">
        <label for="powerName" class="col-sm-2 col-lg-2">Power Name: </label>
        <div id="powerName" name="powerName" class="col-lg-4 col-sm-4">
            <sf:input type="text" id="editPowerNameInput" class="form-control" path="name"></sf:input>
            <sf:errors path="name" cssClass="errors"></sf:errors>
        </div>
    </div>
    <div>
        <sf:hidden path="id"/>
        <a class="btn col-sm-2 col-lg-2 mr-3" href="${pageContext.request.contextPath}/power/createList">Cancel</a>
        <button id="editPowerBtn" type="submit" class="btn col-sm-2 col-lg-2">Save Changes</button>
    </div>
</sf:form>
</div>
</body>
</html>
