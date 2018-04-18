<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: n0250996
  Date: 4/16/2018
  Time: 2:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Location</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<div class="container">
<h2>Edit Location</h2>

<sf:form class="form-horizontal" role="form" method="POST" action="/location/edit" modelAttribute="commandModel">
    <div class="form-group row">
        <label for="add-name" class="col-sm-4 col-lg-4 col-md-4">Name: </label>
        <div class="col-sm-6 col-lg-6 col-md-6">
            <sf:input path="name" id="add-name" type="text" class="form-control" name="name" placeholder="Location Name" />
        </div><!--end form group col -->
    </div><!--end form group-->
    <div class="form-group row">
        <label for="add-description" class="col-sm-4 col-lg-4 col-md-4">Description: </label>
        <div class="col-sm-6 col-lg-6 col-md-6">
            <sf:textarea path="description" rows="3" id="add-description" type="text" class="form-control" name="description" placeholder="Location description" ></sf:textarea>
        </div><!--end form group col -->
    </div><!--end form group-->
    <div class="form-group row">
        <label for="add-street" class="col-sm-4 col-lg-4 col-md-4">Street: </label>
        <div class="col-sm-6 col-lg-6 col-md-6">
            <sf:input path="street" id="add-street" type="text" class="form-control" name="street" placeholder="Street Address" ></sf:input>
        </div><!--end form group col -->
    </div><!--end form group-->
    <div class="form-group row">
        <label for="add-city" class="col-sm-4 col-lg-4 col-md-4">City: </label>
        <div class="col-sm-6 col-lg-6 col-md-6">
            <sf:input path="city" id="add-city" type="text" class="form-control" name="city" placeholder="City" ></sf:input>
        </div><!--end form group col -->
    </div><!--end form group-->
    <div class="form-group row">
        <label for="add-state" class="col-sm-4 col-lg-4 col-md-4">State: </label>
        <div class="col-sm-6 col-lg-6 col-md-6">
            <sf:input path="state" id="add-state" type="text" class="form-control" name="state" placeholder="State" ></sf:input>
        </div><!--end form group col -->
    </div><!--end form group-->
    <div class="form-group row">
        <label for="add-zip" class="col-sm-4 col-lg-4 col-md-4">Zip Code: </label>
        <div class="col-sm-6 col-lg-6 col-md-6">
            <sf:input path="zip" id="add-zip" type="text" class="form-control" name="zip" placeholder="zip" ></sf:input>
        </div><!--end form group col -->
    </div><!--end form group-->
    <div class="form-group row">
        <label for="add-country" class="col-sm-4 col-lg-4 col-md-4">Country: </label>
        <div class="col-sm-6 col-lg-6 col-md-6">
            <sf:input path="country" id="add-country" type="text" class="form-control" name="state" placeholder="country" ></sf:input>
        </div><!--end form group col -->
    </div><!--end form group-->
    <div class="form-group row">
        <label for="add-latitude" class="col-sm-4 col-lg-4 col-md-4">Latitude: </label>
        <div class="col-sm-6 col-lg-6 col-md-6">
            <sf:input path="latitude" id="add-latitude" type="text" class="form-control" name="latitude" placeholder="latitude" ></sf:input>
        </div><!--end form group col -->
    </div><!--end form group-->
    <div class="form-group row">
        <label for="add-longitude" class="col-sm-4 col-lg-4 col-md-4">Longitude: </label>
        <div class="col-sm-6 col-lg-6 col-md-6">
            <sf:input path="longitude" id="add-longitude" type="text" class="form-control" name="longitude" placeholder="longitude" ></sf:input>
        </div><!--end form group col -->
    </div><!--end form group-->
    <div class="form-group">
        <div class="col-md-offset-4 col-md-8">
            <a href="/location/createList" class="btn btn-secondary">Cancel</a>
            <input type="submit" class="btn btn-default" value="Submit" />
        </div><!--end form group col -->
    </div><!--end form group-->
</sf:form>
</div>
</body>
</html>
