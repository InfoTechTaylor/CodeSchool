package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dao.VendingMachinePersistenceException;
import com.sg.vendingmachinespringmvc.dto.VendingMachineChange;
import com.sg.vendingmachinespringmvc.dto.VendingMachineItem;

import java.math.BigDecimal;
import java.util.List;

public interface VendingMachineServiceLayer {
    List<VendingMachineItem> retrieveAllVendingMachineItems() throws VendingMachinePersistenceException;
    BigDecimal addMoneyToMemory(Money type) throws InvalidAmountException;
    String purchaseItem(String itemId) throws VendingMachinePersistenceException, NoItemInventoryException, InsufficientFundsException;
    VendingMachineChange convertDollarsToCoinsAndGetChange() throws InsufficientFundsException;
    BigDecimal getRemainingMoney();
    VendingMachineChange getChangeAmount();
    String getMessage();



}
