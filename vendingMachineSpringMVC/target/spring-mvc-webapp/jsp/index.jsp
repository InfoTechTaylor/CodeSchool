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
        <style>
            .square{
                width: 100%;
            }
            .square:after {
                content: "";
                display: block;
                padding-bottom:30%;
            }
        </style>
    </head>
    <div class="container"> <!-- overall container for entire page -->

        <div id="headingDiv" class="page-header text-center">
            <h2>Vending Machine</h2>
        </div> <!--END #headingDiv -->

        <!--Left Div of vending machine items ----------------------------------------------->
        <div id="vendingMachineItemDiv" class="col-lg-9 col-sm-9">

            <c:forEach var="currentItem" items="${itemInventory}">
                <div class="col-lg-4 col-sm-4"><div style="margin-right: 1%; padding: 2%;" class="panel panel-primary square">
                    <p class="text-left"> <c:out value="${currentItem.itemId}" /> </p>
                    <p class="text-center"> <c:out value="${currentItem.itemName}" /></p>
                    <p class="text-center">$<c:out value="${currentItem.itemCost}" /></p>
                    <p class="text-center">Quantity Left: <c:out value="${currentItem.itemQuantity}" /></p></div></div>
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
                        <input id="currentMoneyAmt" type="text" class="form-control" style="margin-bottom: 2%" disabled/>
                        <div class="col-sm-6 col-lg-6">
                            <button id="addDollarBtn" type="button" style="margin-right: 1%; margin-bottom: 2%; width: 100%;"
                                    class="btn btn-primary" value="1.00">Add Dollar
                            </button>
                        </div>
                        <div class="col-sm-6 col-lg-6">
                            <button id="addQuarterBtn" type="button" class="btn btn-primary"
                                    style="margin-bottom: 2%; margin-right: 1%; width: 100%;" value=".25">Add Quarter
                            </button>
                        </div>
                        <div class="col-sm-6 col-lg-6">
                            <button id="addDimeBtn" type="button" class="btn btn-primary"
                                    style="margin-bottom: 2%; margin-right: 1%; width: 100%;" value=".10">Add Dime</button>
                        </div>
                        <div class="col-sm-6 col-lg-6">
                            <button id="addNickelBtn" type="button" class="btn btn-primary"
                                    style="margin-bottom: 2%; margin-right: 1%; width: 100%;" value=".05">Add Nickel
                            </button>
                        </div>

                    </form>

                </div><!--end totalMoneyDiv-->
                <div class="col-sm-12 col-lg-12">
                    <hr/>
                </div>

                <!-- MESSAGES DIV -->
                <div id="messagesDiv" class="col-sm-12 col-lg-12">
                    <h2 class="text-center">Messages</h2>
                    <form id="messagesForm">
                        <input id="messagesTextBox" type="text" class="form-control col-sm-12 col-lg-12" style="margin-bottom: 2%"
                               disabled/>
                        <div class="form-group">
                            <label for="itemText" class="control-label col-sm-4 col-lg-4">Item: </label>
                            <div id="itemTextDiv" class="col-sm-8 col-lg-8" style="margin-bottom: 2%">
                                <input id="itemText" type="text" class="form-control" disabled />
                            </div>
                        </div>
                        <button id="makePurchaseBtn" type="button" class="btn btn-primary col-sm-12 col-lg-12">
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
                    <form id="changeForm">
                    <textarea rows="4" id="changeText" type="text" class="form-control col-sm-12 col-lg-12" style="margin-bottom: 2%"
                              disabled></textarea>
                        <button id="changeBtn" type="button" class="btn btn-primary col-sm-12 col-lg-12">Change Return
                        </button>
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

