package dao;

import dto.Product;
import dto.Tax;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class FlooringDaoProductsTest {

    private FlooringDaoProducts daoProducts;

    public FlooringDaoProductsTest(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        daoProducts = ctx.getBean("productsDao", FlooringDaoProducts.class);
    }

    @Test
    public void retrieveProductByMaterial() throws Exception{
        List<Product> allProducts = daoProducts.retrieveAllProducts();
        for(Product currentProduct : allProducts){
            daoProducts.removeProduct(currentProduct);
        }
    }

    @Test
    public void retrieveAllProducts() throws Exception{
        // arrange
        Product newProduct = new Product("Carpet",new BigDecimal("2.25"),new BigDecimal("2.10"));
        daoProducts.createProduct(newProduct);
        // act
        List<Product> allProducts = daoProducts.retrieveAllProducts();
        // assert
        assertEquals(1,daoProducts.retrieveAllProducts().size());
    }

    @Test
    public void createProduct() throws Exception{
        // arrange
        Product newProduct = new Product("Carpet",new BigDecimal("2.25"),new BigDecimal("2.10"));
        // act
        daoProducts.createProduct(newProduct);
        assertEquals(newProduct, daoProducts.retrieveProductByMaterial("Carpet"));
    }

    @Test
    public void updateProduct() throws Exception{
        // arrange
        Product newProduct = new Product("Carpet",new BigDecimal("2.25"),new BigDecimal("2.10"));
        daoProducts.createProduct(newProduct);
        Product productObjectToUpdate = daoProducts.retrieveProductByMaterial("Carpet");
        productObjectToUpdate.setLaborCostPerSquareFoot(new BigDecimal("5.43"));

        // act
        daoProducts.updateProduct(productObjectToUpdate);

        // assert
        assertNotEquals(newProduct, daoProducts.retrieveProductByMaterial("Carpet"));
    }

    @Test
    public void removeProduct() throws Exception{
        Product newProduct = new Product("Carpet",new BigDecimal("2.25"),new BigDecimal("2.10"));
        daoProducts.createProduct(newProduct);

        daoProducts.removeProduct(newProduct);

        assertEquals(0, daoProducts.retrieveAllProducts().size());
    }
}