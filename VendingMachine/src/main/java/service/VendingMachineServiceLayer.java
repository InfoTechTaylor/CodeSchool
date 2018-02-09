package service;


import dao.VendingMachinePersistenceException;
import dto.VendingMachineChange;
import dto.VendingMachineItem;

import java.math.BigDecimal;
import java.util.List;

public interface VendingMachineServiceLayer {
    List<VendingMachineItem> retrieveAllVendingMachineItems() throws VendingMachinePersistenceException;
    BigDecimal addMoneyToMemory(BigDecimal amount);
    VendingMachineItem purchaseItem(String itemId);
    VendingMachineChange convertDollarsToChange();
    BigDecimal retrieveRemainingMoney();


}
