package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.dto.VendingMachineItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VendingMachineDaoStubImpl implements VendingMachineDao{

    private VendingMachineItem firstVendingMachineItem;
    private VendingMachineItem secondVendingMachineItem;
    private ArrayList<VendingMachineItem> vendingMachineItemList = new ArrayList<>();


    public VendingMachineDaoStubImpl(){
        this.firstVendingMachineItem = new VendingMachineItem("1");
        firstVendingMachineItem.setItemName("Chips");
        firstVendingMachineItem.setItemCost(new BigDecimal("1.00"));
        firstVendingMachineItem.setItemQuantity(3);
        vendingMachineItemList.add(firstVendingMachineItem);

        this.secondVendingMachineItem = new VendingMachineItem("2");
        secondVendingMachineItem.setItemName("Soda");
        secondVendingMachineItem.setItemCost(new BigDecimal("1.50"));
        secondVendingMachineItem.setItemQuantity(0);
        vendingMachineItemList.add(secondVendingMachineItem);

    }

    @Override
    public List<VendingMachineItem> retrieveAllVendingMachineItems()  {
        return vendingMachineItemList;
    }

    @Override
    public void updateItem(VendingMachineItem item)  {
        if(item.getItemId().equals(firstVendingMachineItem.getItemId())){
            firstVendingMachineItem.setItemQuantity(item.getItemQuantity());
        } else if (item.getItemId().equals(secondVendingMachineItem.getItemId())){
            secondVendingMachineItem.setItemQuantity(item.getItemQuantity());
        }
    }

    @Override
    public VendingMachineItem retrieveItemById(String itemId)  {
        if(itemId.equals(firstVendingMachineItem.getItemId())){
            return firstVendingMachineItem;
        } else if (itemId.equals(secondVendingMachineItem.getItemId())){
            return secondVendingMachineItem;
        } else {
            return null;
        }
    }

    @Override
    public VendingMachineItem removeVendingMachineItem(String itemId)  {
        if(itemId.equals(firstVendingMachineItem.getItemId())){
            return firstVendingMachineItem;
        } else if (itemId.equals(secondVendingMachineItem.getItemId())){
            return secondVendingMachineItem;
        } else {
            return null;
        }
    }

    @Override
    public VendingMachineItem createVendingMachineItem(VendingMachineItem item) {
        if(item.getItemId().equals(firstVendingMachineItem.getItemId())){
            return firstVendingMachineItem;
        } else if (item.getItemId().equals(secondVendingMachineItem.getItemId())){
            return secondVendingMachineItem;
        } else {
            return null;
        }
    }


}
