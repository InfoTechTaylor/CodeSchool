<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: n0250996
  Date: 4/16/2018
  Time: 2:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Location</title>
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
                <li role="presentation" class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/location/createList">Locations</a></li>
                <li role="presentation" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/sighting/createList">Sightings</a></li>
                <li role="presentation" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/organization/createList">Organizations</a></li>
                <li role="presentation" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/power/createList">Powers</a></li>
            </ul>
        </div>

    </div>
</div>
<div >
<h2>Edit Location</h2>

<sf:form class="form-horizontal" role="form" method="POST" action="/location/edit" modelAttribute="commandModel">
    <div class="form-group row">
        <label for="add-name" class="col-sm-4 col-lg-4 col-md-4">Name: </label>
        <div class="col-sm-6 col-lg-6 col-md-6">
            <sf:input path="name" id="add-name" type="text" class="form-control" name="name" placeholder="Location Name" />
            <sf:errors path="name" cssClass="errors"></sf:errors>
        </div><!--end form group col -->
    </div><!--end form group-->
    <div class="form-group row">
        <label for="add-description" class="col-sm-4 col-lg-4 col-md-4">Description: </label>
        <div class="col-sm-6 col-lg-6 col-md-6">
            <sf:textarea path="description" rows="3" id="add-description" type="text" class="form-control" name="description" placeholder="Location description" ></sf:textarea>
            <sf:errors path="description" cssClass="errors"></sf:errors>
        </div><!--end form group col -->
    </div><!--end form group-->
    <div class="form-group row">
        <label for="add-street" class="col-sm-4 col-lg-4 col-md-4">Street: </label>
        <div class="col-sm-6 col-lg-6 col-md-6">
            <sf:input path="street" id="add-street" type="text" class="form-control" name="street" placeholder="Street Address" ></sf:input>
            <sf:errors path="street" cssClass="errors"></sf:errors>
        </div><!--end form group col -->
    </div><!--end form group-->
    <div class="form-group row">
        <label for="add-city" class="col-sm-4 col-lg-4 col-md-4">City: </label>
        <div class="col-sm-6 col-lg-6 col-md-6">
            <sf:input path="city" id="add-city" type="text" class="form-control" name="city" placeholder="City" ></sf:input>
            <sf:errors path="city" cssClass="errors"></sf:errors>
        </div><!--end form group col -->
    </div><!--end form group-->
    <div class="form-group row">
        <label for="add-state" class="col-sm-4 col-lg-4 col-md-4">State: </label>
        <div class="col-sm-6 col-lg-6 col-md-6">
            <sf:input path="state" id="add-state" type="text" class="form-control" name="state" placeholder="State" ></sf:input>
            <sf:errors path="state" cssClass="errors"></sf:errors>
        </div><!--end form group col -->
    </div><!--end form group-->
    <div class="form-group row">
        <label for="add-zip" class="col-sm-4 col-lg-4 col-md-4">Zip Code: </label>
        <div class="col-sm-6 col-lg-6 col-md-6">
            <sf:input path="zip" id="add-zip" type="text" class="form-control" name="zip" placeholder="zip" ></sf:input>
            <sf:errors path="zip" cssClass="errors"></sf:errors>
        </div><!--end form group col -->
    </div><!--end form group-->
    <div class="form-group row">
        <label for="add-country" class="col-sm-4 col-lg-4 col-md-4">Country: </label>
        <div class="col-sm-6 col-lg-6 col-md-6">
            <sf:input path="country" id="add-country" type="text" class="form-control" name="state" placeholder="country" ></sf:input>
            <sf:errors path="country" cssClass="errors"></sf:errors>
        </div><!--end form group col -->
    </div><!--end form group-->
    <div class="form-group row">
        <label for="add-latitude" class="col-sm-4 col-lg-4 col-md-4">Latitude: </label>
        <div class="col-sm-6 col-lg-6 col-md-6">
            <sf:input path="latitude" id="add-latitude" type="text" class="form-control" name="latitude" placeholder="latitude" ></sf:input>

        </div><!--end form group col -->
    </div><!--end form group-->
    <div class="form-group row">
        <label for="add-longitude" class="col-sm-4 col-lg-4 col-md-4">Longitude: </label>
        <div class="col-sm-6 col-lg-6 col-md-6">
            <sf:input path="longitude" id="add-longitude" type="text" class="form-control" name="longitude" placeholder="longitude" ></sf:input>

        </div><!--end form group col -->
    </div><!--end form group-->
    <div class="form-group">
        <sf:hidden path="id"/>
        <sf:errors path="id" cssClass="errors"></sf:errors>
        <div class="col-md-offset-4 col-md-8">
            <a href="/location/createList" class="btn btn-secondary">Cancel</a>
            <input type="submit" class="btn btn-default" value="Submit" />
        </div><!--end form group col -->
    </div><!--end form group-->
</sf:form>
</div>
</div>
</body>
</html>
