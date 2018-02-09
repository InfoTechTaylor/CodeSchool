package controller;

import dao.VendingMachinePersistenceException;
import dto.VendingMachineItem;
import service.VendingMachineServiceLayer;
import ui.VendingMachineView;

import java.math.BigDecimal;
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
            // display current balance
            displayCurrentBalance();
            // display menu to user and get selection (int between 1-4)
            userSelection = retrieveMenuSelection();

            // enter switch statement
            switch(userSelection) {
                case 1:
                    addMoney();
                    break;
                case 2:
                    purchaseItem();
                    break;
                case 3:
                    retrieveChange();
                    break;
                case 4:
                    isRunning = false;
                    exit();
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

    private int retrieveMenuSelection(){
        return view.displayMenuAndPromptSelection();

    }

    private void addMoney(){
        // ask user for how much money to add to machine
        BigDecimal moneyAmount = view.promptForMoneyToAdd();
        // update remainingMoney in the service layer
        BigDecimal remaingMoney = service.addMoneyToMemory(moneyAmount);
        // display current amount back to the user
        view.displayCurrentBalance(remaingMoney);

    }

    private void purchaseItem(){

    }

    private void retrieveChange() {

    }

    private void displayCurrentBalance(){
        BigDecimal currentBalance = service.retrieveRemainingMoney();
        view.displayCurrentBalance(currentBalance);
    }

    private void exit(){
        view.displaySuccessExitBanner();
    }

} // end class
