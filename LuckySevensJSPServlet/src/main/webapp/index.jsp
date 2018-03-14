<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Lucky Sevens</title>
</head>
<body>
<h2>Lucky Sevens</h2>
<p>Each round, the program rolls a virtual pair of dice for the user</p>
<p>If the sum of the 2 dice is equal to 7, the player wins $4; otherwise, the player loses $1</p>

<form action="LuckySevensServlet" method="post">
Bet Amount: <input type="text" name="betAmount" /><br/>
    <input type="submit" value="Roll Dice!!!" />
</form>

</body>
</html>
