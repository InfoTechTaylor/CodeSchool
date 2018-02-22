package service;

import dao.FlooringDaoOrder;
import dao.FlooringDaoProducts;
import dao.FlooringDaoTaxes;
import dao.FlooringPersistenceException;
import dto.Order;
import dto.Product;
import dto.Tax;

import java.time.LocalDate;
import java.util.List;

public class FlooringServiceLayerImpl implements FlooringServiceLayer {

    private FlooringDaoOrder daoOrder;
    private FlooringDaoTaxes daoTaxes;
    private FlooringDaoProducts daoProducts;

    public FlooringServiceLayerImpl(FlooringDaoOrder daoOrder, FlooringDaoTaxes daoTaxes, FlooringDaoProducts daoProducts) {
        this.daoOrder = daoOrder;
        this.daoTaxes = daoTaxes;
        this.daoProducts = daoProducts;
    }

    @Override
    public List<Order> retrieveAllOrdersByDate(LocalDate orderDate) throws FlooringPersistenceException {
        return daoOrder.retrieveAllOrdersByDate(orderDate);
    }

    @Override
    public List<Product> retrieveAllProducts() throws FlooringPersistenceException {
        return daoProducts.retrieveAllProducts();
    }

    @Override
    public List<Tax> retrieveAllTaxes() throws FlooringPersistenceException {
        return daoTaxes.retrieveAllTaxes();
    }

    @Override
    public Order processOrder(Order orderObj) {
        return null;
    }

    @Override
    public Order addOrder(Order orderObj) throws FlooringPersistenceException {
        return daoOrder.createOrder(orderObj.getOrderDate(), orderObj);
    }

    @Override
    public Order retrieveOrderByDateAndId(LocalDate orderDate, int orderNumber) throws FlooringPersistenceException {
        return daoOrder.retrieveOrderByDateAndId(orderDate, orderNumber);
    }

    @Override
    public void removeOrder(LocalDate orderDate, int orderNumber) throws FlooringPersistenceException {
        daoOrder.removeOrder(orderDate, orderNumber);
    }

    @Override
    public Order editOrder(Order orderObj) throws FlooringPersistenceException {
        daoOrder.updateOrder(orderObj.getOrderDate(), orderObj);
        return orderObj;
    }

    @Override
    public void saveAllOrders() {

    }

    public boolean activateTrainingMode() {
        return false;
    }

    private void validateOrdersExistForDate(LocalDate orderDate){

    }

    private Tax retrieveTaxObject(String taxState){
        return null;
    }

    private Product retrieveProductObject(String materialProductType){
        return null;
    }
}
