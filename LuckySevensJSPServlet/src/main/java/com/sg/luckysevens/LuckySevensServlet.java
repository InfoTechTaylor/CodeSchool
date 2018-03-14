package com.sg.luckysevens;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Random;

@WebServlet(name = "LuckySevensServlet")
public class LuckySevensServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BigDecimal startingBet = new BigDecimal(request.getParameter("betAmount"));

        Result resultObj = rollDice(startingBet);

        request.setAttribute("startingBet", startingBet);
        request.setAttribute("resultObj", resultObj);

        RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
        rd.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }

    public Result rollDice(BigDecimal startingBet){
        int numberOfRolls = 0, numberOfRollsAtMaxAmt = 0;
        BigDecimal maxAmount, currentAmt;

        currentAmt = startingBet;
        maxAmount = startingBet;
        while (currentAmt.compareTo(BigDecimal.ZERO) > 0) {
            //roll dice
            int diceOne, diceTwo, diceSum;
            Random rGen = new Random();
            diceOne = rGen.nextInt(6) + 1;
            diceTwo = rGen.nextInt(6) + 1;
            diceSum = diceOne + diceTwo;
            numberOfRolls++;
            if(diceSum == 7){
                currentAmt = currentAmt.add(new BigDecimal("4"));
            }else {
                currentAmt = currentAmt.subtract(new BigDecimal("1"));
            }
            if(currentAmt.compareTo(maxAmount) == 1){
                maxAmount = currentAmt;
                numberOfRollsAtMaxAmt = numberOfRolls;
            }
        }
        Result luckySevenResult = new Result(numberOfRolls, numberOfRollsAtMaxAmt, maxAmount);
        return luckySevenResult;
    }
}
