package dao;

import dto.Order;

import java.time.LocalDate;
import java.util.List;

public interface FlooringDaoOrder {

    List<Order> retrieveOrdersByDate(LocalDate orderDate);
    Order retrieveOrderByDateAndId(LocalDate orderDate, int orderNumber);
    Order createOrder(Order orderObj);
    void updateOrder(Order orderObj);
    void removeOrder(Order orderObj);
}
