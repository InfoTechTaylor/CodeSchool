package com.sg.interestcalculator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "InterestCalculatorServlet")
public class InterestCalculatorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        request.setAttribute("annualInterestRate", annualInterestRate);
        request.setAttribute("initialPrinciple", initialPrinciple);
        request.setAttribute("numYears", numYears);
        request.setAttribute("startingBalanceList", startingBalanceList);
        request.setAttribute("interestEarnedList", interestEarnedList);
        request.setAttribute("endPrincipleList", endPrincipleList);

        RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
        rd.forward(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }
}
