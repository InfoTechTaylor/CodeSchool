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
    public List<Order> retrieveAllOrdersByDate(LocalDate orderDate) throws FlooringPersistenceException, OrderNotFoundException {
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

        return daoOrder.createOrder(orderObj.getOrderDate(), orderObj);
    }

    @Override
    public Order retrieveOrderByDateAndId(LocalDate orderDate, int orderNumber) throws FlooringPersistenceException,
            OrderNotFoundException {
        validateOrdersExistForDate(orderDate);
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

    // 0 order number - int
    // 1 customer name - String
    // 2 State, - String
    // 3 TaxRate, - BD
    // 4 ProductType, - String
    // 5 Area, - BD
    // 6 Material - CostPerSquareFoot, - BD
    // 7 LaborCost - PerSquareFoot,- BD
    // 8 total MaterialCost, -BD
    // 9 total LaborCost,-BD
    // 10 total Tax, -BD
    // 11 total Cost - BD

    private Order calculateAndSetTotalMaterialCost(Order orderToUpdate){
        // material costPerSquareFoot x area
        BigDecimal area = orderToUpdate.getArea();
        BigDecimal materialCostPerSquareFoot = orderToUpdate.getProductObject().getMaterialCostPerSquareFoot();
        BigDecimal totalMaterialCost = area.multiply(materialCostPerSquareFoot);
        orderToUpdate.setTotalMaterialCost(totalMaterialCost);
        return orderToUpdate;
    }

    private Order calculateAndSetTotalLaborCost(Order orderToUpdate) {

        return null;
    }

    private Order calculateAndSetTotalTax(Order orderToUpdate) {
        return null;
    }

    private Order calculateAndSetTotalCost(Order ordertToUpdate) {
        return null;
    }
}
