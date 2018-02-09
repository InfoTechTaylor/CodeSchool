package controller;

import service.VendingMachineServiceLayer;
import ui.VendingMachineView;

public class VendingMachineController {

    private VendingMachineView view;
    private VendingMachineServiceLayer service;

    public VendingMachineController(VendingMachineView view, VendingMachineServiceLayer service){
        this.view = view;
        this.service = service;
    }

    public void run(){

    }
}
