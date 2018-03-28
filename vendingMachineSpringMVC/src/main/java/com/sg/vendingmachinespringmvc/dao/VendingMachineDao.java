package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.dto.VendingMachineItem;

import java.util.List;

public interface VendingMachineDao {

    List<VendingMachineItem> retrieveAllVendingMachineItems() throws VendingMachinePersistenceException;
    void updateItem(VendingMachineItem item) throws VendingMachinePersistenceException;
    VendingMachineItem retrieveItemById(VendingMachineItem item) throws VendingMachinePersistenceException;
    VendingMachineItem removeVendingMachineItem(VendingMachineItem item) throws VendingMachinePersistenceException;
    VendingMachineItem createVendingMachineItem(VendingMachineItem item) throws VendingMachinePersistenceException;
}
