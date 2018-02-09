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
        //boolean isRunning = true;

        // display welcome banner

        // start while loop for a running vending machine
        //while(isRunning){

            // load inventory from file and store in an ArrayList<VendingMachineItem>

            // display inventory to user, pass ArrayList<VendingMachineItem>

            // display menu to user and get selection (int between 1-4)

            // if selection is to exit (4), set isRunning to false

            // else, enter switch statement on user's selection

                //case 1 add money

                //case 2 purchase item

                //case 3 get change

                // default throw error to user, allow program to loop back to menu options

       // } // end while(isRunning)
    } // end run()

    //private void listAllAvailableItems() method

    //private void retrieveMenuSelection() method

    //private void addMoney() method

    // private void purchaseItem() method

    // private void retrieveChange() method

} // end class
