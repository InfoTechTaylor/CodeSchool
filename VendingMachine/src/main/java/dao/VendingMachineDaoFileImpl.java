package dao;

import dto.VendingMachineItem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineDaoFileImpl implements VendingMachineDao {

    private Map<String, VendingMachineItem> vendingMachineItemMap = new HashMap<>();
    private final String VENDING_MACHINE_ITEMS_TEXT;
    private final String STRING_DELIMITER = "::";

    // constructor to accept file name for providing prod file verses testing file
    public VendingMachineDaoFileImpl(String VENDING_MACHINE_ITEMS_TEXT){
        this.VENDING_MACHINE_ITEMS_TEXT = VENDING_MACHINE_ITEMS_TEXT;
    }


    @Override
    public List<VendingMachineItem> retrieveAllVendingMachineItems() {
        return null;
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

    }

    private void writeVendingMachineItems() throws VendingMachinePersistenceException{

    }
}
