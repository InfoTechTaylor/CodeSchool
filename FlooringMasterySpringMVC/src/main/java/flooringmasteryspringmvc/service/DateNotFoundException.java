package flooringmasteryspringmvc.service;

public class DateNotFoundException extends Exception {

    public DateNotFoundException(String message){
        super(message);
    }

    public DateNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}
