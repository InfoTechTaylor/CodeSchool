package flooringmasteryspringmvc.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class FlooringDaoOrderProdDbImplTest {

    FlooringDaoOrder dao;

    @Before
    public void setUp() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("prodOrderDao", FlooringDaoOrder.class);
    }

    @Test
    public void setJdbcTemplate() {
    }

    @Test
    public void retrieveAllOrdersByDate() {
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

    @Test
    public void saveOrders() {
    }
}