package service;

import dao.FlooringDaoOrder;
import dao.FlooringDaoProducts;
import dao.FlooringDaoTaxes;
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
    public List<Order> retrieveAllOrdersByDate(LocalDate orderDate) {
        return null;
    }

    @Override
    public List<Order> retrieveAllProducts() {
        return null;
    }

    @Override
    public List<Tax> retrieveAllTaxes() {
        return null;
    }

    @Override
    public Order processOrder(Order orderObj) {
        return null;
    }

    @Override
    public Order addOrder(Order orderObj) {
        return null;
    }

    @Override
    public Order retrieveOrderByDateAndId(LocalDate orderDate, int orderNumber) {
        return null;
    }

    @Override
    public void removeOrder(LocalDate orderDate, int orderNumber) {

    }

    @Override
    public Order editOrder(Order orderObj) {
        return null;
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
