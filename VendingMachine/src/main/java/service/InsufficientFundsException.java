package service;

public class InsufficientFundsException extends Exception {

    InsufficientFundsException(String message){
        super(message);
    }

    InsufficientFundsException(String message, Throwable cause){
        super(message, cause);
    }
}
