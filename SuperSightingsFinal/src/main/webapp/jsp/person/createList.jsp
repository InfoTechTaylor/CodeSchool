<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: n0250996
  Date: 4/14/2018
  Time: 9:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>People</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/custom.css" rel="stylesheet">
    <link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/jsp/images/favicon.png" />
</head>
<body>
<div class="container mb-5 pl-5 pr-5 rounded">
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
                <li role="presentation" class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/person/createList">People</a></li>
                <li role="presentation" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/location/createList">Locations</a></li>
                <li role="presentation" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/sighting/createList">Sightings</a></li>
                <li role="presentation" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/organization/createList">Organizations</a></li>
                <li role="presentation" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/power/createList">Powers</a></li>
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
            <h2>People</h2>
            <table id="personTable" class="table table-hover">
                <tr>
                    <th width="40%">Name</th>
                    <th width="30%">Type</th>
                    <th width="15%"></th>
                    <th width="15%"></th>
                </tr>
                <c:forEach var="person" items="${personsList}">
                    <tr>
                        <td>
                            <p>
                                <a href="/person/show?id=${person.id}"><c:out value="${person.name} " /></a>
                            </p>
                        </td>
                        <td>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <a href="/person/edit?id=${person.id}">
                                Edit
                            </a>
                            </sec:authorize>
                        </td>
                        <td>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <a href="/person/delete?id=${person.id}"
                               onclick="return confirm('Are you sure you want to delete ${person.name}?')">
                                Delete
                            </a>
                            </sec:authorize>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <div class="text-center pb-5">
                <ul class="pagination">
                    <c:forEach items="${viewModel.pageNumbers}" var="pageNumber">
                        <!-- do math for page number cause our page utils are wrong -->
                        <li class="page-item"><a href="/person/createList?offset=${(pageNumber-1) * 5}" class="page-link">${pageNumber}</a></li>
                    </c:forEach>
                </ul>
            </div>

        </div><!-- end left col div-->

        <!-- add col to hold the new contact form - have it take up the other half of the row -->
        <div class="col-md-6">
<sec:authorize access="hasRole('ROLE_ADMIN')">
            <h2>Add New Person</h2>
            <sf:form class="form-horizontal" role="form" method="POST" action="/person/create" modelAttribute="commandModel">
                <div class="form-group row">
                    <label for="add-name" class="col-sm-4 col-lg-4 col-md-4">Name: </label>
                    <div class="col-sm-6 col-lg-6 col-md-6">
                        <sf:input path="name" id="add-name" type="text" class="form-control" name="name" placeholder="Name" />
                        <sf:errors path="name" cssClass="errors"/>
                    </div><!--end form group col -->
                </div><!--end form group-->

                <div class="form-group row">
                    <label for="add-description" class="col-sm-4 col-lg-4 col-md-4">Description: </label>
                    <div class="col-sm-6 col-lg-6 col-md-6">
                        <sf:textarea path="description" rows="3" id="add-description" type="text" class="form-control" name="name" placeholder="Description" ></sf:textarea>
                        <sf:errors path="description" cssClass="errors"/>
                    </div><!--end form group col -->
                </div><!--end form group-->

                <div class="form-group row">
                    <label for="add-type" class="col-sm-4 col-lg-4 col-md-4">Type: </label>
                    <div class="col-sm-6 col-lg-6 col-md-6">
                        <sf:select path="type" id="add-type" class="form-control">
                            <sf:option value="" label="Select Type" />
                            <sf:option value="Person" label="Person" />
                            <sf:option value="Hero" label="Hero" />
                            <sf:option value="Villian" label="Villian" />
                        </sf:select>
                        <sf:errors path="type" cssClass="errors"/>

                    </div><!--end form group col -->
                </div><!--end form group-->

                <div class="form-group row">
                    <label for="add-org" class="col-sm-4 col-lg-4 col-md-4">Organization(s): </label>
                    <div class="col-sm-6 col-lg-6 col-md-6">
                        <sf:select path="orgIds" id="add-org" class="form-control">
                            <sf:option value="" label="Select 1 or more Orgs" />
                            <sf:options items="${viewModel.organizations}" itemValue="id" itemLabel="name" />
                        </sf:select>


                    </div><!--end form group col -->
                </div><!--end form group-->

                <div class="form-group row">
                    <label for="add-powers" class="col-sm-4 col-lg-4 col-md-4">Power(s): </label>
                    <div class="col-sm-6 col-lg-6 col-md-6">
                        <sf:select path="powerIds" id="add-powers" class="form-control">
                            <sf:option value="" label="Select 1 or more powers" />
                            <sf:options items="${viewModel.powers}" itemValue="id" itemLabel="name" />
                        </sf:select>

                    </div><!--end form group col -->
                </div><!--end form group-->
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Add" />
                    </div><!--end form group col -->
                </div><!--end form group-->
            </sf:form>
</sec:authorize>
        </div><!--end right col div-->
    </div><!--end row div-->
</div>
</div>
</body>
</html>
