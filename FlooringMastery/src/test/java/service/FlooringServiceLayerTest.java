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
    public void addOrder() throws Exception{
        Order orderObj = service.retrieveOrderByDateAndId(orderDate, 1);
        service.addOrder(orderObj);
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
    public void saveAllOrders() {
        service.saveAllOrders();
    }

    @Test
    public void activateTrainingMode() {
        service.activateTrainingMode();
    }
}