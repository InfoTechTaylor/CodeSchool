package com.sg.vendingmachinespringmvc.dto;

import java.util.Objects;

public class VendingMachineChange {
    private int quarters;
    private int dimes;
    private int nickels;
    private int pennies;

    public VendingMachineChange(int quarters, int dimes, int nickels, int pennies) {
        this.quarters = quarters;
        this.dimes = dimes;
        this.nickels = nickels;
        this.pennies = pennies;
    }

    public VendingMachineChange() {
    }

    public int getQuarters() {
        return quarters;
    }

    public void setQuarters(int quarters) {
        this.quarters = quarters;
    }

    public int getDimes() {
        return dimes;
    }

    public void setDimes(int dimes) {
        this.dimes = dimes;
    }

    public int getNickels() {
        return nickels;
    }

    public void setNickels(int nickels) {
        this.nickels = nickels;
    }

    public int getPennies() {
        return pennies;
    }

    public void setPennies(int pennies) {
        this.pennies = pennies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendingMachineChange that = (VendingMachineChange) o;
        return quarters == that.quarters &&
                dimes == that.dimes &&
                nickels == that.nickels &&
                pennies == that.pennies;
    }

    @Override
    public int hashCode() {

        return Objects.hash(quarters, dimes, nickels, pennies);
    }
}
