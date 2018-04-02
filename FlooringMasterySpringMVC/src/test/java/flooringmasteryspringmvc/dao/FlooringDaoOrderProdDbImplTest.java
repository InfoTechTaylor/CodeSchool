package flooringmasteryspringmvc.dao;

import flooringmasteryspringmvc.dto.Order;
import flooringmasteryspringmvc.dto.Product;
import flooringmasteryspringmvc.dto.Tax;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.*;

public class FlooringDaoOrderProdDbImplTest {

    FlooringDaoOrder dao;
    private static LocalDate orderDate = LocalDate.parse("02202000", DateTimeFormatter.ofPattern("MMddyyyy"));


    @Before
    public void setUp() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("prodOrderDao", FlooringDaoOrder.class);

        List<Order> allOrders = dao.retrieveAllOrders();
        if(allOrders != null) {
            for (Order currentOrder : allOrders) {
                if (currentOrder != null) {
                    dao.removeOrder(orderDate, currentOrder.getOrderNumber());
                }
            }
        }
    }


    @Test
    public void retrieveOrdersByDate() throws Exception {
        //act
        Order orderObj = createTestOrder();

        //act
        List<Order> allOrdersForOneDate = dao.retrieveAllOrdersByDate(orderDate);
        //assert
        assertEquals(1, allOrdersForOneDate.size());

    }

    @Test
    public void testCreateAndRetrieveOrderByDateAndId() throws Exception{
        //act
        Order orderObj = createTestOrder();

        //act

        Order secondorderFromDao = dao.retrieveOrderByDateAndId(orderDate, orderObj.getOrderNumber());

        // assert

        assertEquals(orderObj.getCustomerName(), secondorderFromDao.getCustomerName());
    }

    private Order createTestOrder() throws FlooringPersistenceException {
        Product newProduct = new Product();
        newProduct.setProductId(1);
        Tax newTaxObj = new Tax();
        newTaxObj.setTaxId(1);
        Order orderObj = new Order();
        orderObj.setTaxObject(newTaxObj);
        orderObj.setProductObject(newProduct);
        orderObj.setCustomerName("TaylorLapointe");
        orderObj.setOrderDate(orderDate);
        orderObj.setArea(new BigDecimal("5"));
        orderObj.setTotalCost(new BigDecimal("5"));
        orderObj.setTotalTax(new BigDecimal("5"));
        orderObj.setTotalMaterialCost(new BigDecimal("5"));
        orderObj.setTotalLaborCost(new BigDecimal("5"));
        dao.createOrder(orderDate, orderObj);
        return orderObj;
    }

    @Test
    public void updateOrder() throws Exception{
        //act
        Order orderObj = createTestOrder();

        //act
        Order orderToUpdate = dao.retrieveOrderByDateAndId(orderDate, orderObj.getOrderNumber());
        orderToUpdate.setCustomerName("Brett Hicklin");
        dao.updateOrder(orderDate, orderToUpdate);

        // assert
        assertNotEquals("Taylor Lapointe", orderToUpdate.getCustomerName());



    }

    @Test
    public void removeOrder() throws Exception{
        //act
        Order orderObj = createTestOrder();

        // act
        dao.removeOrder(orderDate, orderObj.getOrderNumber());

        //assert
        assertEquals(0, dao.retrieveAllOrdersByDate(orderDate).size());
    }
}