package controller;

import dao.FlooringPersistenceException;
import dto.Order;
import dto.Product;
import dto.Tax;
import service.*;
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
        try {
            view.displayCreateNewOrderBanner();
            // get lists of products and available states
            List<Tax> allTaxObjList = service.retrieveAllTaxes();
            List<Product> allProductObjList = service.retrieveAllProducts();
            view.displayAllTaxes(allTaxObjList);
            view.displayAllProducts(allProductObjList);
            // get order details from user
            LocalDate orderDate = view.promptForDate();
            Order newOrder = view.promptForNewOrderDetails();
            newOrder.setOrderDate(orderDate);
            // display order summary back to user
            view.displayOrderSummary(newOrder);
            // confirm commit new order to memory, if true add order
            if(view.promptToCommitToMemory()) {
                // add order
                service.addOrder(newOrder);
            } else {
                view.displayConfirmRevertChanges();
            }
            view.promptUserToHitEnter();

        } catch(FlooringPersistenceException | TaxStateNotFoundException | ProductMaterialNotFoundException e){
            view.displayError(e.getMessage());
        }

    }

    private void removeOrder(){

    }

    private void editOrder(){

    }

    private void saveAllOrders(){
        try {
            service.saveAllOrders();
        } catch(FlooringPersistenceException e){
            view.displayError(e.getMessage());
        }
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
