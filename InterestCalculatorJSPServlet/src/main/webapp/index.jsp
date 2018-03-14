<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Interest Calculator</title>
</head>
<body>
<h2>Interest Calculator</h2>

<form method="post" action="InterestCalculatorServlet">
    <label>Enter interest rate: </label>
    <input type="text" id="interestRate" name="interestRate" /><br />
    <label>Enter initial amount of principle: </label>
    <input type="text" id="initialPrinciple" name="initialPrinciple" /><br />
    <label>Enter number of years: </label>
    <input type="text" id="numYears" name="numYears" /><br />
    <input type="submit" value="Calculate Interest!" />

    <p></p>
</form>
</body>
</html>
