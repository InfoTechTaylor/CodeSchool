<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: n0250996
  Date: 4/16/2018
  Time: 8:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Org</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/custom.css" rel="stylesheet">
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
                <li role="presentation" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/sighting/createList">Sightings</a></li>
                <li role="presentation" class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/organization/createList">Organizations</a></li>
                <li role="presentation" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/power/createList">Powers</a></li>
            </ul>
        </div>

    </div>
</div>

<div class="mt-5">
    <h2>Edit Organization</h2>
    <sf:form id="editPowerForm" action="/orgs/edit" method="POST"  modelAttribute="commandModel" novalidate="novalidate" >
        <div class="form-group row col-sm-12 col-lg-12 mb-2">
            <label for="orgName" class="col-sm-2 col-lg-2">Organization Name: </label>
            <div id="orgName" name="orgName" class="col-lg-4 col-sm-4">
                <sf:input type="text" id="editOrgNameInput" class="form-control" path="name"></sf:input>
                <sf:errors path="name" cssClass="errors"></sf:errors>
            </div>
        </div>

            <div class="form-group row col-sm-12 col-lg-12 mb-2">
                <label for="edit-location" class="col-sm-2 col-lg-2 col-md-2">Location: </label>
                <div class="col-lg-4 col-sm-4">
                    <sf:select path="locationId" id="edit-location" class="form-control">
                        <sf:options items="${viewModel.locations}" itemValue="id" itemLabel="name" />
                    </sf:select>
                    <sf:errors path="locationId" cssClass="errors"></sf:errors>

                </div><!--end form group col -->
            </div><!--end form group-->

        <div class="form-group row col-sm-12 col-lg-12 mb-2">
            <label for="orgDescription" class="col-sm-2 col-lg-2">Organization Name: </label>
            <div id="orgDescription" name="orgName" class="col-lg-4 col-sm-4">
                <sf:input type="text" id="orgDescription" class="form-control" path="description"></sf:input>
                <sf:errors path="description" cssClass="errors"></sf:errors>
            </div>
        </div>

        <div>
            <sf:hidden path="id"/>
            <sf:errors path="id" cssClass="errors"></sf:errors>
            <a class="btn col-sm-2 col-lg-2 mr-3" href="${pageContext.request.contextPath}/orgs/list">Cancel</a>
            <button id="editOrgBtn" type="submit" class="btn col-sm-2 col-lg-2">Save Changes</button>
        </div>
    </sf:form>
</div>
</div>
</body>
</html>
