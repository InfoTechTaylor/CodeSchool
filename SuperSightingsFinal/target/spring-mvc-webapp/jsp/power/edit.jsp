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
                <li role="presentation" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/home/latest">Home</a></li>
                <li role="presentation" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/person/createList">People</a></li>
                <li role="presentation" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/location/createList">Locations</a></li>
                <li role="presentation" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/sighting/createList">Sightings</a></li>
                <li role="presentation" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/organization/createList">Organizations</a></li>
                <li role="presentation" class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/power/createList">Powers</a></li>
            </ul>
        </div>

    </div>
</div>
<div class="mt-5">
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
        <sf:errors path="id" cssClass="errors"></sf:errors>
        <a class="btn col-sm-2 col-lg-2 mr-3" href="${pageContext.request.contextPath}/power/createList">Cancel</a>
        <button id="editPowerBtn" type="submit" class="btn col-sm-2 col-lg-2">Save Changes</button>
    </div>
</sf:form>
</div>
</div>
</body>
</html>
