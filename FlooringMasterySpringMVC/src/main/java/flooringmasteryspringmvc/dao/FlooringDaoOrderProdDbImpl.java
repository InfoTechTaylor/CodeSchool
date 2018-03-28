package flooringmasteryspringmvc.dao;

import flooringmasteryspringmvc.dto.Order;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.List;

public class FlooringDaoOrderProdDbImpl implements FlooringDaoOrder {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Order> retrieveAllOrdersByDate(LocalDate orderDate) throws FlooringPersistenceException {
        return null;
    }

    @Override
    public Order retrieveOrderByDateAndId(LocalDate orderDate, String orderNumber) throws FlooringPersistenceException {
        return null;
    }

    @Override
    public Order createOrder(LocalDate orderDate, Order orderObj) throws FlooringPersistenceException {
        return null;
    }

    @Override
    public void updateOrder(LocalDate orderDate, Order orderObj) throws FlooringPersistenceException {

    }

    @Override
    public void removeOrder(LocalDate orderDate, String orderNum) throws FlooringPersistenceException {

    }

    @Override
    public void saveOrders() throws FlooringPersistenceException {

    }
}
