package service;

import dto.Order;
import dto.Tax;

import java.time.LocalDate;
import java.util.List;

public interface FlooringServiceLayer {
    List<Order> retrieveAllOrdersByDate(LocalDate orderDate);
    List<Order> retrieveAllProducts();
    List<Tax> retrieveAllTaxes();
    Order processOrder(Order orderObj);
    Order addOrder(Order orderObj);
    Order retrieveOrderByDateAndId(LocalDate orderDate, int orderNumber);
    void removeOrder(LocalDate orderDate, int orderNumber);
    Order editOrder(Order orderObj);
    void saveAllOrders();
    boolean activateTrainingMode();

}
