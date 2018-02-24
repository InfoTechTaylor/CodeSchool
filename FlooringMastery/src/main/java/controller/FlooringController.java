package controller;

import dao.FlooringPersistenceException;
import dto.Order;
import dto.Product;
import dto.Tax;
import service.*;
import ui.FlooringView;

import java.time.LocalDate;
import java.util.Date;
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
        try {
            // ask user for date of order
            LocalDate orderDate = view.promptForDate();
            // display all orders for that date
            List<Order> orderList = service.retrieveAllOrdersByDate(orderDate);
            view.displayOrdersByDate(orderList);
            // ask user for order number
            int orderNumber = view.promptForOrderId();
            Order orderToRemove = service.retrieveOrderByDateAndId(orderDate, orderNumber);
            view.displayOrderSummary(orderToRemove);
            // prompt user if they are sure they want to remove
            if(view.promptToConfirmRemoval()){
                // remove
                service.removeOrder(orderDate, orderNumber);
                // confirm removal
                view.displayConfirmRemoval();
            } else {
                // confirm abort removal
                view.displayConfirmAbortRemoval();
            }
        } catch(FlooringPersistenceException | OrderNotFoundException | DateNotFoundException e){
            view.displayError(e.getMessage());
        }

    }

    private void editOrder(){
        try {
            // ask for date of order
            LocalDate orderDate = view.promptForDate();
            // display orders for that date
            List<Order> allOrders = service.retrieveAllOrdersByDate(orderDate);
            view.displayOrdersByDate(allOrders);
            // prompt for order ID
            int orderNumber = view.promptForOrderId();
            Order orderToEdit = service.retrieveOrderByDateAndId(orderDate, orderNumber);
            // display edit order prompt
            orderToEdit = view.promptForOrderUpdates(orderToEdit);
            // display changes back to the user
            view.displayOrderSummary(orderToEdit);
            // prompt if user wants to commit changes
            if(view.promptToCommitToMemory()){
                // commit changes & display success banner if yes to commit
                service.editOrder(orderToEdit);
                view.displaySuccessfulUpdateBanner();
            } else {
                // display confirm revert changes if no to commit
                view.displayConfirmRevertChanges();
            }


        } catch (FlooringPersistenceException | OrderNotFoundException | DateNotFoundException |
                TaxStateNotFoundException | ProductMaterialNotFoundException e){
            view.displayError(e.getMessage());
        }


    }

    private void saveAllOrders(){
        try {
            if(view.promptForSaveAllOrders()) {
                service.saveAllOrders();
            } else {
                view.displayConfirmRevertChanges();
            }
        } catch(FlooringPersistenceException e){
            view.displayError(e.getMessage());
        }
    }

    private void exit(){
        saveAllOrders();
        view.displayGoodBye();
    }

    private void unknownCommand(){
        view.displayUnknownCommand();
    }

    private void trainingMode(){

    }
}
