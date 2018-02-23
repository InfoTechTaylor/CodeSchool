package service;

import dto.Order;
import dto.Product;
import dto.Tax;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class FlooringServiceLayerTest {

    private FlooringServiceLayer service;
    private static LocalDate orderDate = LocalDate.parse("02202000", DateTimeFormatter.ofPattern("MMddyyyy"));

    public FlooringServiceLayerTest(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("service", FlooringServiceLayer.class);
    }

    @Test
    public void retrieveAllOrdersByDate() throws Exception {
        //there is only one Order object in our FlooringDaoOrderStubImpl
        assertEquals(1, service.retrieveAllOrdersByDate(orderDate).size());
    }

    @Test
    public void retrieveAllProducts() throws Exception{
        //there is only one Product object in our FlooringDaoProductStubImpl
        assertEquals(1, service.retrieveAllProducts().size());
    }

    @Test
    public void retrieveAllTaxes() throws Exception{
        //there is only one tax object in our FlooringDaoTaxesStubImpl
        assertEquals(1, service.retrieveAllTaxes().size());
    }

    @Test
    public void processOrder() throws Exception{
        service.processOrder(service.retrieveOrderByDateAndId(orderDate, 1));
    }

    @Test
    public void testProcessAllOrderTaxProductExists() throws Exception{
        Order orderToValidate = service.addOrder(service.retrieveOrderByDateAndId(orderDate, 1));
        // passes if it doesn't throw exception
    }

    @Test
    public void testProcessOrderTaxStateDoesNotExist() throws Exception{
        // arrange
        Order orderToValidate = service.addOrder(service.retrieveOrderByDateAndId(orderDate, 1));
        Tax taxObj = new Tax("PA", new BigDecimal(3.2));
        orderToValidate.setTaxObject(taxObj);

        //act
        try{
            service.addOrder(orderToValidate);
            fail("Expected TaxStateNotFoundException was not thrown.");
        } catch (TaxStateNotFoundException e){
            return;
        }

    }

    @Test
    public void testProcessOrderProductMaterialDoesNotExist() throws Exception{
        // arrange
        Order orderToValidate = service.addOrder(service.retrieveOrderByDateAndId(orderDate, 1));
        Product productObj = new Product("Tile",new BigDecimal("2.25"),new BigDecimal("2.10"));
        orderToValidate.setProductObject(productObj);

        //act
        try{
            service.addOrder(orderToValidate);
            fail("Expected ProductMaterialNotFoundException was not thrown.");
        } catch (ProductMaterialNotFoundException e){
            return;
        }
    }

    @Test
    public void testValidateOrdersDoNotExistForDate() throws Exception {
        // arrange
        Order orderToValidate = service.addOrder(service.retrieveOrderByDateAndId(orderDate, 1));
        LocalDate orderDate = LocalDate.parse("02212000", DateTimeFormatter.ofPattern("MMddyyyy"));

        //act
        try{
            service.retrieveAllOrdersByDate(orderDate);
            fail("Expected OrderNotFoundException was not thrown.");
        } catch (OrderNotFoundException e){
            return;
        }
    }

    @Test
    public void testValidateDateExists() throws Exception {
        LocalDate orderDate = LocalDate.parse("02212000", DateTimeFormatter.ofPattern("MMddyyyy"));
        try{
            service.retrieveAllOrdersByDate(orderDate);
            fail("Expected DateNotFoundException never thrown.");
        } catch (DateNotFoundException e){
            return;
        }
    }

    @Test
    public void addOrder() throws Exception{
        Order orderObj = service.retrieveOrderByDateAndId(orderDate, 1);
        service.addOrder(orderObj);
    }


    @Test
    public void testCalculateAndSetTotalMaterialCost() throws Exception{
        Order orderToValidate = service.addOrder(service.retrieveOrderByDateAndId(orderDate, 1));
        assertEquals(new BigDecimal("11.25"), orderToValidate.getTotalMaterialCost());
    }

    @Test
    public void testCalculateAndSetTotalLaborCost() throws Exception{
        Order orderToValidate = service.addOrder(service.retrieveOrderByDateAndId(orderDate, 1));
        assertEquals(new BigDecimal("10.50"), orderToValidate.getTotalLaborCost());
    }

    @Test
    public void testCalculateAndSetTotalTax() throws Exception{
        Order orderToValidate = service.addOrder(service.retrieveOrderByDateAndId(orderDate, 1));
        assertEquals(new BigDecimal(".76125"), orderToValidate.getTotalTax());
    }

    @Test
    public void testCalculateAndSetTotalCost() throws Exception{
        Order orderToValidate = service.addOrder(service.retrieveOrderByDateAndId(orderDate, 1));
        assertEquals(new BigDecimal("22.51125"), orderToValidate.getTotalCost());
    }

    @Test
    public void retrieveOrderByDateAndId() throws Exception{
        //there is only one Order object in our FlooringDaoOrderStubImpl
        assertNotNull(service.retrieveOrderByDateAndId(orderDate, 1));
        assertNull(service.retrieveOrderByDateAndId(orderDate, 2));
    }

    @Test
    public void removeOrder() throws Exception {
        // remove the order in the stub with id of 1
        service.removeOrder(orderDate, 2);
    }

    @Test
    public void editOrder() throws Exception{
        service.editOrder(service.retrieveOrderByDateAndId(orderDate, 1));
    }

    @Test
    public void saveAllOrders() throws Exception{
        service.saveAllOrders();
    }

    @Test
    public void activateTrainingMode() {
        service.activateTrainingMode();
    }
}