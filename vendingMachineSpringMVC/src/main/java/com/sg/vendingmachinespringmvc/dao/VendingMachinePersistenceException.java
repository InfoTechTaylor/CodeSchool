package com.sg.vendingmachinespringmvc.dao;

public class VendingMachinePersistenceException extends Exception {

    VendingMachinePersistenceException(String message){
        super(message);
    }

    VendingMachinePersistenceException(String message, Throwable cause){
        super(message, cause);
    }
}
