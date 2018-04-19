<%--
  Created by IntelliJ IDEA.
  User: n0250996
  Date: 4/19/2018
  Time: 10:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Login</title>
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
                    <li role="presentation" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/sighting/createList">Sightings</a></li>
                    <li role="presentation" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/organization/createList">Organizations</a></li>
                    <li role="presentation" class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/power/createList">Powers</a></li>
                </ul>
            </div>

        </div>
    </div>

    <div class="mt-5">

      <h2>Login Page</h2>
        <c:if test="${param.login_error == 1}">
            <h3>Wrong id or password!</h3>
        </c:if>

        <form class="form-horizontal"
              role="form"
              method="post"
              action="j_spring_security_check">
            <div class="form-group">
                <label for="j_username"
                       class="col-md-4 control-label">Username:</label>
                <div class="col-md-8">
                    <input type="text"
                           class="form-control"
                           name="j_username"
                           id="j_username"
                           placeholder="Username"/>
                </div>
            </div>
            <div class="form-group">
                <label for="j_password"
                       class="col-md-4 control-label">Password:</label>
                <div class="col-md-8">
                    <input type="password"
                           class="form-control"
                           name="j_password"
                           id="j_password"
                           placeholder="Password"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-offset-4 col-md-8">
                    <input type="submit"
                           class="btn btn-default"
                           id="search-button"
                           value="Sign In"/>
                </div>
            </div>
        </form>
    </div>
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</div>
</body>
</html>
