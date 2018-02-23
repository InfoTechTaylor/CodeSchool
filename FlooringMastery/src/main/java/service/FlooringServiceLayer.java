package service;

import dao.FlooringPersistenceException;
import dto.Order;
import dto.Product;
import dto.Tax;

import java.time.LocalDate;
import java.util.List;

public interface FlooringServiceLayer {
    List<Order> retrieveAllOrdersByDate(LocalDate orderDate) throws FlooringPersistenceException, OrderNotFoundException;
    List<Product> retrieveAllProducts() throws FlooringPersistenceException;
    List<Tax> retrieveAllTaxes() throws FlooringPersistenceException;
    Order processOrder(Order orderObj) throws FlooringPersistenceException, TaxStateNotFoundException, ProductMaterialNotFoundException;
    Order addOrder(Order orderObj) throws FlooringPersistenceException, TaxStateNotFoundException, ProductMaterialNotFoundException;
    Order retrieveOrderByDateAndId(LocalDate orderDate, int orderNumber) throws FlooringPersistenceException, OrderNotFoundException;
    void removeOrder(LocalDate orderDate, int orderNumber) throws FlooringPersistenceException, OrderNotFoundException;
    Order editOrder(Order orderObj) throws FlooringPersistenceException;
    void saveAllOrders() throws FlooringPersistenceException;
    boolean activateTrainingMode();

}
