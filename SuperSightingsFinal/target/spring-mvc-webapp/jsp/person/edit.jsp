<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: n0250996
  Date: 4/16/2018
  Time: 8:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Person</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
</head>
<body>

<div class="col-md-6 container mt-5">
    <h2>Edit Person</h2>
    <sf:form class="form-horizontal" role="form" method="POST" action="/person/edit" modelAttribute="commandModel" novalidate="novalidate">
        <div class="form-group row">
            <label for="add-name" class="col-sm-4 col-lg-4 col-md-4">Organization Name: </label>
            <div class="col-sm-6 col-lg-6 col-md-6">
                <sf:input path="name" id="add-name" type="text" class="form-control" name="name" placeholder="Name" />
            </div><!--end form group col -->
        </div><!--end form group-->

        <div class="form-group row">
            <label for="add-description" class="col-sm-4 col-lg-4 col-md-4">Description: </label>
            <div class="col-sm-6 col-lg-6 col-md-6">
                <sf:textarea path="description" rows="3" id="add-description" type="text" class="form-control" name="name" placeholder="Description" ></sf:textarea>
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

            </div><!--end form group col -->
        </div><!--end form group-->

        <div class="form-group row">
            <label for="add-org" class="col-sm-4 col-lg-4 col-md-4">Organization(s): </label>
            <div class="col-sm-6 col-lg-6 col-md-6">
                <sf:select path="orgIds" id="add-org" class="form-control">
                    <sf:option value="" label="Select 1 or more Orgs" />
                    <sf:options items="${orgsList}" itemValue="id" itemLabel="name" />
                </sf:select>

            </div><!--end form group col -->
        </div><!--end form group-->

        <div class="form-group row">
            <label for="add-powers" class="col-sm-4 col-lg-4 col-md-4">Power(s): </label>
            <div class="col-sm-6 col-lg-6 col-md-6">
                <sf:select path="powerIds" id="add-powers" class="form-control">
                    <sf:option value="" label="Select 1 or more powers" />
                    <sf:options items="${powersList}" itemValue="id" itemLabel="name" />
                </sf:select>

            </div><!--end form group col -->
        </div><!--end form group-->
        <div class="form-group">
            <div class="col-md-offset-4 col-md-8">
                <a href="/person/createList" class="btn">Cancel</a>
                <input type="submit" class="btn btn-default" value="Submit" />
            </div><!--end form group col -->
        </div><!--end form group-->
        <sf:hidden path="id"/>
    </sf:form>
</div><!--end right col div-->

</body>
</html>
