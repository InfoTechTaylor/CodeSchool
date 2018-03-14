<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Tip Calculator</title>
</head>
<body>
<h2>Tip Calculator</h2>

<form method="post" action="TipCalculatorServlet">
    Enter bill amount: <input type="text" name="billAmount" />
    Enter percentage for tip: <input type="text" name="tipPercentage" />
    <input type="submit" value="Calculate Tip!" />
</form>
</body>
</html>
