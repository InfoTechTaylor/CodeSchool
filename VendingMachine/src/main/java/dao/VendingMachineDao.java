package dao;

import dto.VendingMachineItem;

import java.util.List;

public interface VendingMachineDao {

    List<VendingMachineItem> retrieveAllVendingMachineItems() throws VendingMachinePersistenceException;
    void updateItem(VendingMachineItem item) throws VendingMachinePersistenceException;
    VendingMachineItem retrieveItemById(String itemId) throws VendingMachinePersistenceException;
    VendingMachineItem removeVendingMachineItem(String item);
    VendingMachineItem createVendingMachineItem(VendingMachineItem item);
}
