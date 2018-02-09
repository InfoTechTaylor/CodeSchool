package controller;

import dao.VendingMachinePersistenceException;
import dto.VendingMachineItem;
import service.VendingMachineServiceLayer;
import ui.VendingMachineView;

import java.util.ArrayList;
import java.util.List;

public class VendingMachineController {

    private VendingMachineView view;
    private VendingMachineServiceLayer service;

    public VendingMachineController(VendingMachineView view, VendingMachineServiceLayer service){
        this.view = view;
        this.service = service;
    }

    public void run(){
        // declare variables
        boolean isRunning = true;
        int userSelection;

        // display welcome banner
        view.displayWelcomeBanner();

        // start while loop for a running vending machine
        while(isRunning){
            // display inventory to user, pass ArrayList<VendingMachineItem>
            listAllAvailableItems();
            // display menu to user and get selection (int between 1-4)
            userSelection = view.displayMenuAndPromptSelection();

            // enter switch statement
            switch(userSelection) {
                case 1:
                    //add money
                    break;
                case 2:
                    //purchase item
                    break;
                case 3:
                    // Get change
                    break;
                case 4:
                    isRunning = false;
                    break;
                default:
                    // default throw error to user, allow program to loop back to menu options
                    break;
            } // end switch
       } // end while(isRunning)
    } // end run()

    private void listAllAvailableItems(){
        try {
            // call the service for a list of available items
            List<VendingMachineItem> allItemsList = service.retrieveAllVendingMachineItems();
            //print all items calling the view
            view.displayVendingMachineInventory(allItemsList);
        } catch (VendingMachinePersistenceException e){
            // call the view to display error
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void retrieveMenuSelection(){

    }

    private void addMoney(){

    }

    private void purchaseItem(){

    }

    private void retrieveChange() {

    }

    private void exit(){

    }

} // end class
