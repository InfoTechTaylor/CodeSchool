package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dao.VendingMachineAuditDao;
import com.sg.vendingmachinespringmvc.dao.VendingMachineDao;
import com.sg.vendingmachinespringmvc.dao.VendingMachinePersistenceException;
import com.sg.vendingmachinespringmvc.dto.VendingMachineChange;
import com.sg.vendingmachinespringmvc.dto.VendingMachineItem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    private VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;
    private BigDecimal remainingMoney = new BigDecimal("0");
    private VendingMachineChange changeAmount = new VendingMachineChange(0, 0, 0, 0);

    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {

        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public List<VendingMachineItem> retrieveAllVendingMachineItems() throws VendingMachinePersistenceException {
        return dao.retrieveAllVendingMachineItems();
    }

    @Override
    public BigDecimal addMoneyToMemory(BigDecimal amount) throws InvalidAmountException {
        if (amount.compareTo(new BigDecimal("0")) > 0) {
            remainingMoney = (remainingMoney.add(amount)).setScale(2, RoundingMode.HALF_UP);
        } else {
            throw new InvalidAmountException("Must add positive amount to the machine.");
        }
        return remainingMoney;
    }

    @Override
    public VendingMachineChange purchaseItem(String itemId) throws VendingMachinePersistenceException, NoItemInventoryException, InsufficientFundsException {
        VendingMachineItem itemToPurchase = dao.retrieveItemById(itemId);
        validateItemChoice(itemId);
        validateFunds(itemToPurchase);

        // update VendingMachineItem quantity
        itemToPurchase.setItemQuantity(itemToPurchase.getItemQuantity() - 1);
        dao.updateItem(itemToPurchase);
        // update remainingMoney
        updateMoneyAmountInMemory(itemToPurchase.getItemCost());
        changeAmount = convertDollarsToCoinsAndGetChange();
        resetRemainingMoneyToZero();

        return changeAmount;
    }

    @Override
    public VendingMachineChange convertDollarsToCoinsAndGetChange() throws InsufficientFundsException {
        // create VendingMachineChange object
        VendingMachineChange countsOfCoins = new VendingMachineChange();
        BigDecimal zero = new BigDecimal("0");
        if (remainingMoney.compareTo(zero) > 0) {
            // convert remainingMoney to pennies and from BigDecimal to int
            int amountOfPennies = (remainingMoney.movePointRight(2)).setScale(2, RoundingMode.HALF_UP).intValueExact();

            int remainderOfPennies = 0;

            // get count of quarters
            if (amountOfPennies >= 25) {
                remainderOfPennies = amountOfPennies % 25;
                int countOfQuarters = (amountOfPennies - remainderOfPennies) / 25;
                countsOfCoins.setQuarters(countOfQuarters);
                amountOfPennies = remainderOfPennies;
            }

            if (amountOfPennies >= 10) {
                remainderOfPennies = amountOfPennies % 10;
                int countOfDimes = (amountOfPennies - remainderOfPennies) / 10;
                countsOfCoins.setDimes(countOfDimes);
                amountOfPennies = remainderOfPennies;
            }

            if (amountOfPennies >= 5) {
                remainderOfPennies = amountOfPennies % 5;
                int countOfNickels = (amountOfPennies - remainderOfPennies) / 5;
                countsOfCoins.setNickels(countOfNickels);
                amountOfPennies = remainderOfPennies;
            }

            if (amountOfPennies >= 1) {
                countsOfCoins.setPennies(remainderOfPennies);
            }
            changeAmount = countsOfCoins;
            resetRemainingMoneyToZero();
        } else {
            throw new InsufficientFundsException("No money left to get change.");
        }

        return changeAmount;
    }


    private void validateItemChoice(String itemId) throws VendingMachinePersistenceException, NoItemInventoryException {

        VendingMachineItem itemToValidate = dao.retrieveItemById(itemId);
        if (itemId.equals("")) {
            throw new NoItemInventoryException("Please select an item.");
        }
        if (itemToValidate == null){
            throw new NoItemInventoryException("Cannot find item with given ID. ");
        }
        if (itemToValidate.getItemQuantity() == 0) {
            throw new NoItemInventoryException("SOLD OUT!!!");
        }


    }

    private void validateFunds(VendingMachineItem item) throws InsufficientFundsException {
        if (!((item.getItemCost()).compareTo(remainingMoney) <= 0)) {
            throw new InsufficientFundsException("Please insert: " + item.getItemCost().subtract(remainingMoney));
        }
    }

    private void resetRemainingMoneyToZero() {
        remainingMoney = new BigDecimal("0");
    }

    private void updateMoneyAmountInMemory(BigDecimal amount) {
        remainingMoney = remainingMoney.subtract(amount);
    }


//    public void setRemainingMoney(BigDecimal remainingMoney) {
//        this.remainingMoney = remainingMoney;
//    }

    public BigDecimal getRemainingMoney() {
        return remainingMoney;
    }

    public VendingMachineItem retrieveVendingMachineItemById(String itemId) throws VendingMachinePersistenceException {
        return dao.retrieveItemById(itemId);
    }

    public VendingMachineChange getChangeAmount() {
        return changeAmount;
    }
}
