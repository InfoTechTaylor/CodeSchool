package ui;

import dto.Order;
import dto.Product;
import dto.Tax;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
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
            userIO.print("\tTotal Material Cost: " + NumberFormat.getCurrencyInstance().format(currentOrder.getTotalMaterialCost()));
            userIO.print("\tTotal Labor Cost: " + NumberFormat.getCurrencyInstance().format(currentOrder.getTotalLaborCost()));
            userIO.print("\tTotal Taxes: " + NumberFormat.getCurrencyInstance().format(currentOrder.getTotalTax()));
            userIO.print("\tTotal Cost: " + NumberFormat.getCurrencyInstance().format(currentOrder.getTotalCost()) + "\n");
        }
    }

    public void displayAllProducts(List<Product> productsList){
        userIO.print("Available products to choose from: ");
        for(Product currentProduct : productsList){
            userIO.print("\t" + currentProduct.getProductType());
        }
    }

    public void displayAllTaxes(List<Tax> allTaxObjectsList){
        userIO.print("Available states we support: ");
        for(Tax currentTaxObj : allTaxObjectsList){
            userIO.print("\t" + currentTaxObj.getState());
        }
    }

    public void displayOrderSummary(Order orderToDisplay){
        userIO.print("ORDER SUMMARY: ");
        userIO.print("\tOrder Date: " + orderToDisplay.getOrderDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
        userIO.print("\tCustomer Name: " + orderToDisplay.getCustomerName());
        userIO.print("\tState: " + orderToDisplay.getTaxObject().getState());
        userIO.print("\tProduct Selected: " + orderToDisplay.getProductObject().getProductType());
        userIO.print("\tArea in square feet: " + orderToDisplay.getArea());
    }

    public boolean promptToCommitToMemory(){
        String userChoice = userIO.readString("Commit order(s) to memory? (y/n)");
        return userChoice.toUpperCase().equals("Y");
    }

    public Order promptForNewOrderDetails(){
        Order newOrder = new Order();

//        userIO.print("Follow the below prompts to complete new order: ");
        String customerName = userIO.readString("Enter customer name: ");
        String state = userIO.readString("Enter your state: ");
        String productType = userIO.readString("Select the product you want: ");
        BigDecimal area = userIO.readBigDecimal("Enter the size of your floor in square feet: ");

        newOrder.setCustomerName(customerName);
        Tax taxObj = new Tax(state);
            newOrder.setTaxObject(taxObj);
        Product productObj = new Product(productType);
            newOrder.setProductObject(productObj);
        newOrder.setArea(area);

        return newOrder;
    }

    public Order promptForOrderUpdates(Order orderToUpdate){
        return null;
    }

    public boolean promptForSaveAllOrders(){
        String userChoice = userIO.readString("Are you sure you want to save? (y/n) ");
        return userChoice.toUpperCase().equals("Y");
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

    public void displayConfirmRevertChanges(){
        userIO.print("Changes not saved to permanent storage. ");
    }

    public void displayCreateNewOrderBanner(){
        userIO.print("=====================================================");
        userIO.print("CREATE NEW ORDER ");
        userIO.print("=====================================================");
    }


}
