<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: n0250996
  Date: 4/14/2018
  Time: 9:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Powers</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/custom.css" rel="stylesheet">
</head>
<body>
<div class="container mb-5 pl-5 pr-5 rounded">
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

<div id="main" class="mt-5">
<div class="row">
    <!-- Add a col to hold the summary table - have it take up half the row -->
    <div class="col-md-6">
        <div id="errorMessages" class="alert-danger">
            <p>
                <c:out value="${errorMessage}"/>
            </p>
        </div>
        <h2>Powers</h2>
        <table id="powerTable" class="table table-hover">
            <tr>
                <th width="70%">Name</th>
                <th width="15%"></th>
                <th width="15%"></th>
            </tr>
            <c:forEach var="power" items="${powerList}">
                <tr>
                    <td>
                        <p>
                            <c:out value="${power.name} " />
                        </p>
                    </td>
                    <td>
                        <a href="/power/edit?id=${power.id}">
                            Edit
                        </a>
                    </td>
                    <td>
                        <a href="/power/delete?id=${power.id}"
                           onclick="return confirm('Are you sure you want to delete this ${power.name}?')">
                            Delete
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div class="text-center pb-5">
            <ul class="pagination">
                <c:forEach items="${viewModel.pageNumbers}" var="pageNumber">
                    <!-- do math for page number cause our page utils are wrong -->
                    <li class="page-item"><a href="/power/createList?offset=${(pageNumber-1) * 5}" class="page-link">${pageNumber}</a></li>
                </c:forEach>
            </ul>
        </div>

    </div><!-- end left col div-->

    <!-- add col to hold the new contact form - have it take up the other half of the row -->
    <div class="col-md-6">
        <h2>Add New Power</h2>
        <sf:form class="form-horizontal" role="form" method="POST" action="/power/create" modelAttribute="commandModel" novalidate="novalidate">
            <div class="form-group row">
                <label for="add-name" class="col-sm-4 col-lg-4 col-md-4">Power Name: </label>
                <div class="col-sm-6 col-lg-6 col-md-6">
                    <sf:input path="name" id="add-name" type="text" class="form-control" name="name" placeholder="Power Name" />
                    <sf:errors path="name" cssClass="errors"></sf:errors>
                </div><!--end form group col -->
            </div><!--end form group-->
            <div class="form-group">
                <div class="col-md-offset-4 col-md-8">
                    <input type="submit" class="btn btn-default" value="Add Power" />
                </div><!--end form group col -->
            </div><!--end form group-->
        </sf:form>
    </div><!--end right col div-->
</div><!--end row div-->
</div>
</div>
</body>
</html>
