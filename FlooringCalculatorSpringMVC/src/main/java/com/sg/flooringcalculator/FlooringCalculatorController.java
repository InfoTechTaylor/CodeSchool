package com.sg.flooringcalculator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

@Controller
public class FlooringCalculatorController {

    @RequestMapping(value="/calculateCosts", method= RequestMethod.POST)
    public String calculateCosts(HttpServletRequest request, Map<String, Object> model) {

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
        model.put("floorWidth", floorWidth);
        model.put("floorLength", floorLength);
        model.put("costPerSquareFoot", costPerSquareFoot);
        model.put("costOfMaterial", costOfMaterial);
        model.put("laborCost", laborCost);
        model.put("totalTimeRequired", totalTimeRequired);
        model.put("totalCost", totalCost);
        model.put("totalTimeCharged", totalTimeCharged);

        return "result";
    }
}
