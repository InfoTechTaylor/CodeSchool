<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Dvd Library</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <div id="navDiv" class="row mt-5">

                <div class="col-md-5">
                    <a href="/displayCreateDvdForm" class="btn btn-outline-primary">Create Dvd</a>
                </div>

                <form action="search" method="GET">
                    <div class="row">
                        <div class="col-md-2">
                            <button type="submit" class="btn btn-outline-primary">Search</button>
                        </div>

                        <div class="col-md-5">
                            <select id="searchCategory" name="searchCategory" class="form-control">
                                <option value="searchCategory">Search Category</option>
                                <option value="DVDTITLE">Title</option>
                                <option value="RELEASEDATE">Release Date</option>
                                <option value="DIRECTOR">Director Name</option>
                                <option value="MPAARATING">Rating</option>
                            </select>
                        </div>

                        <input id="searchBox" name="searchBox" class="form-control col-md-5" type="text" placeholder="Search Term" />
                    </div>
                </form>
            </div>
                <div class="col-sm-12 col-lg-12">
                    <br />
                    <hr />
                </div>

                <div id="dvdTableDiv" class="container">
                    <table id="dvdTable" class="table table-bordered text-center">
                        <thead id="dvdTableHeader" class="table-dark">
                        <tr>
                            <th>Title</th>
                            <th>Release Date</th>
                            <th>Director</th>
                            <th>Rating</th>
                            <th></th>
                        </tr>

                        </thead>
                        <tbody id="dvdTableBody">
                            <c:forEach var="currentDvd" items="${allDvds}">
                                <tr>
                                    <td>
                                        <a href="viewDvd?dvdId=${currentDvd.dvdId}">
                                    <c:out value="${currentDvd.title}" /></a>
                                    </td>
                                    <td>
                                        <c:out value="${currentDvd.releaseDate}" />
                                    </td>
                                    <td>
                                        <c:out value="${currentDvd.director}" />
                                    </td>
                                    <td>
                                        <c:out value="${currentDvd.rating}" />
                                    </td>
                                    <td>
                                        <a href="displayEditDvdForm?dvdId=${currentDvd.dvdId}">Edit</a> |
                                        <a href="deleteDvd?dvdId=${currentDvd.dvdId}"
                                           onclick="return confirm('Are you sure you want to delete this DVD?')">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </div>

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

