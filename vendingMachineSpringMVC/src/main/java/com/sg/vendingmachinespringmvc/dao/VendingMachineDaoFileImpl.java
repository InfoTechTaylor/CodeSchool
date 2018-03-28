package com.sg.vendingmachinespringmvc.dao;



import com.sg.vendingmachinespringmvc.dto.VendingMachineItem;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class VendingMachineDaoFileImpl implements VendingMachineDao {

    private Map<Integer, VendingMachineItem> vendingMachineItemMap = new HashMap<>();
    private final String VENDING_MACHINE_ITEMS_TEXT;
    private final String STRING_DELIMITER = "::";

    // constructor to accept file name for providing prod file verses testing file
    public VendingMachineDaoFileImpl(String VENDING_MACHINE_ITEMS_TEXT){
        this.VENDING_MACHINE_ITEMS_TEXT = VENDING_MACHINE_ITEMS_TEXT;
    }


    @Override
    public List<VendingMachineItem> retrieveAllVendingMachineItems() throws VendingMachinePersistenceException{
        loadVendingMachineItems();
        return new ArrayList<>(vendingMachineItemMap.values());
    }

    @Override
    public void updateItem(VendingMachineItem item) throws VendingMachinePersistenceException{
        vendingMachineItemMap.replace(item.getItemId(), item);
        writeVendingMachineItems();
    }

    @Override
    public VendingMachineItem retrieveItemById(VendingMachineItem item) throws VendingMachinePersistenceException{
        loadVendingMachineItems();
        return vendingMachineItemMap.get(item.getItemId());
    }

    @Override
    public VendingMachineItem removeVendingMachineItem(VendingMachineItem item) throws VendingMachinePersistenceException{
        VendingMachineItem itemToRemove = vendingMachineItemMap.remove(item.getItemId());
        writeVendingMachineItems();
        return itemToRemove;
    }

    @Override
    public VendingMachineItem createVendingMachineItem(VendingMachineItem item) throws VendingMachinePersistenceException {
        VendingMachineItem itemToCreate = vendingMachineItemMap.put(item.getItemId(), item);
        writeVendingMachineItems();
        return itemToCreate;
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
            VendingMachineItem currentItem = new VendingMachineItem(Integer.parseInt(currentTokens[0]));
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
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(VENDING_MACHINE_ITEMS_TEXT));
        } catch (IOException e){
            throw new VendingMachinePersistenceException("Could not save vending machine data");
        }

        // Write out the VendingMachineItem objects to the file
        List<VendingMachineItem> itemList = this.retrieveAllVendingMachineItems();
        for (VendingMachineItem currentItem : itemList){
            // write the VendingMachineItem object to the file
            out.println(currentItem.getItemId() + STRING_DELIMITER
                            + currentItem.getItemName() + STRING_DELIMITER
                            + currentItem.getItemCost() + STRING_DELIMITER
                            + currentItem.getItemQuantity());
            // force PrintWriter to write line to the file
            out.flush();
        } // end enhanced for loop
        // clean up
        out.close();
    }


}
