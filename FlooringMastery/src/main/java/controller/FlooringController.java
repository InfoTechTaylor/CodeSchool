package controller;

import service.FlooringServiceLayer;
import ui.FlooringView;

public class FlooringController {

    private FlooringView view;
    private FlooringServiceLayer service;

    public FlooringController(FlooringView view, FlooringServiceLayer service){
        this.view = view;
        this.service = service;
    }

    public void run(){
        boolean isRunning = true;
        int userSelection;

        while(isRunning){
            userSelection = printMenuAndRetrieveSelection();

            switch(userSelection){
                case 1:
                    displayOrdersByDate();
                    break;
                case 2:
                    addNewOrder();
                    break;
                case 3:
                    editOrder();
                    break;
                case 4:
                    removeOrder();
                    break;
                case 5:
                    saveAllOrders();
                    break;
                case 6:
                    exit();
                    isRunning = false;
                    break;
                default:
                    unknownCommand();
                    break;
            }

        }


    }

    private int printMenuAndRetrieveSelection(){

        return view.displayMenuAndPromptForSelection();
    }

    private void displayOrdersByDate(){

    }

    private void addNewOrder(){

    }

    private void removeOrder(){

    }

    private void editOrder(){

    }

    private void saveAllOrders(){

    }

    private void exit(){
        view.displayGoodBye();
    }

    private void unknownCommand(){
        view.displayUnknownCommand();
    }

    private void trainingMode(){

    }
}
