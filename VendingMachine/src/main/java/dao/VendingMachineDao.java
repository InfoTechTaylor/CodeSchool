package dao;

import dto.VendingMachineItem;

import java.util.List;

public interface VendingMachineDao {

    List<VendingMachineItem> retrieveAllVendingMachineItems();
    void updateItem(VendingMachineItem item);
    VendingMachineItem retrieveItemById(String itemId);
    VendingMachineItem removeVendingMachineItem(String item);
    VendingMachineItem createVendingMachineItem(VendingMachineItem item);
}
