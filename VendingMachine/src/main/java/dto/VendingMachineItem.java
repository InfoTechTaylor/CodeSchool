package dto;

import java.math.BigDecimal;

public class VendingMachineItem {

    private String itemId;
    private String itemName;
    private BigDecimal itemCost;
    private int itemQuantity;

    public VendingMachineItem(String itemId) {
        this.itemId = itemId;
    }

    public String getItemId() {
        return itemId;
    }

// KEEP NO SETTER FOR itemId so that it is read only

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getItemCost() {
        return itemCost;
    }

    public void setItemCost(BigDecimal itemCost) {
        this.itemCost = itemCost;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
}
