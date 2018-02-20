package dao;

import dto.Order;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlooringDaoOrderTrainingFileImpl implements FlooringDaoOrder {

    private Map<LocalDate, HashMap<Integer, Order>> ordersByDateMap = new HashMap<>();
    private static final String STRING_DELIMITER = ",";

    @Override
    public List<Order> retrieveOrdersByDate(LocalDate orderDate) {
        return null;
    }

    @Override
    public Order retrieveOrderByDateAndId(LocalDate orderDate, int orderNumber) {
        return null;
    }

    @Override
    public Order createOrder(Order orderObj) {
        return null;
    }

    @Override
    public void updateOrder(Order orderObj) {

    }

    @Override
    public void removeOrder(Order orderObj) {

    }

    private int generateOrderId(){
        return 0;
    }

    private void loadOrdersIntoMap(LocalDate orderDate){

    }




}
