<%--
  Created by IntelliJ IDEA.
  User: n0250996
  Date: 3/15/2018
  Time: 8:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Directive for Spring Form tag libraries -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Dvd Library</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div id="editDvdDiv" class="container mt-5">
    <h1 id="editDvdH1">Edit Dvd: </h1>
    <hr />
    <sf:form id="editDvdForm" action="/editDvd" method="POST" novalidate="novalidate" modelAttribute="dvd">
        <div class="form-group row">
            <label for="editDvdTitleInput" class="col-sm-2 col-lg-2">Dvd Title:</label>
            <div id="editDvdTitleDiv" class="col-sm-6 col-lg-6">
                <sf:input type="text" id="editDvdTitleInput" class="form-control" path="title"/>
                <sf:errors path="title" cssClass="error"></sf:errors>
            </div>
        </div>
        <div class="form-group row">
            <label for="editDvdReleaseYearInput" class="col-sm-2 col-lg-2">Release Year:</label>
            <div id="editDvdReleaseYearDiv" class="col-sm-6 col-lg-6">
                <sf:input id="editDvdReleaseYearInput" class="form-control" type="text" path="releaseDate"/>
                <sf:errors path="releaseDate" cssClass="error"></sf:errors>
            </div>
        </div>
        <div class="form-group row">
            <label for="editDvdDirectorInput" class="col-sm-2 col-lg-2">Director:</label>
            <div id="editDvdDirectorDiv" class="col-sm-6 col-lg-6">
                <sf:input id="editDvdDirectorInput" class="form-control" type="text" path="director"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="editDvdRatingInput" class="col-sm-2 col-lg-2">Rating:</label>
            <div id="editDvdRatingDiv" class="col-sm-2 col-lg-2">
                <sf:select id="editDvdRatingInput" class="form-control" path="rating">
                    <sf:option value="G">G</sf:option>
                    <sf:option value="PG">PG</sf:option>
                    <sf:option value="PG-13">PG-13</sf:option>
                    <sf:option value="R">R</sf:option>
                </sf:select>
                <sf:hidden path="dvdId"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="editDvdNotesInput" class="col-sm-2 col-lg-2">Notes:</label>
            <div id="editDvdNotesDiv" class="col-sm-6 col-lg-6">
                <sf:textarea rows="3" id="editDvdNotesInput" class="form-control" path="notes"></sf:textarea>
            </div>
        </div>

        <div class="form-group row">
            <a class="btn btn-outline-primary col-sm-1 col-lg-1 mr-3" href="${pageContext.request.contextPath}/">Cancel</a>
            <button id="createSaveBtn" type="submit" class="btn btn-outline-primary col-sm-2 col-lg-2">Save Changes</button>
        </div>


    </sf:form>
</div>

<!-- Placed at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>
