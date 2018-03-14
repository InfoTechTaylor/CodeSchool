package com.sg.flooringcalculator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

@WebServlet(name = "FlooringCalculatorServlet")
public class FlooringCalculatorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get the values from the index.jsp form
        BigDecimal floorWidth = new BigDecimal(request.getParameter("floorWidth"));
        BigDecimal floorLength = new BigDecimal(request.getParameter("floorLength"));
        BigDecimal costPerSquareFoot = new BigDecimal(request.getParameter("costPerSquareFoot"));

        // calculate the cost of the flooring material for the specified area
        BigDecimal area = floorLength.multiply(floorWidth);
        BigDecimal costOfMaterial = (floorLength.multiply(floorWidth)).multiply(costPerSquareFoot);

        // calculate and display the labor cost for installation (86/hour)
        BigDecimal totalTimeRequired = area.divide(new BigDecimal("20"));

        BigDecimal totalTimeRequiredInMins = totalTimeRequired.multiply(new BigDecimal("60"));
        BigDecimal numQuarters = totalTimeRequiredInMins.divide(new BigDecimal("15")).setScale(0, RoundingMode.UP);
        BigDecimal totalTimeCharged = numQuarters.divide(new BigDecimal("4"));

        BigDecimal laborCost = totalTimeCharged.multiply(new BigDecimal("86"));

        // calculates and dispalys the total cost (labor + materials)
        BigDecimal totalCost = costOfMaterial.add(laborCost);

        // set values as an attribute on the request so that it is available to result.jsp
        request.setAttribute("floorWidth", floorWidth);
        request.setAttribute("floorLength", floorLength);
        request.setAttribute("costPerSquareFoot", costPerSquareFoot);
        request.setAttribute("costOfMaterial", costOfMaterial);
        request.setAttribute("laborCost", laborCost);
        request.setAttribute("totalTimeRequired", totalTimeRequired);
        request.setAttribute("totalCost", totalCost);
        request.setAttribute("totalTimeCharged", totalTimeCharged);

        // Get the result dispatcher for result.jsp and forward the result to result.jsp
        RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
        rd.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }
}
