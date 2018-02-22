package dao;

import dto.Order;
import dto.Product;
import dto.Tax;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FlooringDaoOrderStubImpl implements FlooringDaoOrder {

    Order onlyOrder;
    List<Order> allOrders = new ArrayList<>();
    Tax onlyTaxObject;
    Product onlyProductObject;

    private static LocalDate orderDate = LocalDate.parse("02202000", DateTimeFormatter.ofPattern("MMddyyyy"));

    public FlooringDaoOrderStubImpl(){
        onlyTaxObject = new Tax("NH", new BigDecimal("3.5"));
        onlyProductObject = new Product("Carpet",new BigDecimal("2.25"),new BigDecimal("2.10"));
        onlyOrder = new Order();
        onlyOrder.setTaxObject(onlyTaxObject);
        onlyOrder.setOrderNumber(1);
        onlyOrder.setProductObject(onlyProductObject);
        onlyOrder.setCustomerName("TaylorLapointe");
        onlyOrder.setOrderDate(orderDate);
        onlyOrder.setArea(new BigDecimal("5"));
        onlyOrder.setTotalCost(new BigDecimal("5"));
        onlyOrder.setTotalTax(new BigDecimal("5"));
        onlyOrder.setTotalMaterialCost(new BigDecimal("5"));
        onlyOrder.setTotalLaborCost(new BigDecimal("5"));

        allOrders.add(onlyOrder);
    }

    @Override
    public List<Order> retrieveAllOrdersByDate(LocalDate orderDate) throws FlooringPersistenceException {
        LocalDate onlyOrderDate = onlyOrder.getOrderDate();
        if(orderDate.equals(onlyOrderDate)){
            return allOrders;
        } else {
            return null;
        }
    }

    @Override
    public Order retrieveOrderByDateAndId(LocalDate orderDate, int orderNumber) throws FlooringPersistenceException {
        if((orderDate.equals(onlyOrder.getOrderDate())) && (orderNumber == onlyOrder.getOrderNumber())){
            return onlyOrder;
        } else {
            return null;
        }
    }

    @Override
    public Order createOrder(LocalDate orderDate, Order orderObj) throws FlooringPersistenceException {
        return null;
    }

    @Override
    public void updateOrder(LocalDate orderDate, Order orderObj) throws FlooringPersistenceException {

    }

    @Override
    public void removeOrder(LocalDate orderDate, int orderNum) throws FlooringPersistenceException {

    }
}
