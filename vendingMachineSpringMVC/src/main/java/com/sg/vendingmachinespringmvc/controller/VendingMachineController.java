package com.sg.vendingmachinespringmvc.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.sg.vendingmachinespringmvc.dao.VendingMachinePersistenceException;
import com.sg.vendingmachinespringmvc.dto.VendingMachineItem;
import com.sg.vendingmachinespringmvc.service.InsufficientFundsException;
import com.sg.vendingmachinespringmvc.service.InvalidAmountException;
import com.sg.vendingmachinespringmvc.service.NoItemInventoryException;
import com.sg.vendingmachinespringmvc.service.VendingMachineServiceLayer;
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
            String hiddenItemId = request.getParameter("hiddenItemId");
            BigDecimal currentMoneyAmount = service.getRemainingMoney();
            String message = request.getParameter("message");

            model.addAttribute("itemInventory", itemInventory);
            model.addAttribute("hiddenItemId", hiddenItemId);
            model.addAttribute("currentMoneyAmount", currentMoneyAmount);
            model.addAttribute("message", message);
        } catch (VendingMachinePersistenceException e){
            String message = e.getMessage();
            model.addAttribute("message", message);
            return "index";
        }
        return "index";
    }

    @RequestMapping(value="/selectItem", method=RequestMethod.POST)
    public String selectItem(HttpServletRequest request, Model model){
        String hiddenItemId = request.getParameter("hiddenItemId");

        model.addAttribute("hiddenItemId", hiddenItemId);
        return "redirect:/";
    }

    @RequestMapping(value="/addMoney", method=RequestMethod.GET)
    public String addMoney(HttpServletRequest request, Model model){
        try {
            String coinAmount = request.getParameter("coinAmount");
            BigDecimal currentMoneyAmount = service.addMoneyToMemory(new BigDecimal(coinAmount));
            model.addAttribute("currentMoneyAmount", currentMoneyAmount);
        } catch (InvalidAmountException e){
            String message = e.getMessage();
            model.addAttribute("message", message);
        }
        return "redirect:/";
    }

    @RequestMapping(value="/purchaseItem", method=RequestMethod.POST)
    public String purchaseItem(HttpServletRequest request, Model model){
        try {
            String itemSelectedId = request.getParameter("hiddenItemId");
            service.purchaseItem(itemSelectedId);
            String message = "Thank you!!!";
            model.addAttribute("message", message);
        } catch (VendingMachinePersistenceException | NoItemInventoryException | InsufficientFundsException e){
            String message = e.getMessage();
            model.addAttribute("message", message);
        }

        return "redirect:/";
    }
}
