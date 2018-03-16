package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dao.VendingMachinePersistenceException;
import com.sg.vendingmachinespringmvc.dto.VendingMachineChange;
import com.sg.vendingmachinespringmvc.dto.VendingMachineItem;

import java.math.BigDecimal;
import java.util.List;

public interface VendingMachineServiceLayer {
    List<VendingMachineItem> retrieveAllVendingMachineItems() throws VendingMachinePersistenceException;
    BigDecimal addMoneyToMemory(BigDecimal amount) throws InvalidAmountException;
    VendingMachineItem purchaseItem(String itemId) throws VendingMachinePersistenceException, NoItemInventoryException, InsufficientFundsException;
    VendingMachineChange convertDollarsToCoinsAndGetChange() throws InsufficientFundsException;
    BigDecimal getRemainingMoney();



}
