package com.sg.luckysevens;

import java.math.BigDecimal;

public class Result {

    private int totalNumberOfRolls;
    private int numberOfRollsAtMaxAmount;
    private BigDecimal maxAmount;

    public Result(int totalNumberOfRolls, int numberOfRollsAtMaxAmount, BigDecimal maxAmount) {
        this.totalNumberOfRolls = totalNumberOfRolls;
        this.numberOfRollsAtMaxAmount = numberOfRollsAtMaxAmount;
        this.maxAmount = maxAmount;
    }

    public int getTotalNumberOfRolls() {
        return totalNumberOfRolls;
    }

    public void setTotalNumberOfRolls(int totalNumberOfRolls) {
        this.totalNumberOfRolls = totalNumberOfRolls;
    }

    public int getNumberOfRollsAtMaxAmount() {
        return numberOfRollsAtMaxAmount;
    }

    public void setNumberOfRollsAtMaxAmount(int numberOfRollsAtMaxAmount) {
        this.numberOfRollsAtMaxAmount = numberOfRollsAtMaxAmount;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }
}
