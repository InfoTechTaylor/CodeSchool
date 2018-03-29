package flooringmasteryspringmvc.dao;

import flooringmasteryspringmvc.dto.Order;
import flooringmasteryspringmvc.dto.Product;
import flooringmasteryspringmvc.dto.Tax;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class FlooringDaoOrderProdDbImpl implements FlooringDaoOrder {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_ORDER
            = "insert into customer_order "
            + "(orderDate, customerName, taxId, productId, area, totalMaterialCost, totalLaborCost, totalTax, totalCost) "
            + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_SELECT_ORDER_BY_ID
            = "select from customer_order where orderNumber = ?";

    private static final String SQL_UPDATE_ORDER
            = "update customer_order set orderDate = ?, customerName = ?, taxId = ?, productId = ?, "
            + "area = ?, totalMaterialCost = ?, totalLaborCost = ?, totalTax = ?, totalCost = ? "
            + "where orderNumber = ?";

    private static final String SQL_DELETE_ORDER
            = "delete from customer_order where orderNumber = ?";

    private static final String SQL_SELECT_ALL_ORDERS
            = "select * from customer_order";

    private static final String SQL_SELECT_ORDER_BY_DATE
            = "select from customer_order where orderDate = ?";



    @Override
    public List<Order> retrieveAllOrdersByDate(LocalDate orderDate) throws FlooringPersistenceException {
        Date date = Date.valueOf(orderDate);

        return jdbcTemplate.query(SQL_SELECT_ORDER_BY_DATE,
                new orderMapper(),
                date);
    }

    @Override
    public Order retrieveOrderByDateAndId(LocalDate orderDate, String orderNumber) throws FlooringPersistenceException {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ORDER_BY_ID,
                    new orderMapper(),
                    orderNumber);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Order createOrder(LocalDate orderDate, Order orderObj) throws FlooringPersistenceException {
        Date date = Date.valueOf(orderObj.getOrderDate());

        jdbcTemplate.update(SQL_INSERT_ORDER,
                date,
                orderObj.getCustomerName(),
                orderObj.getTaxObject().getTaxId(),
                orderObj.getProductObject().getProductId(),
                orderObj.getArea(),
                orderObj.getTotalMaterialCost(),
                orderObj.getTotalLaborCost(),
                orderObj.getTotalTax(),
                orderObj.getTotalCost());

        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class);
        orderObj.setOrderNumber(Integer.toString(newId));

        return orderObj;
    }

    @Override
    public void updateOrder(LocalDate orderDate, Order orderObj) throws FlooringPersistenceException {
        jdbcTemplate.update(SQL_UPDATE_ORDER,
                orderObj.getCustomerName(),
                orderObj.getTaxObject().getTaxId(),
                orderObj.getProductObject().getProductId(),
                orderObj.getArea(),
                orderObj.getTotalMaterialCost(),
                orderObj.getTotalLaborCost(),
                orderObj.getTotalTax(),
                orderObj.getTotalCost(),
                orderObj.getOrderNumber());
    }

    @Override
    public void removeOrder(LocalDate orderDate, String orderNum) throws FlooringPersistenceException {
        jdbcTemplate.update(SQL_DELETE_ORDER,
                Integer.parseInt(orderNum));
    }

    @Override
    public void saveOrders() throws FlooringPersistenceException {

    }

    private static final class orderMapper implements RowMapper<Order> {

        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
            Order order = new Order();
            // lazy loading setting up empty object to just add ids, will update in service layer
            Product product = new Product();
            Tax tax = new Tax();

            order.setOrderNumber(Integer.toString(rs.getInt("orderNumber")));
            product.setProductId(rs.getInt("productId"));
            tax.setTaxId(rs.getInt("taxId"));
            order.setProductObject(product);
            order.setTaxObject(tax);
            order.setOrderDate(rs.getDate("OrderDate").toLocalDate());
            order.setCustomerName(rs.getString("customerName"));
            order.setArea(rs.getBigDecimal("area"));
            order.setTotalMaterialCost(rs.getBigDecimal("totalMaterialCost"));
            order.setTotalLaborCost(rs.getBigDecimal("totalLaborCost"));
            order.setTotalTax(rs.getBigDecimal("totalTax"));
            order.setTotalCost(rs.getBigDecimal("totalCost"));

            return order;
        }
    }
}
