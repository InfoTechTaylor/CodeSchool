package flooringmasteryspringmvc.dao;

import flooringmasteryspringmvc.dto.Tax;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class FlooringDaoTaxesDBImplTest {

    FlooringDaoTaxes dao;

    @Before
    public void setUp() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("taxesDao", FlooringDaoTaxes.class);

        List<Tax> allTaxes = dao.retrieveAllTaxes();
        for(Tax currentTax : allTaxes ){
            dao.removeTax(currentTax);
        }
    }

    @Test
    public void setJdbcTemplate() {
    }

    @Test
    public void retrieveTaxByState() throws Exception {
        // arrange
        Tax newTaxObj = new Tax("NH", new BigDecimal("5.75"));
        dao.createTax(newTaxObj);
        // act
        Tax taxObj = dao.retrieveTaxByState("NH");
        // assert
        assertEquals(newTaxObj.getState(), taxObj.getState());
        assertEquals(newTaxObj.getTaxRate(), taxObj.getTaxRate());
        assertNotNull(taxObj);
    }

    @Test
    public void retrieveAllTaxes() throws Exception{
        // arrange
        Tax newTaxObj = new Tax("NH", new BigDecimal("5.75"));
        dao.createTax(newTaxObj);
        // act
        List<Tax> allTaxes = dao.retrieveAllTaxes();
        // assert
        assertEquals(1,dao.retrieveAllTaxes().size());

    }

    @Test
    public void createTax() throws Exception{
        // arrange
        Tax newTaxObj = new Tax("NH", new BigDecimal("5.75"));
        // act
        dao.createTax(newTaxObj);
        // assert
        assertEquals(newTaxObj, dao.retrieveTaxByState("NH"));


    }

    @Test
    public void updateTax() throws FlooringPersistenceException{
        // arrange
        Tax originalTaxObject = new Tax("NH", new BigDecimal("5.75"));
        dao.createTax(originalTaxObject);
        Tax taxObjectToUpdate = dao.retrieveTaxByState("NH");
        taxObjectToUpdate.setTaxRate(new BigDecimal("6.05"));
        // act
        dao.updateTax(taxObjectToUpdate);

        // assert
        assertNotEquals(originalTaxObject, dao.retrieveTaxByState("NH"));

    }

    @Test
    public void removeTax() throws FlooringPersistenceException {
        //arrange
        Tax originalTaxObject = new Tax("NH", new BigDecimal("5.75"));
        dao.createTax(originalTaxObject);
        //act
        dao.removeTax(originalTaxObject);
        //assert
        assertEquals(0, dao.retrieveAllTaxes().size());

    }
}