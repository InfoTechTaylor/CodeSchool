<%--
  Created by IntelliJ IDEA.
  User: n0250996
  Date: 3/15/2018
  Time: 4:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <form id="createDvdForm" action="/addDvd" method="POST" novalidate="novalidate">
        <div class="form-group row">
            <label for="createDvdTitleInput" class="col-sm-2 col-lg-2" >Dvd Title:</label>
            <div id="createDvdTitleDiv" class="col-sm-6 col-lg-6">
                <input id="createDvdTitleInput" name="title" class="form-control" type="text" placeholder="Enter Title"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="createDvdReleaseYearInput" class="col-sm-2 col-lg-2">Release Year:</label>
            <div id="createDvdReleaseYearDiv" class="col-sm-6 col-lg-6">
                <input id="createDvdReleaseYearInput" name="releaseYear" class="form-control" type="text" placeholder="Enter Release Year"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="createDvdDirectorInput" class="col-sm-2 col-lg-2">Director:</label>
            <div id="createDvdDirectorDiv" class="col-sm-6 col-lg-6">
                <input id="createDvdDirectorInput" name="director" class="form-control" type="text" placeholder="Enter Director"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="createDvdRatingInput" class="col-sm-2 col-lg-2">Rating:</label>
            <div id="createDvdRatingDiv" class="col-sm-2 col-lg-2">
                <select id="createDvdRatingInput" name="rating" class="form-control">
                    <option selected value="chooseRating">Choose Rating</option>
                    <option value="G">G</option>
                    <option value="PG">PG</option>
                    <option value="PG-13">PG-13</option>
                    <option value="R">R</option>
                </select>
            </div>
        </div>
        <div class="form-group row">
            <label for="createDvdNotesInput" class="col-sm-2 col-lg-2">Notes:</label>
            <div id="createDvdNotesDiv" class="col-sm-6 col-lg-6">
                <textarea rows="3" id="createDvdNotesInput" name="notes" class="form-control" placeholder="Enter Note"></textarea>
            </div>
        </div>

        <div class="form-group row">
            <a class="btn btn-outline-primary col-sm-1 col-lg-1 mr-3" href="${pageContext.request.contextPath}/">Cancel</a>
            <button id="createSaveBtn" type="submit" class="btn btn-outline-primary col-sm-2 col-lg-2">Save Changes</button>
        </div>


    </form>
</div>

<!-- Placed at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>
