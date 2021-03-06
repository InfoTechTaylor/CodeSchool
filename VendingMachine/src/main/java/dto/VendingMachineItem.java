package dto;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendingMachineItem that = (VendingMachineItem) o;
        return itemQuantity == that.itemQuantity &&
                Objects.equals(itemId, that.itemId) &&
                Objects.equals(itemName, that.itemName) &&
                Objects.equals(itemCost, that.itemCost);
    }

    @Override
    public int hashCode() {

        return Objects.hash(itemId, itemName, itemCost, itemQuantity);
    }

    @Override
    public String toString() {
        return "VendingMachineItem{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemCost=" + itemCost +
                ", itemQuantity=" + itemQuantity +
                '}';
    }
}
