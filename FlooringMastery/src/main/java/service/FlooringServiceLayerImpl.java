package service;

import dao.*;
import dto.Order;
import dto.Product;
import dto.Tax;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class FlooringServiceLayerImpl implements FlooringServiceLayer {

    private FlooringDaoOrder daoOrder;
    private FlooringDaoTaxes daoTaxes;
    private FlooringDaoProducts daoProducts;
    private ConfigDao daoConfig;
    //private boolean isModeTraining = false;

    public FlooringServiceLayerImpl(FlooringDaoOrder daoOrder, FlooringDaoTaxes daoTaxes, FlooringDaoProducts daoProducts,
                                    ConfigDao daoConfig) {
        this.daoOrder = daoOrder;
        this.daoTaxes = daoTaxes;
        this.daoProducts = daoProducts;
        this.daoConfig = daoConfig;
    }

    @Override
    public List<Order> retrieveAllOrdersByDate(LocalDate orderDate) throws FlooringPersistenceException, OrderNotFoundException, DateNotFoundException {
        validateOrdersExistForDate(orderDate, null);
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
    public Order processOrder(Order orderObj) throws FlooringPersistenceException,
            TaxStateNotFoundException, ProductMaterialNotFoundException {

        // should we validate the date

        // check that tax state exists
        String state = orderObj.getTaxObject().getState();
        if(retrieveTaxObject(state) == null){
            throw new TaxStateNotFoundException("Sorry, we do not support the state you provided. Please try again with one of our supported states. ");
        } else {
            orderObj.setTaxObject(retrieveTaxObject(state));
        }
        // check that product material exists
        String productMaterial = orderObj.getProductObject().getProductType();
        if(retrieveProductObject(productMaterial) == null){
            throw new ProductMaterialNotFoundException("Sorry, we do not offer the product you provided. Please try again with one of our supported states. ");
        } else {
            orderObj.setProductObject(retrieveProductObject(productMaterial));
        }

        return orderObj;
    }

    @Override
    public Order addOrder(Order orderObj) throws FlooringPersistenceException, TaxStateNotFoundException,
                                            ProductMaterialNotFoundException {
        // validate input for orderObj (state and material type)
        processOrder(orderObj);
        // calculate and set totals on orderObj
        orderObj = calculateAndSetTotalMaterialCost(orderObj);
        orderObj = calculateAndSetTotalLaborCost(orderObj);
        orderObj = calculateAndSetTotalTax(orderObj);
        orderObj = calculateAndSetTotalCost(orderObj);
        if(orderObj.getOrderNumber() == null) {
            orderObj.setOrderNumber(daoConfig.generateOrderNumber());
        }

        return daoOrder.createOrder(orderObj.getOrderDate(), orderObj);
    }

    @Override
    public Order retrieveOrderByDateAndId(LocalDate orderDate, String orderNumber) throws FlooringPersistenceException,
            OrderNotFoundException {
        validateOrdersExistForDate(orderDate, orderNumber);
        try{
            return daoOrder.retrieveOrderByDateAndId(orderDate, orderNumber);
        } catch(FlooringPersistenceException e){
            throw new OrderNotFoundException("No orders exist with provided order ID. ");
        }

    }

    @Override
    public void removeOrder(LocalDate orderDate, String orderNumber) throws FlooringPersistenceException, OrderNotFoundException, DateNotFoundException {

        validateOrdersExistForDate(orderDate, orderNumber);
        daoOrder.removeOrder(orderDate, orderNumber);
    }

    @Override
    public Order editOrder(LocalDate originalDate, Order orderObj) throws FlooringPersistenceException, TaxStateNotFoundException, ProductMaterialNotFoundException, OrderNotFoundException, DateNotFoundException {
        //validateOrdersExistForDate(orderObj.getOrderDate());
        orderObj = processOrder(orderObj);
        orderObj = calculateAndSetTotalMaterialCost(orderObj);
        orderObj = calculateAndSetTotalLaborCost(orderObj);
        orderObj = calculateAndSetTotalTax(orderObj);
        orderObj = calculateAndSetTotalCost(orderObj);

        // check if originalDate is same as date of object
        if(!originalDate.equals(orderObj.getOrderDate())){
            removeOrder(originalDate, orderObj.getOrderNumber());
            addOrder(orderObj);
        } else {
            daoOrder.updateOrder(orderObj.getOrderDate(), orderObj);
        }
        return orderObj;
    }

    @Override
    public void saveAllOrders() throws FlooringPersistenceException {
        // write everything in the map to a file
        daoOrder.saveOrders();
    }

    public boolean activateTrainingMode(boolean userSelection) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        if(userSelection){

            daoOrder = ctx.getBean("trainingOrderDao", FlooringDaoOrder.class);
            return true;
        } else {
            daoOrder = ctx.getBean("prodOrderDao", FlooringDaoOrder.class);
            return false;
        }

    }

    private void validateOrdersExistForDate(LocalDate orderDate, String orderNumber) throws FlooringPersistenceException, OrderNotFoundException {
//        if(daoOrder.retrieveAllOrdersByDate(orderDate) == null){
        if(daoOrder.retrieveAllOrdersByDate(orderDate).size() == 0){
            throw new OrderNotFoundException("No orders found for given date. ");
        }

        if(orderNumber != null) {
            if (daoOrder.retrieveOrderByDateAndId(orderDate, orderNumber) == null) {
                throw new OrderNotFoundException("No orders with that number exist for that date.");
            }
        }

    }

    private Tax retrieveTaxObject(String taxState) throws FlooringPersistenceException {
        return daoTaxes.retrieveTaxByState(taxState);
    }

    private Product retrieveProductObject(String materialProductType) throws FlooringPersistenceException {
        return daoProducts.retrieveProductByMaterial(materialProductType);
    }

    private Order calculateAndSetTotalMaterialCost(Order orderToUpdate){
        // material costPerSquareFoot x area
        BigDecimal area = orderToUpdate.getArea();
        BigDecimal materialCostPerSquareFoot = orderToUpdate.getProductObject().getMaterialCostPerSquareFoot();
        BigDecimal totalMaterialCost = area.multiply(materialCostPerSquareFoot);
        orderToUpdate.setTotalMaterialCost(totalMaterialCost);
        return orderToUpdate;
    }

    private Order calculateAndSetTotalLaborCost(Order orderToUpdate) {
        // labor cost per square foot x area
        BigDecimal area = orderToUpdate.getArea();
        BigDecimal laborCostPerSquareFoot = orderToUpdate.getProductObject().getLaborCostPerSquareFoot();
        BigDecimal totalLaborCost = area.multiply(laborCostPerSquareFoot);
        orderToUpdate.setTotalLaborCost(totalLaborCost);
        return orderToUpdate;
    }

    private Order calculateAndSetTotalTax(Order orderToUpdate) {
        // tax rate times (total material cost + total labor cost)
        BigDecimal totalMaterialCost = orderToUpdate.getTotalMaterialCost();
        BigDecimal totalLaborCost = orderToUpdate.getTotalLaborCost();
        BigDecimal taxRate = (orderToUpdate.getTaxObject().getTaxRate()).movePointLeft(2);
        BigDecimal sumCost = totalLaborCost.add(totalMaterialCost);
        BigDecimal totalTax = taxRate.multiply(sumCost);
        orderToUpdate.setTotalTax(totalTax);
        return orderToUpdate;
    }

    private Order calculateAndSetTotalCost(Order orderToUpdate) {
        // total cost = total labor cost + total material cost + total tax
        BigDecimal totalLaborCost = orderToUpdate.getTotalLaborCost();
        BigDecimal totalMaterialCost = orderToUpdate.getTotalMaterialCost();
        BigDecimal totalTax = orderToUpdate.getTotalTax();
        BigDecimal totalCost = totalLaborCost.add(totalMaterialCost).add(totalTax);
        orderToUpdate.setTotalCost(totalCost);
        return orderToUpdate;
    }

}
