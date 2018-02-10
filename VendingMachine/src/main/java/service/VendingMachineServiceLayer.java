package service;


import dao.VendingMachinePersistenceException;
import dto.VendingMachineChange;
import dto.VendingMachineItem;

import java.math.BigDecimal;
import java.util.List;

public interface VendingMachineServiceLayer {
    List<VendingMachineItem> retrieveAllVendingMachineItems() throws VendingMachinePersistenceException;
    BigDecimal addMoneyToMemory(BigDecimal amount) throws InsufficientFundsException;
    VendingMachineItem purchaseItem(String itemId) throws VendingMachinePersistenceException, NoItemInventoryException, InsufficientFundsException;
    VendingMachineChange convertDollarsToCoinsAndGetChange() throws InsufficientFundsException;
    BigDecimal retrieveRemainingMoney();


}
