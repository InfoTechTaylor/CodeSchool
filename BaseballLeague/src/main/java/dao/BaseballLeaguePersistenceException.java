package dao;

public class BaseballLeaguePersistenceException extends Exception {

    BaseballLeaguePersistenceException(String message){
        super(message);
    }

    BaseballLeaguePersistenceException(String message, Throwable cause){
        super(message, cause);
    }
}
