<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
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
    <title>Edit Sighting</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/custom.css" rel="stylesheet">
    <link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/jsp/images/favicon.png" />
</head>
<body>
<div class="container mb-5 pl-5 pr-5 pb-5 rounded">
<div id="nav">
    <div class="mt-5">
        <div  class="pt-5 row">
            <div class="col-lg-8"><h1>Super Sightings</h1></div>
            <div class="col-lg-4 text-right">
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <p>Hello ${pageContext.request.userPrincipal.name}!
                        | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                    </p>
                </c:if>
            </div>
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

<div>
    <h2>Edit Sighting</h2>
    <sf:form class="form-horizontal" role="form" method="POST" action="/sighting/edit" modelAttribute="commandModel" novalidate="novalidate">
        <div class="form-group row">
            <label for="add-date" class="col-sm-4 col-lg-4 col-md-4">Date: </label>
            <div class="col-sm-6 col-lg-6 col-md-6">
                <sf:input path="date" id="add-date" type="date" class="form-control" name="date" placeholder="Date of Sighting" />
                <sf:errors path="date" cssClass="errors"></sf:errors>
            </div><!--end form group col -->
        </div><!--end form group-->
        <div class="form-group row">
            <label for="add-description" class="col-sm-4 col-lg-4 col-md-4">Description: </label>
            <div class="col-sm-6 col-lg-6 col-md-6">
                <sf:textarea path="description" rows="3" id="add-description" type="text" class="form-control" name="name" placeholder="Sighting Description" ></sf:textarea>
                <sf:errors path="description" cssClass="errors"></sf:errors>
            </div><!--end form group col -->
        </div><!--end form group-->
        <div class="form-group row">
            <label for="add-location" class="col-sm-4 col-lg-4 col-md-4">Location: </label>
            <div class="col-sm-6 col-lg-6 col-md-6">
                <sf:select path="locationId" id="add-location" class="form-control">
                    <sf:option value="" label="Select Location" />
                    <sf:options items="${viewModel.locations}" itemValue="id" itemLabel="name" />
                </sf:select>
                <sf:errors path="locationId" cssClass="errors"></sf:errors>
            </div><!--end form group col -->
        </div><!--end form group-->
        <div class="form-group row">
            <label for="add-persons" class="col-sm-4 col-lg-4 col-md-4">People: </label>
            <div class="col-sm-6 col-lg-6 col-md-6">
                <sf:select path="personIds" id="add-persons" class="form-control">
                    <sf:option value="" label="Select people" />
                    <sf:options items="${viewModel.persons}" itemValue="id" itemLabel="name"  />
                </sf:select>
                <sf:errors path="personIds" cssClass="errors"></sf:errors>
                <sf:hidden path="id" />
                <sf:errors path="id" cssClass="errors"></sf:errors>

            </div><!--end form group col -->
        </div><!--end form group-->
        <div class="form-group">
            <div class="col-md-offset-4 col-md-8">
                <a href="/sighting/createList" class="btn btn-secondary">Cancel</a>
                <input type="submit" class="btn btn-default" value="Submit" />
            </div><!--end form group col -->
        </div><!--end form group-->
    </sf:form>
</div>
</div>
</body>
</html>
