package com.sg.interestcalculator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class InterestCalculatorController {

    @RequestMapping(value="/calculateInterest", method= RequestMethod.POST)
    public String calculateInterest(HttpServletRequest request, Map<String, Object> model) {

        BigDecimal annualInterestRate = new BigDecimal(request.getParameter("interestRate"));
        BigDecimal initialPrinciple = new BigDecimal(request.getParameter("initialPrinciple"));
        int numYears = Integer.parseInt(request.getParameter("numYears"));

        BigDecimal runningBalance = initialPrinciple;
        annualInterestRate = annualInterestRate.divide(new BigDecimal("4"));
        BigDecimal quarterlyRunningBalance = runningBalance;
        BigDecimal interestEarned, yearlyInterestEarned = new BigDecimal("0");
        BigDecimal oneHundred = new BigDecimal("100");

        List<BigDecimal> startingBalanceList = new ArrayList<>();
        List<BigDecimal> interestEarnedList = new ArrayList<>();
        List<BigDecimal> endPrincipleList = new ArrayList<>();

        for(int i=1; i <= numYears; i++) {
            startingBalanceList.add(runningBalance);

            for (int quarter = 0; quarter < 4; quarter++) {

                interestEarned = quarterlyRunningBalance.multiply(annualInterestRate).divide(oneHundred, 2, RoundingMode.HALF_UP);
                yearlyInterestEarned = yearlyInterestEarned.add(interestEarned);
                quarterlyRunningBalance = quarterlyRunningBalance.add(interestEarned);
            }

            runningBalance = runningBalance.add(yearlyInterestEarned);
            interestEarnedList.add(yearlyInterestEarned);
            endPrincipleList.add(runningBalance);
            yearlyInterestEarned = new BigDecimal(0);
        }

        model.put("annualInterestRate", annualInterestRate);
        model.put("initialPrinciple", initialPrinciple);
        model.put("numYears", numYears);
        model.put("startingBalanceList", startingBalanceList);
        model.put("interestEarnedList", interestEarnedList);
        model.put("endPrincipleList", endPrincipleList);

        return "result";
    }

}
