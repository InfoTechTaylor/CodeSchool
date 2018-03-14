<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Flooring Calculator</title>
</head>
<body>
    <h2>Flooring Calculator!</h2>

    <p>This program calculates the cost of flooring.</p>
    <p>Enter the three items below, width of floor area, length of floor area
    and the cost per square foot of the material and hit the "Calculate Flooring Cost!" button. </p>
    <ul>
        <li>The team can install flooring material at a rate of 20 square feet per hour.</li>
        <li>Labor cost for the team is $86.00/hour.</li>
        <li>The company bills in 15 minute increments.</li>
    </ul>

    <form method="post" action="FlooringCalculatorServlet">
        <label>Please enter floor area width: </label>
        <input type="text" id="floorWidth" name="floorWidth"/><br />

        <label>Please enter the floor area length: </label>
        <input type="text" id="floorLength" name="floorLength" /><br />

        <label>Please enter material cost per square foot: </label>
        <input type="text" id="costPerSquareFoot" name="costPerSquareFoot" /><br />

        <input type="submit" value="Calculate Flooring Cost!" />
    </form>

</body>
</html>
