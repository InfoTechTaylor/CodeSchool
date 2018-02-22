package service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
    public void retrieveAllOrdersByDate() {
        //there is only one Order object in our FlooringDaoOrderStubImpl
        assertEquals(1, service.retrieveAllOrdersByDate(orderDate).size());
    }

    @Test
    public void retrieveAllProducts() {
        //there is only one Product object in our FlooringDaoProductStubImpl
        assertEquals(1, service.retrieveAllProducts().size());
    }

    @Test
    public void retrieveAllTaxes() {
        //there is only one tax object in our FlooringDaoTaxesStubImpl
        assertEquals(1, service.retrieveAllTaxes().size());
    }

    @Test
    public void processOrder() {
        service.processOrder(service.retrieveOrderByDateAndId(orderDate, 1));
    }

    @Test
    public void addOrder() {
        service.addOrder(service.retrieveOrderByDateAndId(orderDate, 1));
    }

    @Test
    public void retrieveOrderByDateAndId() {
        //there is only one Order object in our FlooringDaoOrderStubImpl
        assertNotNull(service.retrieveOrderByDateAndId(orderDate, 1));
        assertNull(service.retrieveOrderByDateAndId(orderDate, 2));
    }

    @Test
    public void removeOrder() {
        // remove the order in the stub with id of 1
        service.removeOrder(orderDate, 1);
        assertEquals(0, service.retrieveAllOrdersByDate(orderDate).size());
        assertNull(service.retrieveOrderByDateAndId(orderDate, 1));
    }

    @Test
    public void editOrder() {
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