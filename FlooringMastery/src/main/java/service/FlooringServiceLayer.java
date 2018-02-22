package service;

import dao.FlooringPersistenceException;
import dto.Order;
import dto.Product;
import dto.Tax;

import java.time.LocalDate;
import java.util.List;

public interface FlooringServiceLayer {
    List<Order> retrieveAllOrdersByDate(LocalDate orderDate) throws FlooringPersistenceException;
    List<Product> retrieveAllProducts() throws FlooringPersistenceException;
    List<Tax> retrieveAllTaxes() throws FlooringPersistenceException;
    Order processOrder(Order orderObj);
    Order addOrder(Order orderObj) throws FlooringPersistenceException;
    Order retrieveOrderByDateAndId(LocalDate orderDate, int orderNumber) throws FlooringPersistenceException;
    void removeOrder(LocalDate orderDate, int orderNumber) throws FlooringPersistenceException;
    Order editOrder(Order orderObj) throws FlooringPersistenceException;
    void saveAllOrders();
    boolean activateTrainingMode();

}
