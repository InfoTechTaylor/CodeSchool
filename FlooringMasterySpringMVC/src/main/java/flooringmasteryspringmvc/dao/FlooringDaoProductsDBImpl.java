package flooringmasteryspringmvc.dao;

import flooringmasteryspringmvc.dto.Product;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FlooringDaoProductsDBImpl implements FlooringDaoProducts{

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_SELECT_PRODUCT_BY_MATERIAL
            = "select * from product where productType = ?";

    private static final String SQL_INSERT_PRODUCT
            = "insert into product (productType, materialCostPerSquareFoot, laborCostPerSquareFoot) "
            + "values (?, ?, ?)";

    private static final String SQL_DELETE_PRODUCT
            = "delete from product where productId = ?";

    private static final String SQL_UPDATE_PRODUCT
            = "update product set productType = ?, materialCostPerSquareFoot = ?, laborCostPerSquareFoot = ?"
            + "where productId = ?";

    private static final String SQL_SELECT_ALL_PRODUCTS
            = "select * from product";

    @Override
    public Product retrieveProductByMaterial(String material) throws FlooringPersistenceException {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_PRODUCT_BY_MATERIAL,
                    new productMapper(),
                    material);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Product> retrieveAllProducts() throws FlooringPersistenceException {
        return jdbcTemplate.query(SQL_SELECT_ALL_PRODUCTS,
                new productMapper());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Product createProduct(Product productObj) throws FlooringPersistenceException {
        jdbcTemplate.update(SQL_INSERT_PRODUCT,
                productObj.getProductType(),
                productObj.getMaterialCostPerSquareFoot(),
                productObj.getLaborCostPerSquareFoot());

        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class);

        productObj.setProductId(newId);
        return productObj;
    }

    @Override
    public void updateProduct(Product productObj) throws FlooringPersistenceException {
        jdbcTemplate.update(SQL_UPDATE_PRODUCT,
                productObj.getProductType(),
                productObj.getMaterialCostPerSquareFoot(),
                productObj.getLaborCostPerSquareFoot(),
                productObj.getProductId());
    }

    @Override
    public void removeProduct(Product productObj) throws FlooringPersistenceException {
        jdbcTemplate.update(SQL_DELETE_PRODUCT,
                productObj.getProductId());
    }

    private static final class productMapper implements RowMapper<Product>{

        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product();
            product.setProductId(rs.getInt("productId"));
            product.setProductType(rs.getString("productType"));
            product.setMaterialCostPerSquareFoot(rs.getBigDecimal("materialCostPerSquareFoot"));
            product.setLaborCostPerSquareFoot(rs.getBigDecimal("laborCostPerSquareFoot"));
            return product;
        }
    }
}

