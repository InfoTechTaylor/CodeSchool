package dao;

import dto.Order;

import java.time.LocalDate;
import java.util.List;

public interface FlooringDaoOrder {

    List<Order> retrieveAllOrdersByDate(LocalDate orderDate) throws FlooringPersistenceException;
    Order retrieveOrderByDateAndId(LocalDate orderDate, int orderNumber) throws FlooringPersistenceException;
    Order createOrder(LocalDate orderDate, Order orderObj) throws FlooringPersistenceException;
    void updateOrder(LocalDate orderDate, Order orderObj) throws FlooringPersistenceException;
    void removeOrder(LocalDate orderDate, int orderNum) throws FlooringPersistenceException;
}
