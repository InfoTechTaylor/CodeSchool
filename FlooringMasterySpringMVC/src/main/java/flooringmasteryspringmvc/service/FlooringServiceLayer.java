package flooringmasteryspringmvc.service;



import flooringmasteryspringmvc.dao.FlooringPersistenceException;
import flooringmasteryspringmvc.dto.Order;
import flooringmasteryspringmvc.dto.Product;
import flooringmasteryspringmvc.dto.Tax;

import java.time.LocalDate;
import java.util.List;

public interface FlooringServiceLayer {
    List<Order> retrieveAllOrdersByDate(LocalDate orderDate) throws FlooringPersistenceException, OrderNotFoundException, DateNotFoundException;
    List<Product> retrieveAllProducts() throws FlooringPersistenceException;
    List<Tax> retrieveAllTaxes() throws FlooringPersistenceException;
    Order processOrder(Order orderObj) throws FlooringPersistenceException, TaxStateNotFoundException, ProductMaterialNotFoundException;
    Order addOrder(Order orderObj) throws FlooringPersistenceException, TaxStateNotFoundException, ProductMaterialNotFoundException;
    Order retrieveOrderByDateAndId(LocalDate orderDate, String orderNumber) throws FlooringPersistenceException, OrderNotFoundException, DateNotFoundException;
    void removeOrder(LocalDate orderDate, String orderNumber) throws FlooringPersistenceException, OrderNotFoundException, DateNotFoundException;
    Order editOrder(LocalDate originalDate, Order orderObj) throws FlooringPersistenceException, TaxStateNotFoundException, ProductMaterialNotFoundException, OrderNotFoundException, DateNotFoundException;
    void saveAllOrders() throws FlooringPersistenceException;
    boolean activateTrainingMode(boolean userSelection);

}
