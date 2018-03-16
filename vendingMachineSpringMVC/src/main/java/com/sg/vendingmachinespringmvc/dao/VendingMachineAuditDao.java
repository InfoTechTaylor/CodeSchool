package com.sg.vendingmachinespringmvc.dao;

public interface VendingMachineAuditDao {

    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException;
}
