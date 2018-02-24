package service;

import dao.FlooringDaoOrder;
import dao.FlooringDaoProducts;
import dao.FlooringDaoTaxes;
import dao.FlooringPersistenceException;
import dto.Order;
import dto.Product;
import dto.Tax;

import java.math.BigDecimal;
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
    public List<Order> retrieveAllOrdersByDate(LocalDate orderDate) throws FlooringPersistenceException, OrderNotFoundException, DateNotFoundException {
        validateDateExists(orderDate);
        validateOrdersExistForDate(orderDate);
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
            throw new TaxStateNotFoundException("Unable to find tax information for provided state. ");
        } else {
            orderObj.setTaxObject(retrieveTaxObject(state));
        }
        // check that product material exists
        String productMaterial = orderObj.getProductObject().getProductType();
        if(retrieveProductObject(productMaterial) == null){
            throw new ProductMaterialNotFoundException("Unable to find product material type provided. ");
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

        return daoOrder.createOrder(orderObj.getOrderDate(), orderObj);
    }

    @Override
    public Order retrieveOrderByDateAndId(LocalDate orderDate, int orderNumber) throws FlooringPersistenceException,
            OrderNotFoundException, DateNotFoundException {
        validateDateExists(orderDate);
        validateOrdersExistForDate(orderDate);
        return daoOrder.retrieveOrderByDateAndId(orderDate, orderNumber);
    }

    @Override
    public void removeOrder(LocalDate orderDate, int orderNumber) throws FlooringPersistenceException, OrderNotFoundException, DateNotFoundException {
        validateDateExists(orderDate);
        validateOrdersExistForDate(orderDate);
        daoOrder.removeOrder(orderDate, orderNumber);
    }

    @Override
    public Order editOrder(Order orderObj) throws FlooringPersistenceException, TaxStateNotFoundException, ProductMaterialNotFoundException {
        processOrder(orderObj);
        orderObj = calculateAndSetTotalMaterialCost(orderObj);
        orderObj = calculateAndSetTotalLaborCost(orderObj);
        orderObj = calculateAndSetTotalTax(orderObj);
        orderObj = calculateAndSetTotalCost(orderObj);
        daoOrder.updateOrder(orderObj.getOrderDate(), orderObj);
        return orderObj;
    }

    @Override
    public void saveAllOrders() throws FlooringPersistenceException {
        // write everything in the map to a file
        daoOrder.saveOrders();
    }

    public boolean activateTrainingMode() {
        return false;
    }

    private void validateDateExists(LocalDate dateToValidate) throws FlooringPersistenceException, DateNotFoundException {
        try {
            daoOrder.retrieveAllOrdersByDate(dateToValidate);
        } catch(FlooringPersistenceException e){
            throw new DateNotFoundException("Date does not exist.");
        }
    }

    private void validateOrdersExistForDate(LocalDate orderDate) throws FlooringPersistenceException, OrderNotFoundException {
        if(daoOrder.retrieveAllOrdersByDate(orderDate) == null){
            throw new OrderNotFoundException("No orders found for given date. ");
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
