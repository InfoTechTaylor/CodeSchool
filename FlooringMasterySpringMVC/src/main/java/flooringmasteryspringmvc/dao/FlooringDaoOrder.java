package flooringmasteryspringmvc.dao;



import flooringmasteryspringmvc.dto.Order;

import java.time.LocalDate;
import java.util.List;

public interface FlooringDaoOrder {

    List<Order> retrieveAllOrdersByDate(LocalDate orderDate) throws FlooringPersistenceException;
    Order retrieveOrderByDateAndId(LocalDate orderDate, String orderNumber) throws FlooringPersistenceException;
    Order createOrder(LocalDate orderDate, Order orderObj) throws FlooringPersistenceException;
    void updateOrder(LocalDate orderDate, Order orderObj) throws FlooringPersistenceException;
    void removeOrder(LocalDate orderDate, String orderNum) throws FlooringPersistenceException;
    void saveOrders() throws FlooringPersistenceException;
    public List<Order> retrieveAllOrders();
}
