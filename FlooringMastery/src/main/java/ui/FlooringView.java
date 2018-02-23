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
                        "* 6. Enter Training Mode\n" +
                        "* 7. Quit\n" +
                        "* \n" +
                        "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * " +
                        "\nSelect one of the above options: ", 1, 7);
    }

    public LocalDate promptForDate(){
        return userIO.readLocalDate("Please enter a date to get orders: ");
    }

    public String promptForOrderId(){
        return null;
    }

//    private int orderNumber;
//    private LocalDate orderDate;
//    private String customerName;
//    private Tax taxObject;
//    private Product productObject;
//    private BigDecimal area;
//    private BigDecimal totalMaterialCost;
//    private BigDecimal totalLaborCost;
//    private BigDecimal totalTax;
//    private BigDecimal totalCost;

    public void displayOrdersByDate(List<Order> ordersList) {
        userIO.print("Orders for " + ordersList.get(0).getOrderDate() + ": ");
        userIO.print("=====================================================");
        for(Order currentOrder : ordersList){
            userIO.print("\tOrder Number: " + currentOrder.getOrderNumber());
            userIO.print("\tCustomer Name: " + currentOrder.getCustomerName());
            userIO.print("\tState: " + currentOrder.getTaxObject().getState());
            userIO.print("\tTax Rate: " + currentOrder.getTaxObject().getTaxRate() + "%");
            userIO.print("\tProduct Type: " + currentOrder.getProductObject().getProductType());
            userIO.print("\tArea: " + currentOrder.getArea() + " sq. ft.");
            userIO.print("\tTotal Material Cost: $" + currentOrder.getTotalMaterialCost());
            userIO.print("\tTotal Labor Cost: $" + currentOrder.getTotalLaborCost());
            userIO.print("\tTotal Taxes: $" + currentOrder.getTotalTax());
            userIO.print("\tTotal Cost: $" + currentOrder.getTotalCost() + "\n");
        }
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

    public void displayError(String message){
        userIO.print("ERROR:");
        userIO.print(message);
    }

    public void promptUserToHitEnter(){
        userIO.readString("Hit enter to continue: ");
    }
}
