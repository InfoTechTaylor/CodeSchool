package com.sg.vendingmachinespringmvc.controller;

import java.util.List;
import java.util.Map;

import com.sg.vendingmachinespringmvc.dao.VendingMachinePersistenceException;
import com.sg.vendingmachinespringmvc.dto.VendingMachineItem;
import com.sg.vendingmachinespringmvc.service.VendingMachineServiceLayer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

@Controller
//@RequestMapping({"/vendingMachine"})
public class VendingMachineController {

    private VendingMachineServiceLayer service;

    @Inject
    public VendingMachineController(VendingMachineServiceLayer service) {
        this.service = service;
    }
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String loadMachine(Model model) {
        System.out.println("test");
        try {
            List<VendingMachineItem> itemInventory = service.retrieveAllVendingMachineItems();
            model.addAttribute("itemInventory", itemInventory);
        } catch (VendingMachinePersistenceException e){
            String message = e.getMessage();
            model.addAttribute("message", message);
            e.printStackTrace();
            return "index";
        }
        return "index";
    }
}
