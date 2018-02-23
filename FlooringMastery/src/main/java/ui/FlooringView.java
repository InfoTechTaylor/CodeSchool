package ui;

import dto.Order;
import dto.Product;
import dto.Tax;

import java.time.LocalDate;
import java.util.List;

public class FlooringView {

    private UserIO userIO;

    public FlooringView(UserIO userIO) {
        this.userIO = userIO;
    }

    public int displayMenuAndPromptForSelection(){
        return userIO.readInt("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n" +
                        "*  <<Flooring Program>>\n" +
                        "* 1. Display Orders\n" +
                        "* 2. Add an Order\n" +
                        "* 3. Edit an Order\n" +
                        "* 4. Remove an Order\n" +
                        "* 5. Save Current Work\n" +
                        "* 6. Quit\n" +
                        "* \n" +
                        "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * ", 1, 6);
    }

    public LocalDate promptForDate(){
        return null;
    }

    public String promptForOrderId(){
        return null;
    }

    public void displayOrdersByDate(List<Order> ordersList) {

    }

    public void displayAllProducts(List<Product> productsList){

    }

    public void displayAllTaxes(List<Tax> allTaxObjectsList){

    }

    public void displayOrderSummary(Order orderToDisplay){

    }

    public boolean promptToCommitToMemory(){
        return true;
    }

    public Order promptForNewOrderDetails(){
        return null;
    }

    public Order promptForOrderUpdates(Order orderToUpdate){
        return null;
    }

    public boolean promptForSaveAllOrders(){
        return true;
    }

    public void displayUnknownCommand(){
        userIO.print("Unknown command");
    }

    public void displayGoodBye(){
        userIO.print("Exiting program. Goodbye!");
    }
}
