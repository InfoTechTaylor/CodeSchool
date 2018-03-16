package com.sg.vendingmachinespringmvc.service;

public class InvalidAmountException extends Exception {
    InvalidAmountException(String message){
        super(message);
    }

    InvalidAmountException(String message, Throwable cause){
        super(message, cause);
    }
}
