package dao;

import dto.Order;
import dto.Product;
import dto.Tax;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.*;

public class FlooringDaoOrderTest {

    private FlooringDaoOrder orderDao;
    private static LocalDate orderDate = LocalDate.parse("02202000", DateTimeFormatter.ofPattern("MMddyyyy"));

    public FlooringDaoOrderTest(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        orderDao = ctx.getBean("orderDao", FlooringDaoOrder.class);
    }

    @Before
    public void setUp() throws Exception{

        List<Order> allOrders = orderDao.retrieveAllOrdersByDate(orderDate);
        if(allOrders != null) {
            for (Order currentOrder : allOrders) {
                if (currentOrder != null) {
                    orderDao.removeOrder(orderDate, currentOrder);
                }
            }
        }


    } // end setup

    @Test
    public void retrieveOrdersByDate() throws Exception {
        //act
        Product newProduct = new Product("Carpet",new BigDecimal("2.25"),new BigDecimal("2.10"));
        Tax newTaxObj = new Tax("NH", new BigDecimal("5.75"));
        Order orderObj = new Order();
        orderObj.setTaxObject(newTaxObj);
        orderObj.setProductObject(newProduct);
        orderObj.setCustomerName("Taylor Lapointe");
        orderObj.setOrderDate(orderDate);
        orderObj.setArea(new BigDecimal("5"));
        orderObj.setTotalCost(new BigDecimal("5"));
        orderObj.setTotalTax(new BigDecimal("5"));
        orderObj.setTotalMaterialCost(new BigDecimal("5"));
        orderObj.setTotalLaborCost(new BigDecimal("5"));
        orderDao.createOrder(orderDate, orderObj);

        Order secondorderObj = new Order();
        secondorderObj.setTaxObject(newTaxObj);
//        secondorderObj.setOrderNumber(3);
        secondorderObj.setProductObject(newProduct);
        secondorderObj.setCustomerName("TaylorLapointe");
        secondorderObj.setOrderDate(orderDate);
        secondorderObj.setArea(new BigDecimal("5"));
        secondorderObj.setTotalCost(new BigDecimal("5"));
        secondorderObj.setTotalTax(new BigDecimal("5"));
        secondorderObj.setTotalMaterialCost(new BigDecimal("5"));
        secondorderObj.setTotalLaborCost(new BigDecimal("5"));
        orderDao.createOrder(orderDate, secondorderObj);
        //act
        List<Order> allOrdersForOneDate = orderDao.retrieveAllOrdersByDate(orderDate);
        //assert
        assertEquals(2, allOrdersForOneDate.size());

    }

    @Test
    public void testCreateAndRetrieveOrderByDateAndId() throws Exception{
        //act
        Product newProduct = new Product("Carpet",new BigDecimal("2.25"),new BigDecimal("2.10"));
        Tax newTaxObj = new Tax("NH", new BigDecimal("5.75"));
//        Order orderObj = new Order();
////        orderObj.setOrderNumber(2);
//        orderObj.setTaxObject(newTaxObj);
//        orderObj.setProductObject(newProduct);
//        orderObj.setCustomerName("TaylorLapointe");
//        orderObj.setOrderDate(orderDate);
//        orderObj.setArea(new BigDecimal("5"));
//        orderObj.setTotalCost(new BigDecimal("5"));
//        orderObj.setTotalTax(new BigDecimal("5"));
//        orderObj.setTotalMaterialCost(new BigDecimal("5"));
//        orderObj.setTotalLaborCost(new BigDecimal("5"));
//        orderDao.createOrder(orderDate, orderObj);


        Order secondorderObj = new Order();
        secondorderObj.setTaxObject(newTaxObj);
//        secondorderObj.setOrderNumber(3);
        secondorderObj.setProductObject(newProduct);
        secondorderObj.setCustomerName("TaylorLapointe");
        secondorderObj.setOrderDate(orderDate);
        secondorderObj.setArea(new BigDecimal("5"));
        secondorderObj.setTotalCost(new BigDecimal("5"));
        secondorderObj.setTotalTax(new BigDecimal("5"));
        secondorderObj.setTotalMaterialCost(new BigDecimal("5"));
        secondorderObj.setTotalLaborCost(new BigDecimal("5"));
        orderDao.createOrder(orderDate, secondorderObj);

        //act

        Order secondorderFromDao = orderDao.retrieveOrderByDateAndId(orderDate, secondorderObj.getOrderNumber());

        // assert

        assertEquals(secondorderObj, secondorderFromDao);
    }

    @Test
    public void updateOrder() throws Exception{
        //act
        Product newProduct = new Product("Carpet",new BigDecimal("2.25"),new BigDecimal("2.10"));
        Tax newTaxObj = new Tax("NH", new BigDecimal("5.75"));
        Order orderObj = new Order();
        orderObj.setTaxObject(newTaxObj);
        orderObj.setProductObject(newProduct);
        orderObj.setCustomerName("Taylor Lapointe");
        orderObj.setOrderDate(orderDate);
        orderObj.setArea(new BigDecimal("5"));
        orderObj.setTotalCost(new BigDecimal("5"));
        orderObj.setTotalTax(new BigDecimal("5"));
        orderObj.setTotalMaterialCost(new BigDecimal("5"));
        orderObj.setTotalLaborCost(new BigDecimal("5"));
        orderDao.createOrder(orderDate, orderObj);

        //act
        Order orderToUpdate = orderDao.retrieveOrderByDateAndId(orderDate, orderObj.getOrderNumber());
        orderToUpdate.setTotalCost(new BigDecimal("3000.00"));
        orderToUpdate.setCustomerName("Brett Hicklin");
        orderDao.updateOrder(orderDate, orderToUpdate);

        // assert
        assertNotEquals(orderObj, orderToUpdate);


    }

    @Test
    public void removeOrder() throws Exception{
        //act
        Product newProduct = new Product("Carpet",new BigDecimal("2.25"),new BigDecimal("2.10"));
        Tax newTaxObj = new Tax("NH", new BigDecimal("5.75"));
        Order orderObj = new Order();
        orderObj.setTaxObject(newTaxObj);
        orderObj.setProductObject(newProduct);
        orderObj.setCustomerName("Taylor Lapointe");
        orderObj.setOrderDate(orderDate);
        orderObj.setArea(new BigDecimal("5"));
        orderObj.setTotalCost(new BigDecimal("5"));
        orderObj.setTotalTax(new BigDecimal("5"));
        orderObj.setTotalMaterialCost(new BigDecimal("5"));
        orderObj.setTotalLaborCost(new BigDecimal("5"));
        orderDao.createOrder(orderDate, orderObj);

        // act
        orderDao.removeOrder(orderDate, orderObj);

        //assert
        assertEquals(0, orderDao.retrieveAllOrdersByDate(orderDate).size());
    }
}