package dao;

import dto.Order;
import dto.Product;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

public class FlooringDaoOrderTest {

    FlooringDaoOrder orderDao;

    public FlooringDaoOrderTest(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        orderDao = ctx.getBean("prodOrderDao", FlooringDaoOrder.class);
    }

    @Before
    public void setUp() throws Exception{

    }

    @Test
    public void retrieveOrdersByDate() {
    }

    @Test
    public void retrieveOrderByDateAndId() {
    }

    @Test
    public void createOrder() {
    }

    @Test
    public void updateOrder() {
    }

    @Test
    public void removeOrder() {
    }
}