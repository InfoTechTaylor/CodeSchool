package ui;

import dto.VendingMachineChange;
import dto.VendingMachineItem;

import java.math.BigDecimal;
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
            io.print(currentItem.getItemId() + "," + currentItem.getItemName() +
                    "," + currentItem.getItemCost() + "," + currentItem.getItemQuantity());
        }
    }

    public BigDecimal promptForMoneyToAdd(){
        return null;
    }

    public String promptForItemId(){
        return null;
    }

    public void displayChange(VendingMachineChange change){

    }

    public void displayItem(VendingMachineItem item){

    }

    public void displayCurrentBalance(BigDecimal currentBalance){

    }

    public void displayErrorMessage(String message){
        io.print("ERROR");
        io.print("===========================================");
        io.print(message);
    }
}
