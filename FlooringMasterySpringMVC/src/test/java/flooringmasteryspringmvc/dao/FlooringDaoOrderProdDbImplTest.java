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

        List<Order> allOrders = dao.retrieveAllOrdersByDate(orderDate);
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
        Product newProduct = new Product("Carpet",new BigDecimal("2.25"),new BigDecimal("2.10"));
        Tax newTaxObj = new Tax();
        newTaxObj.setTaxId(1);
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
        dao.createOrder(orderDate, orderObj);

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
        dao.createOrder(orderDate, secondorderObj);
        //act
        List<Order> allOrdersForOneDate = dao.retrieveAllOrdersByDate(orderDate);
        //assert
        assertEquals(1, allOrdersForOneDate.size());

    }

    @Test
    public void testCreateAndRetrieveOrderByDateAndId() throws Exception{
        //act
        Product newProduct = new Product("Carpet",new BigDecimal("2.25"),new BigDecimal("2.10"));
        Tax newTaxObj = new Tax();
        newTaxObj.setTaxId(1);



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
        dao.createOrder(orderDate, secondorderObj);

        //act

        Order secondorderFromDao = dao.retrieveOrderByDateAndId(orderDate, secondorderObj.getOrderNumber());

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
        dao.createOrder(orderDate, orderObj);

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
        dao.createOrder(orderDate, orderObj);

        // act
        dao.removeOrder(orderDate, orderObj.getOrderNumber());

        //assert
        assertEquals(0, dao.retrieveAllOrdersByDate(orderDate).size());
    }
}