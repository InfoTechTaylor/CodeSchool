package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dto.VendingMachineChange;
import com.sg.vendingmachinespringmvc.dto.VendingMachineItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

public class VendingMachineServiceLayerTest {

//    private VendingMachineDao daoStub = new VendingMachineDaoStubImpl();
//    private VendingMachineServiceLayerImpl service = new VendingMachineServiceLayerImpl(daoStub);

    private VendingMachineServiceLayer service;


    public VendingMachineServiceLayerTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        service = ctx.getBean("serviceLayer", VendingMachineServiceLayer.class);
    }


    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testRetrieveAllVendingMachineItems() throws Exception{
        assertEquals(2, service.retrieveAllVendingMachineItems().size());
    }


    @Test
    public void testAddMoneyToMemory() throws Exception{
        service.addMoneyToMemory(Money.valueOf("DOLLAR"));
        assertEquals(new BigDecimal("1.00"), service.getRemainingMoney());
    }

    @Test
    public void testPurchaseItemQuantityAndRemainingMoney() throws Exception{
        //arrange
        service.addMoneyToMemory(Money.valueOf("DOLLAR"));
        //act, id of 1 is our only item in the daoStubImpl
        VendingMachineItem item = new VendingMachineItem();
        item.setItemId(1);
        service.purchaseItem(item);
        //assert, may not need the first assert as update is tested in dao tests
        assertEquals(2, service.retrieveAllVendingMachineItems().get(0).getItemQuantity());
        assertEquals(new BigDecimal("0"), service.getRemainingMoney());
    }

    @Test
    public void testPurchaseItemNoItemInventoryException() throws Exception{
        // arrange -- stub only has one item with ID of 1, anything else
        // passed to purchaseItem() should thrown an exception

        try{
            //act
            VendingMachineItem item = new VendingMachineItem();
            item.setItemId(23);
            service.purchaseItem(item);
            fail("Expected NoItemInventoryException was not thrown");
        } catch (NoItemInventoryException e){
            // do nothing, test passes
        }
    }

    @Test
    public void testPurchaseItemInsufficientFundsException() throws Exception{
        // arrange - setup method will set remainingMoney to zero

        try{
            //act
            VendingMachineItem item = new VendingMachineItem();
            item.setItemId(1);
            service.purchaseItem(item);
            fail("Expected InsufficientFundsException was not thrown");
        } catch (InsufficientFundsException e){
            // do nothing, test passes
        }
    }

    @Test
    public void testConvertDollarsToChange() throws Exception {
        service.addMoneyToMemory(Money.valueOf("DOLLAR"));
        service.addMoneyToMemory(Money.valueOf("DIME"));
        service.addMoneyToMemory(Money.valueOf("NICKEL"));
        VendingMachineChange change = service.convertDollarsToCoinsAndGetChange();
        assertEquals(4, change.getQuarters());
        assertEquals(1, change.getDimes());
        assertEquals(1, change.getNickels());
        assertEquals(0, change.getPennies());
    }

    @Test
    public void testRetrieveRemainingMoney() throws Exception{
        // arrange
        service.addMoneyToMemory(Money.valueOf("DOLLAR"));
        // act & assert
        assertEquals(new BigDecimal("1.00"), service.getRemainingMoney());
    }
}