package com.sg.vendingmachinespringmvc.service;

public class NoItemInventoryException extends Exception {
    NoItemInventoryException(String message){
        super(message);
    }

    NoItemInventoryException(String message, Throwable cause){
        super(message, cause);
    }
}
