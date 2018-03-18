package com.sg.vendingmachinespringmvc.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.sg.vendingmachinespringmvc.dao.VendingMachinePersistenceException;
import com.sg.vendingmachinespringmvc.dto.VendingMachineChange;
import com.sg.vendingmachinespringmvc.dto.VendingMachineItem;
import com.sg.vendingmachinespringmvc.service.*;
import javafx.collections.ListChangeListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@Controller
//@RequestMapping({"/vendingMachine"})
public class VendingMachineController {

    private VendingMachineServiceLayer service;

    @Inject
    public VendingMachineController(VendingMachineServiceLayer service) {
        this.service = service;
    }
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String loadMachine(HttpServletRequest request, Model model) {
        try {
            List<VendingMachineItem> itemInventory = service.retrieveAllVendingMachineItems();
            // get item choice and message from the request
            String selectedItemId = request.getParameter("selectedItemId");

            // get change and current amount from service
            BigDecimal currentMoneyAmount = service.getRemainingMoney();
            VendingMachineChange change = service.getChangeAmount();
            String message = service.getMessage();

            // determine if show/hide the get change button and change div
            if(change == null && currentMoneyAmount.equals(new BigDecimal("0"))) {
                // on startup, change is null and current amount is zero, hide button and change div
                model.addAttribute("showButton", false);
                model.addAttribute("showChangeDiv", false);
            } else if (change == null) {
                // user added money but hasn't gotten change or made purchase, show button, hid change div
                model.addAttribute("showButton", true);
                model.addAttribute("showChangeDiv", false);
            } else if (change != null && currentMoneyAmount.equals(new BigDecimal("0"))){
                // purchase was made, change amount displayed and button hides
                model.addAttribute("showButton", false);
                model.addAttribute("showChangeDiv", true);
            } else {
                model.addAttribute("showButton", true);
                model.addAttribute("showChangeDiv", true);

            }
            // if change is not null, add change amounts to the model
            if(change != null){
                model.addAttribute("numQuarters", change.getQuarters());
                model.addAttribute("numDimes", change.getDimes());
                model.addAttribute("numNickels", change.getNickels());
                model.addAttribute("numPennies", change.getPennies());
            }

            // add remaining model attributes
            model.addAttribute("itemInventory", itemInventory);
            model.addAttribute("selectedItemId", selectedItemId);
            model.addAttribute("currentMoneyAmount", currentMoneyAmount);
            model.addAttribute("message", message);

        } catch (VendingMachinePersistenceException e){
            e.printStackTrace();
        }
        return "index";
    }

    @RequestMapping(value="/selectItem", method=RequestMethod.POST)
    public String selectItem(HttpServletRequest request, Model model){
        String selectedItemId = request.getParameter("selectedItemId");

        model.addAttribute("selectedItemId", selectedItemId);
        return "redirect:/";
    }

    @RequestMapping(value="/addMoney", method=RequestMethod.GET)
    public String addMoney(HttpServletRequest request){

        try {
            String coinAmount = request.getParameter("coinAmount");
            service.addMoneyToMemory(Money.valueOf(coinAmount.toUpperCase()));
        } catch (InvalidAmountException e){
            e.getStackTrace();
        }
        return "redirect:/";
    }

    @RequestMapping(value="/purchaseItem", method=RequestMethod.POST)
    public String purchaseItem(HttpServletRequest request, Model model){
        try {
            String itemSelectedId = request.getParameter("selectedItemId");
            String message = service.purchaseItem(itemSelectedId);
            model.addAttribute("message", message);
        } catch (VendingMachinePersistenceException | NoItemInventoryException | InsufficientFundsException e){
            e.getStackTrace();
        }

        return "redirect:/";
    }

    @RequestMapping(value="/getChange", method=RequestMethod.GET)
    public String getChange(Model model){
        try {
            VendingMachineChange change = service.convertDollarsToCoinsAndGetChange();
            model.addAttribute("change", change);
        } catch (InsufficientFundsException e){
            e.getStackTrace();
        }
        return "redirect:/";
    }

}
