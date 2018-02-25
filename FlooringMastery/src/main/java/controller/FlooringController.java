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
    private boolean isModeTraining = false;


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
                    changeProgramMode();
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
        return view.displayMenuAndPromptForSelection(isModeTraining);
    }

    private void displayOrdersByDate(){
        view.displaySubMenuBanner("display all orders");
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
        boolean isInvalidInput = true;
        Order newOrder = new Order();
        int loopCount = 0;

        while(isInvalidInput) {
            try {
                view.displaySubMenuBanner("add new order");
                // get lists of products and available states
                List<Tax> allTaxObjList = service.retrieveAllTaxes();
                List<Product> allProductObjList = service.retrieveAllProducts();
                view.displayAllTaxes(allTaxObjList);
                view.displayAllProducts(allProductObjList);
                // get order details from user
                if(loopCount == 0) {
                    LocalDate orderDate = view.promptForDate();
                    newOrder = view.promptForNewOrderDetails();
                    newOrder.setOrderDate(orderDate);
                } else {
                    newOrder = view.promptForOrderUpdates(newOrder);
                }

                // display order summary back to user
                view.displayOrderSummary(newOrder);
                // confirm commit new order to memory, if true add order
                if (view.promptToCommitToMemory()) {
                    // add order
                    service.addOrder(newOrder);
                    view.displaySuccessfulAdd();
                    isInvalidInput = false;
                } else {
                    view.displayConfirmRevertChanges();
                }
                view.promptUserToHitEnter();

            } catch (FlooringPersistenceException | TaxStateNotFoundException | ProductMaterialNotFoundException e) {
                view.displayError(e.getMessage());
                isInvalidInput = true;
                loopCount++;
                view.promptUserToHitEnter();

            }
        }

    }

    private void removeOrder(){
        view.displaySubMenuBanner("remove order");
        try {
            LocalDate orderDate = view.promptForDate();
            List<Order> orderList = service.retrieveAllOrdersByDate(orderDate);
            view.displayOrdersByDate(orderList);

            // ask user for order number
            int orderNumber = view.promptForOrderId();
            Order orderToRemove = service.retrieveOrderByDateAndId(orderDate, orderNumber);
            view.displayOrderSummary(orderToRemove);

            // prompt user if they are sure they want to remove
            if(view.promptToConfirmRemoval()){
                service.removeOrder(orderDate, orderNumber);
                view.displayConfirmRemoval();
            } else {
                view.displayConfirmAbortRemoval();
            }
        } catch(FlooringPersistenceException | OrderNotFoundException | DateNotFoundException e){
            view.displayError(e.getMessage());
        }

    }

    private void editOrder(){
        try {
            view.displaySubMenuBanner("edit order");

            // get orders that can be edited
            LocalDate orderDate = view.promptForDate();
            List<Order> allOrders = service.retrieveAllOrdersByDate(orderDate);
            view.displayOrdersByDate(allOrders);

            // prompt for order ID & make edits
            int orderNumber = view.promptForOrderId();
            Order orderToEdit = service.retrieveOrderByDateAndId(orderDate, orderNumber);
            orderToEdit = view.promptForOrderUpdates(orderToEdit);

            // display order and confirm if changes should be saved
            view.displayOrderSummary(orderToEdit);
            if(view.promptToCommitToMemory()){
                service.editOrder(orderToEdit);
                view.displaySuccessfulUpdateBanner();
            } else {
                view.displayConfirmRevertChanges();
            }


        } catch (FlooringPersistenceException | OrderNotFoundException | DateNotFoundException |
                TaxStateNotFoundException | ProductMaterialNotFoundException e){
            view.displayError(e.getMessage());
        }


    }

    private void saveAllOrders(){
        view.displaySubMenuBanner("save all orders");
        try {
            if(view.promptForSaveAllOrders()) {
                service.saveAllOrders();
                view.displaySuccesfullSave();
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

    private void changeProgramMode(){
        view.displaySubMenuBanner("change program mode");
        boolean oppositeMode;
        if(view.promptForProgramMode(isModeTraining)){
            // if user prompts yes, change mode to opposite of current mode
            oppositeMode = !isModeTraining;
            isModeTraining = service.activateTrainingMode(oppositeMode);
        } else {
            view.displayDidNotChangeMode();
        }
    }
}
