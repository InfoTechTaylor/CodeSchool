<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Flooring Calculator</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>Flooring Calculator</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                	<li role="presentation" class="active"><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                	<li role="presentation"><a href="${pageContext.request.contextPath}/hello/sayhi">Hello Controller</a></li>
                </ul>    
            </div>
            <h2>Flooring Form</h2>

            <p>This program calculates the cost of flooring.</p>
            <p>Enter the three items below, width of floor area, length of floor area
                and the cost per square foot of the material and hit the "Calculate Flooring Cost!" button. </p>
            <ul>
                <li>The team can install flooring material at a rate of 20 square feet per hour.</li>
                <li>Labor cost for the team is $86.00/hour.</li>
                <li>The company bills in 15 minute increments.</li>
            </ul>
            <form method="post" action="/calculateCosts">
                <label>Please enter floor area width: </label>
                <input type="text" id="floorWidth" name="floorWidth"/><br />

                <label>Please enter the floor area length: </label>
                <input type="text" id="floorLength" name="floorLength" /><br />

                <label>Please enter material cost per square foot: </label>
                <input type="text" id="costPerSquareFoot" name="costPerSquareFoot" /><br />

                <input type="submit" class="btn btn-default" value="Calculate Flooring Cost!" />
            </form>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

