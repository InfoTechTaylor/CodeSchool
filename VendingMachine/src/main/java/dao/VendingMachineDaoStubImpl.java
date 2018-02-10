package dao;

import dto.VendingMachineItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VendingMachineDaoStubImpl implements VendingMachineDao{

    VendingMachineItem onlyVendingMachineItem;
    ArrayList<VendingMachineItem> vendingMachineItemList = new ArrayList<>();

    public VendingMachineDaoStubImpl(){
        this.onlyVendingMachineItem = new VendingMachineItem("1");
        onlyVendingMachineItem.setItemName("Chips");
        onlyVendingMachineItem.setItemCost(new BigDecimal("1.00"));
        onlyVendingMachineItem.setItemQuantity(3);
        vendingMachineItemList.add(onlyVendingMachineItem);
    }

    @Override
    public List<VendingMachineItem> retrieveAllVendingMachineItems() throws VendingMachinePersistenceException {
        return vendingMachineItemList;
    }

    @Override
    public void updateItem(VendingMachineItem item) throws VendingMachinePersistenceException {
        vendingMachineItemList.set(0, item);
    }

    @Override
    public VendingMachineItem retrieveItemById(String itemId) throws VendingMachinePersistenceException {
        if(itemId.equals(onlyVendingMachineItem.getItemId())){
            return onlyVendingMachineItem;
        } else {
            return null;
        }
    }

    @Override
    public VendingMachineItem removeVendingMachineItem(String itemId) throws VendingMachinePersistenceException {
        if(itemId.equals(onlyVendingMachineItem.getItemId())){
            return onlyVendingMachineItem;
        } else {
            return null;
        }
    }

    @Override
    public VendingMachineItem createVendingMachineItem(VendingMachineItem item) throws VendingMachinePersistenceException {
        if(item.getItemId().equals(onlyVendingMachineItem.getItemId())){
            return onlyVendingMachineItem;
        } else {
            return null;
        }
    }
}
