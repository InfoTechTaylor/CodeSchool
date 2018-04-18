<%--
  Created by IntelliJ IDEA.
  User: n0250996
  Date: 3/15/2018
  Time: 1:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Company Contacts</title>
    <!-- Bootstrap core CSS-->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1>Contact Details</h1>
    <hr />
    <div class="navbar">
        <ul class="nav nav-tabs">
            <li role="presentation">
                <a href="${pageContext.request.contextPath}/index.jsp">Home</a>
            </li>
            <li role="presentation">
                <a href="${pageContext.request.contextPath}/displayContactsPage">
                    Contacts
                </a>
            </li>
            <li role="presentation">
                <a href="${pageContext.request.contextPath}/displaySearchPage">
                    Search
                </a>
            </li>
        </ul>
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <p>Hello : ${pageContext.request.userPrincipal.name}
                | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
            </p>
        </c:if>
        <div>
            <p>
                Name: <c:out value="${contact.firstName}"/> <c:out value="${contact.lastName}"/>
            </p>
            <p>
                Company: <c:out value="${contact.company}"/>
            </p>
            <p>
                Phone: <c:out value="${contact.phone}"/>
            </p>
            <p>
                Email: <c:out value="${contact.email}"/>
            </p>
        </div>
    </div>
</div>
<!-- Placed at the end of the document so the pages load faster -->
<%--<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>--%>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>
