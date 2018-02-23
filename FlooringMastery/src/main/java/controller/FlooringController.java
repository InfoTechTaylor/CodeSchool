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
        System.out.println("test");
    }

    private int printMenuAndRetrieveSelection(){

        return 0;
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

    }

    private void trainingMode(){

    }
}
