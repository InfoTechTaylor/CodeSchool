package com.sg.factorizor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "FactorizorServlet")
public class FactorizorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // A list to hold our factors
        List<Integer> factorList = new ArrayList<>();
        // A sum to help us calculate whether or not the number is perfect
        int factorSum = 0;
        boolean isPrime = false;
        boolean isPerfect = false;

        // Get the input from the user and convert it to an int
        String input = request.getParameter("numberToFactor");
        int numberToFactor = Integer.parseInt(input);

        // Factor the number = there are more efficient ways of doing this!
        for (int i = 1; i < numberToFactor; i++){
            if (numberToFactor % i == 0) {
                // i goes into numberToFactor evenly so it is a factor
                // add it to the list and add it to the sum
                factorList.add(i);
                factorSum +=i;
            }
        }

        if (factorSum == numberToFactor){
            isPerfect = true;
        }

        if (factorSum ==1) {
            isPrime = true;
        }

        // Set numberToFactor as an attribute on the request so that
        // it is available to result.jsp
        request.setAttribute("numberToFactor", numberToFactor);
        request.setAttribute("factors", factorList);
        request.setAttribute("isPrime", isPrime);
        request.setAttribute("isPerfect", isPerfect);

        // Get the Request Dispatcher for result.jsp and forward the
        // request to result.jsp
        RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
        rd.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }
}
