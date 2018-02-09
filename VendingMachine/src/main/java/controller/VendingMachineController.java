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
        // declare variables
        boolean isRunning = true;
        int userSelection = 0;

        // display welcome banner
        view.displayWelcomeBanner();

        // start while loop for a running vending machine
        while(isRunning){

            // load inventory from file and store in an ArrayList<VendingMachineItem>

            // display inventory to user, pass ArrayList<VendingMachineItem>

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

    //private void listAllAvailableItems() method

    //private void retrieveMenuSelection() method

    //private void addMoney() method

    // private void purchaseItem() method

    // private void retrieveChange() method

} // end class
