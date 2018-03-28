package flooringmasteryspringmvc.dao;

import flooringmasteryspringmvc.dto.Product;
import flooringmasteryspringmvc.dto.Tax;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class FlooringDaoProductsDBImplTest {

    FlooringDaoProducts dao;

    @Before
    public void setUp() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("productsDao", FlooringDaoProducts.class);

        List<Product> allProducts = dao.retrieveAllProducts();
        for(Product currentProduct : allProducts ){
            dao.removeProduct(currentProduct);
        }
    }

    @Test
    public void setJdbcTemplate() {
    }

    @Test
    public void retrieveProductByMaterial() throws Exception{
        List<Product> allProducts = dao.retrieveAllProducts();
        for(Product currentProduct : allProducts){
            dao.removeProduct(currentProduct);
        }
    }

    @Test
    public void retrieveAllProducts() throws Exception{
        // arrange
        Product newProduct = new Product("Carpet",new BigDecimal("2.25"),new BigDecimal("2.10"));
        dao.createProduct(newProduct);
        // act
        List<Product> allProducts = dao.retrieveAllProducts();
        // assert
        assertEquals(1,dao.retrieveAllProducts().size());
    }

    @Test
    public void createProduct() throws Exception{
        // arrange
        Product newProduct = new Product("Carpet",new BigDecimal("2.25"),new BigDecimal("2.10"));
        // act
        Product fromDao = dao.createProduct(newProduct);
        assertEquals(newProduct.getProductType(), fromDao.getProductType());
    }

    @Test
    public void updateProduct() throws Exception{
        // arrange
        Product newProduct = new Product("Carpet",new BigDecimal("2.25"),new BigDecimal("2.10"));
        dao.createProduct(newProduct);
        Product productObjectToUpdate = dao.retrieveProductByMaterial("Carpet");
        productObjectToUpdate.setLaborCostPerSquareFoot(new BigDecimal("5.43"));

        // act
        dao.updateProduct(productObjectToUpdate);
        Product fromDao = dao.retrieveProductByMaterial("Carpet");

        // assert
        assertEquals(newProduct.getProductType(), fromDao.getProductType());
    }

    @Test
    public void removeProduct() throws Exception{
        Product newProduct = new Product("Carpet",new BigDecimal("2.25"),new BigDecimal("2.10"));
        dao.createProduct(newProduct);

        dao.removeProduct(newProduct);

        assertEquals(0, dao.retrieveAllProducts().size());
    }
}