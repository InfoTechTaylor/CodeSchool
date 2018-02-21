package dao;

import dto.Tax;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FlooringDaoTaxesTest {

    private FlooringDaoTaxes taxDao;

    public FlooringDaoTaxesTest(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        taxDao = ctx.getBean("taxesDao", FlooringDaoTaxes.class);
    }

    @Before
    public void setUp() throws Exception{
        List<Tax> allTaxObjects = taxDao.retrieveAllTaxes();
        for(Tax currentTaxObj : allTaxObjects){
            taxDao.removeTax(currentTaxObj);
        }
    }

    @Test
    public void retrieveTaxByState() throws Exception {
        // arrange
        Tax newTaxObj = new Tax("NH", new BigDecimal("5.75"));
        taxDao.createTax(newTaxObj);
        // act
        Tax taxObj = taxDao.retrieveTaxByState("NH");
        // assert
        assertEquals(newTaxObj, taxObj);
        assertNotNull(taxObj);
    }

    @Test
    public void retrieveAllTaxes() throws Exception{
        // arrange
        Tax newTaxObj = new Tax("NH", new BigDecimal("5.75"));
        taxDao.createTax(newTaxObj);
        // act
        List<Tax> allTaxes = taxDao.retrieveAllTaxes();
        // assert
        assertEquals(1,taxDao.retrieveAllTaxes().size());

    }

    @Test
    public void createTax() throws Exception{
        // arrange
        Tax newTaxObj = new Tax("NH", new BigDecimal("5.75"));
        // act
        taxDao.createTax(newTaxObj);
        // assert
        assertEquals(newTaxObj, taxDao.retrieveTaxByState("NH"));


    }

    @Test
    public void updateTax() throws FlooringPersistenceException{
        // arrange
        Tax originalTaxObject = new Tax("NH", new BigDecimal("5.75"));
        taxDao.createTax(originalTaxObject);
        Tax taxObjectToUpdate = taxDao.retrieveTaxByState("NH");
        taxObjectToUpdate.setTaxRate(new BigDecimal("6.05"));
        // act
        taxDao.updateTax(taxObjectToUpdate);

        // assert
        assertNotEquals(originalTaxObject, taxDao.retrieveTaxByState("NH"));

    }

    @Test
    public void removeTax() throws FlooringPersistenceException {
        //arrange
        Tax originalTaxObject = new Tax("NH", new BigDecimal("5.75"));
        taxDao.createTax(originalTaxObject);
        //act
        taxDao.removeTax(originalTaxObject);
        //assert
        assertEquals(0, taxDao.retrieveAllTaxes().size());

    }
}