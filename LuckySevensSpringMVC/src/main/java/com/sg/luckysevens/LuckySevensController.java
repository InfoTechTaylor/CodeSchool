package com.sg.luckysevens;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Random;

@Controller
public class LuckySevensController {

    @RequestMapping(value="/rollDice", method= RequestMethod.POST)
    public String rollDice(HttpServletRequest request, Map<String, Object> model) {
        BigDecimal startingBet = new BigDecimal(request.getParameter("betAmount"));

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
        model.put("resultObj", luckySevenResult);
        model.put("startingBet", startingBet);
        return "result";
    }
}
