package service;

import dao.VendingMachineDao;
import dao.VendingMachinePersistenceException;
import dto.VendingMachineChange;
import dto.VendingMachineItem;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    private VendingMachineDao dao;
    private VendingMachineChange remainingChange;
    private BigDecimal remainingMoney = new BigDecimal("0");

    public VendingMachineServiceLayerImpl(VendingMachineDao dao){
        this.dao = dao;
    }

    @Override
    public List<VendingMachineItem> retrieveAllVendingMachineItems() throws VendingMachinePersistenceException {
        return dao.retrieveAllVendingMachineItems();
    }

    @Override
    public BigDecimal addMoneyToMemory(BigDecimal amount) {
        remainingMoney = remainingMoney.add(amount);
        return remainingMoney;
    }

    @Override
    public VendingMachineItem purchaseItem(String itemId) throws VendingMachinePersistenceException{
        VendingMachineItem itemToPurchase = dao.retrieveItemById(itemId);
        boolean isValidChoice = validateItemChoice(itemId);
        boolean validFunds = validateFunds(itemToPurchase);

        if(isValidChoice && validFunds){

            // update VendingMachineItem quantity
            itemToPurchase.setItemQuantity(itemToPurchase.getItemQuantity()-1);
            dao.updateItem(itemToPurchase);
            // update remainingMoney
            updateMoneyAmountInMemory(itemToPurchase.getItemCost());
        }
        return itemToPurchase;
    }

    @Override
    public VendingMachineChange convertDollarsToChange() {
        return null;
    }

    @Override
    public BigDecimal retrieveRemainingMoney() {
        return remainingMoney;
    }

    private boolean validateItemChoice(String itemId) throws VendingMachinePersistenceException{
        boolean isValid;
        VendingMachineItem itemToValidate = dao.retrieveItemById(itemId);
        if(itemToValidate != null && itemToValidate.getItemQuantity() != 0){
            isValid = true;
        } else {
            isValid = false;
            // THROW NOITEMINVENTORYEXCEPTION
        }
        return isValid;
    }

    private boolean validateFunds(VendingMachineItem item){
        if((item.getItemCost()).compareTo(remainingMoney) < 0){
            return true;
        } else {
            return false;
            // THROW INSUFFICIENTFUNDSEXCEPTION
        }
    }

    private void resetRemainingMoneyToZero(){

    }

    private BigDecimal updateMoneyAmountInMemory(BigDecimal amount){
        return remainingMoney = remainingMoney.subtract(amount);
    }

    private void updateItemQuantity(int quantity){

    }
}
