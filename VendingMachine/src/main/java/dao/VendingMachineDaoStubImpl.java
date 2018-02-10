package dao;

import dto.VendingMachineItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VendingMachineDaoStubImpl implements VendingMachineDao{

    private VendingMachineItem onlyVendingMachineItem;
    private ArrayList<VendingMachineItem> vendingMachineItemList = new ArrayList<>();


    public VendingMachineDaoStubImpl(){
        this.onlyVendingMachineItem = new VendingMachineItem("1");
        onlyVendingMachineItem.setItemName("Chips");
        onlyVendingMachineItem.setItemCost(new BigDecimal("1.00"));
        onlyVendingMachineItem.setItemQuantity(3);
        vendingMachineItemList.add(onlyVendingMachineItem);

    }

    @Override
    public List<VendingMachineItem> retrieveAllVendingMachineItems()  {
        return vendingMachineItemList;
    }

    @Override
    public void updateItem(VendingMachineItem item)  {
        vendingMachineItemList.set(0, item);
    }

    @Override
    public VendingMachineItem retrieveItemById(String itemId)  {
        if(itemId.equals(onlyVendingMachineItem.getItemId())){
            return onlyVendingMachineItem;
        } else {
            return null;
        }
    }

    @Override
    public VendingMachineItem removeVendingMachineItem(String itemId)  {
        if(itemId.equals(onlyVendingMachineItem.getItemId())){
            return onlyVendingMachineItem;
        } else {
            return null;
        }
    }

    @Override
    public VendingMachineItem createVendingMachineItem(VendingMachineItem item) {
        if(item.getItemId().equals(onlyVendingMachineItem.getItemId())){
            return onlyVendingMachineItem;
        } else {
            return null;
        }
    }
}
