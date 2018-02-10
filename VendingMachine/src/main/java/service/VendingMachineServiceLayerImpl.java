package service;

import dao.VendingMachineDao;
import dao.VendingMachinePersistenceException;
import dto.VendingMachineChange;
import dto.VendingMachineItem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    private VendingMachineDao dao;
    private BigDecimal remainingMoney = new BigDecimal("0");

    public VendingMachineServiceLayerImpl(VendingMachineDao dao) {
        this.dao = dao;
    }

    @Override
    public List<VendingMachineItem> retrieveAllVendingMachineItems() throws VendingMachinePersistenceException {
        return dao.retrieveAllVendingMachineItems();
    }

    @Override
    public BigDecimal addMoneyToMemory(BigDecimal amount) throws InsufficientFundsException {
        if (amount.compareTo(new BigDecimal("0")) > 0) {
            remainingMoney = (remainingMoney.add(amount)).setScale(2, RoundingMode.HALF_UP);
        } else {
            throw new InsufficientFundsException("Must add positive amount to the machine.");
        }
        return remainingMoney;
    }

    @Override
    public VendingMachineItem purchaseItem(String itemId) throws VendingMachinePersistenceException, NoItemInventoryException, InsufficientFundsException {
        VendingMachineItem itemToPurchase = dao.retrieveItemById(itemId);
        boolean isValidChoice = validateItemChoice(itemId);
        boolean validFunds = validateFunds(itemToPurchase);

        if (isValidChoice && validFunds) {

            // update VendingMachineItem quantity
            itemToPurchase.setItemQuantity(itemToPurchase.getItemQuantity() - 1);
            dao.updateItem(itemToPurchase);
            // update remainingMoney
            updateMoneyAmountInMemory(itemToPurchase.getItemCost());
        }

        return itemToPurchase;
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

            resetRemainingMoneyToZero();
        } else {
            throw new InsufficientFundsException("No money left to get change.");
        }

        return countsOfCoins;
    }


    private boolean validateItemChoice(String itemId) throws VendingMachinePersistenceException, NoItemInventoryException {

        VendingMachineItem itemToValidate = dao.retrieveItemById(itemId);
        if (itemToValidate != null && itemToValidate.getItemQuantity() != 0) {
            return true;
        } else if (itemToValidate != null && itemToValidate.getItemQuantity() == 0) {
//            return false;
            throw new NoItemInventoryException("That item is currently out of stock. ");
        } else {
            throw new NoItemInventoryException("Cannot find item with given ID. ");
        }

    }

    private boolean validateFunds(VendingMachineItem item) throws InsufficientFundsException {
        if ((item.getItemCost()).compareTo(remainingMoney) <= 0) {
            return true;
        } else {
//            return false;
            throw new InsufficientFundsException("Not enough money available to make purchase.");
        }
    }

    private void resetRemainingMoneyToZero() {
        remainingMoney = new BigDecimal("0");
    }

    private void updateMoneyAmountInMemory(BigDecimal amount) {
        remainingMoney = remainingMoney.subtract(amount);
    }


    protected void setRemainingMoney(BigDecimal remainingMoney) {
        this.remainingMoney = remainingMoney;
    }
    public BigDecimal getRemainingMoney() {
        return remainingMoney;
    }
}
