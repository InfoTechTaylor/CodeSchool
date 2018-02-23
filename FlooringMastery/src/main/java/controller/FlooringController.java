package controller;

import dao.FlooringPersistenceException;
import dto.Order;
import service.DateNotFoundException;
import service.FlooringServiceLayer;
import service.OrderNotFoundException;
import ui.FlooringView;

import java.time.LocalDate;
import java.util.List;

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
                    trainingMode();
                    break;
                case 7:
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
        try {
            // get date from user
            LocalDate ordersDate = view.promptForDate();
            // get orders list for date given
            List<Order> allOrdersForDateList = service.retrieveAllOrdersByDate(ordersDate);
            // display orders in list
            view.displayOrdersByDate(allOrdersForDateList);
            view.promptUserToHitEnter();
        } catch (FlooringPersistenceException | OrderNotFoundException | DateNotFoundException e){
            view.displayError(e.getMessage());
        }

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
