<%--
  Created by IntelliJ IDEA.
  User: n0250996
  Date: 3/15/2018
  Time: 4:54 PM
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
<div id="createDvdDiv" class="container mt-5">
    <h1 id="createDvdH1">Create Dvd </h1>
    <hr />
    <sf:form id="createDvdForm" action="/addDvd" method="POST" modelAttribute="dvd" novalidate="novalidate" >
        <div class="form-group row">
            <label for="createDvdTitleInput" class="col-sm-2 col-lg-2" >Dvd Title:</label>
            <div id="createDvdTitleDiv" class="col-sm-6 col-lg-6">
                <sf:input id="createDvdTitleInput" name="title" path="title" class="form-control" type="text" placeholder="Enter Title"/>
                <sf:errors path="title" cssClass="error"></sf:errors>
            </div>
        </div>
        <div class="form-group row">
            <label for="createDvdReleaseYearInput" class="col-sm-2 col-lg-2">Release Date:</label>
            <div id="createDvdReleaseYearDiv" class="col-sm-6 col-lg-6">
                <sf:input id="createDvdReleaseYearInput" path="releaseDate" name="releaseDate" class="form-control" type="text" placeholder="Enter Release Date"/>
                <sf:errors path="releaseDate" cssClass="error"></sf:errors>
            </div>
        </div>
        <div class="form-group row">
            <label for="createDvdDirectorInput" class="col-sm-2 col-lg-2">Director:</label>
            <div id="createDvdDirectorDiv" class="col-sm-6 col-lg-6">
                <sf:input id="createDvdDirectorInput" name="director" path="director" class="form-control" type="text" placeholder="Enter Director"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="createDvdRatingInput" class="col-sm-2 col-lg-2">Rating:</label>
            <div id="createDvdRatingDiv" class="col-sm-2 col-lg-2">
                <form:select id="createDvdRatingInput" name="rating" path="rating" class="form-control">
                    <form:option selected="selected" value="chooseRating">Choose Rating</form:option>
                    <form:option value="G">G</form:option>
                    <form:option value="PG">PG</form:option>
                    <form:option value="PG-13">PG-13</form:option>
                    <form:option value="R">R</form:option>
                </form:select>
            </div>
        </div>
        <div class="form-group row">
            <label for="createDvdNotesInput" class="col-sm-2 col-lg-2">Notes:</label>
            <div id="createDvdNotesDiv" class="col-sm-6 col-lg-6">
                <sf:textarea rows="3" id="createDvdNotesInput" path="notes" name="notes" class="form-control" placeholder="Enter Note"></sf:textarea>
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
