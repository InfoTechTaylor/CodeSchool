<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Vending Machine</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/customStyle.css" rel="stylesheet">
    </head>
    <div class="container"> <!-- overall container for entire page -->

        <div id="headingDiv" class="page-header text-center">
            <h2>Vending Machine</h2>
        </div> <!--END #headingDiv -->

        <!--Left Div of vending machine items ----------------------------------------------->
        <div id="vendingMachineItemDiv" class="col-lg-9 col-sm-9">

            <c:forEach var="currentItem" items="${itemInventory}">
                <form action="selectItem" method="POST">
                    <input type="hidden" name="selectedItemId" value="<c:out value="${currentItem.itemId}" />" />
                <div class="col-lg-4 col-sm-4"><button class="panel panel-primary square itemPadding" type="submit">
                    <p class="text-left"><c:out value="${currentItem.itemId}" /></p>
                    <p class="text-center"> <c:out value="${currentItem.itemName}" /></p>
                    <p class="text-center">$<c:out value="${currentItem.itemCost}" /></p>
                    <p class="text-center">Quantity Left: <c:out value="${currentItem.itemQuantity}" /></p></button></div>
                </form>
            </c:forEach>


            <div id="vendingMachineItemRowDiv" class="row" >

            </div> <!-- END vendingMachineItemRowDiv -->
        </div> <!--END Left Div of vending machine items -->

        <!--RIGHT DIV ------------------------------------------------------------->
        <div id="vendingMachineRightInfoDiv" class="col-lg-3 col-sm-3">
            <div id="vendingMachineRightInfoRowDiv" class="row">
                <!-- TOTAL MONEY DIV -->
                <div id="totalMoneyDiv" class="col-sm-12 col-lg-12">
                    <h2 class="text-center">Total $ In</h2>
                    <form id="totalMoneyForm">
                        <input id="currentMoneyAmt" type="text" class="form-control bottomMargin"
                               value="<c:out value="${currentMoneyAmount}" />" disabled/>
                        <div class="col-sm-6 col-lg-6">
                            <a href="addMoney?coinAmount=DOLLAR&selectedItemId=<c:out value="${selectedItemId}" />" class="btn btn-primary addMoneyButtonStyle">Add Dollar</a>
                        </div>
                        <div class="col-sm-6 col-lg-6">
                            <a href="addMoney?coinAmount=QUARTER&selectedItemId=<c:out value="${selectedItemId}" />" class="btn btn-primary addMoneyButtonStyle">Add Quarter</a>
                        </div>
                        <div class="col-sm-6 col-lg-6">
                            <a href="addMoney?coinAmount=DIME&selectedItemId=<c:out value="${selectedItemId}" />" class="btn btn-primary addMoneyButtonStyle">Add Dime</a>
                        </div>
                        <div class="col-sm-6 col-lg-6">
                            <a href="addMoney?coinAmount=NICKEL&selectedItemId=<c:out value="${selectedItemId}" />" class="btn btn-primary addMoneyButtonStyle">Add Nickel</a>
                        </div>
                    </form>

                </div><!--end totalMoneyDiv-->
                <div class="col-sm-12 col-lg-12">
                    <hr/>
                </div>

                <!-- MESSAGES DIV -->
                <div id="messagesDiv" class="col-sm-12 col-lg-12">
                    <h2 class="text-center">Messages</h2>
                    <form id="messagesForm" action="purchaseItem" method="POST">
                        <input type="hidden" name="selectedItemId" value="<c:out value="${selectedItemId}" />" />
                        <input id="messagesTextBox" type="text" class="form-control col-sm-12 col-lg-12 bottomMargin"
                               value="<c:out value="${message}" />" disabled/>
                        <div class="form-group row">
                            <label for="itemSelectedId" class="control-label col-sm-4 col-lg-4">Item: </label>
                            <div id="itemTextDiv" class="col-sm-2 col-lg-3 bottomMargin">
                                    <input id="itemSelectedId" name="itemSelectedId" type="text" class="col-sm-4 col-lg-4 form-control"
                                           value="<c:out value="${selectedItemId}" />" disabled />
                            </div>
                        </div>
                        <button id="makePurchaseBtn" type="submit" class="btn btn-primary col-sm-12 col-lg-12">
                            Make Purchase
                        </button>
                    </form>

                </div> <!-- END messages div -->
                <div class="col-sm-12 col-lg-12">
                    <hr>
                </div>

                <!-- CHANGE DIV -->
                <div id="changeDiv" class="col-sm-12 col-lg-12">
                    <h2 class="text-center">Change</h2>
                    <form id="changeForm" action="getChange" method="GET">
                    <div id="changeText" class="col-sm-12 col-lg-12 bottomMargin panel panel-default panel-heading">
                    <c:if test = "${showChangeDiv == true}">
                        <p><c:out value="Quarters: ${change.quarters}" /></p>
                        <p><c:out value="Nickels: ${change.nickels}" /></p>
                        <p><c:out value="Dimes: ${change.dimes}" /></p>
                        <p><c:out value="Pennies: ${change.pennies}" /></p>
                    </c:if>
                    </div>
                        <c:if test = "${showButton == true}">
                            <button id="changeBtn" type="submit" class="btn btn-primary col-sm-12 col-lg-12">Change Return
                            </button>
                        </c:if>
                        <input type="hidden" name="selectedItemId" value="<c:out value="${selectedItemId}" />" />
                    </form>

                </div>

            </div>
        </div><!-- END RIGHT DIV -->


    </div> <!-- END overall container for entire page -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

