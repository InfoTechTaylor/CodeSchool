package ui;

import dto.VendingMachineChange;
import dto.VendingMachineItem;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Formatter;
import java.util.List;

public class VendingMachineView {

    private UserIO io;

    public VendingMachineView(UserIO io){
        this.io = io;
    } // end constructor

    public void displayWelcomeBanner(){
        io.print("===========================================");
        io.print("      WELCOME TO THE VENDING MACHINE!");
        io.print("===========================================");
    }

    public int displayMenuAndPromptSelection(){
        return io.readInt("\nMAIN MENU: \n" +
                "===========================================\n" +
                "1. Add Money\n" +
                "2. Purchase an Item\n" +
                "3. Get Change\n" +
                "4. Exit\n\n" +
                "Enter your selection (1-4) and hit enter: ", 1, 4);
    }

    public void displayVendingMachineInventory(List<VendingMachineItem> itemList){

        io.print("Item Id, Name, Cost, Quantity");
        for(VendingMachineItem currentItem : itemList){
            if(currentItem.getItemQuantity() != 0) {
                io.print(currentItem.getItemId() + "," + currentItem.getItemName() +
                        "," + currentItem.getItemCost() + "," + currentItem.getItemQuantity());
            }
        }
    }

    public BigDecimal promptForMoneyToAdd(){
        return io.readBigDecimal("Please enter the amount of money you have to spend: ");
    }

    public String promptForItemId(){
        return null;
    }

    public void displayChange(VendingMachineChange change){

    }

    public void displayItem(VendingMachineItem item){

    }

    public void displayCurrentBalance(BigDecimal currentBalance){
        io.print("\nCurrent Balance: " + NumberFormat.getCurrencyInstance().format(currentBalance) +"\n");
    }

    public void displayErrorMessage(String message){
        io.print("ERROR");
        io.print("===========================================");
        io.print(message);
    }

    public void displaySuccessExitBanner(){
        io.print("Exit Successful. Good bye! ");
    }
}
