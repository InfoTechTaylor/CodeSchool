<%--
  Created by IntelliJ IDEA.
  User: n0250996
  Date: 4/13/2018
  Time: 11:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Player</title>
</head>
<body>
    <sf:form action="/player/create" method="post" modelAttribute="commandModel">

        First Name: <sf:input path="first" /><sf:errors path="first" /><br/>
        Last Name: <sf:input path="last" /><sf:errors path="last" /><br />
        Hometown: <sf:input path="hometown" /><sf:errors path="hometown" /><br />
        Team:
        <sf:select path="teamId" >
            <sf:option value="" label="No team" />
            <sf:options items="${viewModel.teams}" itemValue="id" itemLabel="name" />
        </sf:select><br />
        <sf:errors path="teamId" />

        Position(s):
        <sf:select path="positionIds" >
            <sf:options items="${viewModel.positions}" itemValue="id" itemLabel="name" />
        </sf:select><br/>

        <sf:errors path="positionIds" />

        <a href="/player/list">Cancel</a><button type="submit">Submit</button>

    </sf:form>

</body>
</html>
