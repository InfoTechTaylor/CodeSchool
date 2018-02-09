package dao;

import dto.VendingMachineItem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;

public class VendingMachineDaoFileImpl implements VendingMachineDao {

    private Map<String, VendingMachineItem> vendingMachineItemMap = new HashMap<>();
    private final String VENDING_MACHINE_ITEMS_TEXT;
    private final String STRING_DELIMITER = "::";

    // constructor to accept file name for providing prod file verses testing file
    public VendingMachineDaoFileImpl(String VENDING_MACHINE_ITEMS_TEXT){
        this.VENDING_MACHINE_ITEMS_TEXT = VENDING_MACHINE_ITEMS_TEXT;
    }


    @Override
    public List<VendingMachineItem> retrieveAllVendingMachineItems() throws VendingMachinePersistenceException{
        loadVendingMachineItems();
        return new ArrayList<VendingMachineItem>(vendingMachineItemMap.values());
    }

    @Override
    public void updateItem(VendingMachineItem item) {

    }

    @Override
    public VendingMachineItem retrieveItemById(String itemId) {
        return null;
    }

    @Override
    public VendingMachineItem removeVendingMachineItem(String item) {
        return null;
    }

    @Override
    public VendingMachineItem createVendingMachineItem(VendingMachineItem item) {
        return null;
    }

    private void loadVendingMachineItems() throws VendingMachinePersistenceException{
        Scanner scanner;

        try{
            // create Scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(VENDING_MACHINE_ITEMS_TEXT)));
        } catch(FileNotFoundException e) {
            throw new VendingMachinePersistenceException("-_- Could not load dvd data into memory.", e);
        }// end catch

        // currentLine holds the most recent line read from the file
        String currentLine;

        // currentToken holds each of the parts of the currentLine after it has been split on our DELIMITER
        String[] currentTokens;

        // Go through each line in VENDING_MACHINE_ITEMS_TEXT, decoding each line into a VendingMachineItem object
        // process while we have more lines in the file
        while(scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // break up the line into tokens
            currentTokens = currentLine.split(STRING_DELIMITER);
            // create a new VendingMachineItem object and put it into the map vendingMachineItemMap
            VendingMachineItem currentItem = new VendingMachineItem(currentTokens[0]);
            // set the remaining values on currentItem manually
            currentItem.setItemName(currentTokens[1]);
            currentItem.setItemCost(new BigDecimal(currentTokens[2]));
            currentItem.setItemQuantity(Integer.parseInt(currentTokens[3]));

            // put currentItem into the map using itemId as the key
            vendingMachineItemMap.put(currentItem.getItemId(), currentItem);
        } // end while(scanner.hasNextLine())
        //close scanner
        scanner.close();
    } // end load method

    private void writeVendingMachineItems() throws VendingMachinePersistenceException{

    }
}
