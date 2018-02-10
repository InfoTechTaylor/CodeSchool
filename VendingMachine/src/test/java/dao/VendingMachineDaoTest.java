package dao;

import dto.VendingMachineItem;
//import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class VendingMachineDaoTest {

    private VendingMachineDao dao = new VendingMachineDaoFileImpl("vendingMachineItems_test.txt");

    @Before
    public void setUp() throws Exception {
        List<VendingMachineItem> allItems = dao.retrieveAllVendingMachineItems();
        for (VendingMachineItem currentItem : allItems){
            dao.removeVendingMachineItem(currentItem.getItemId());
        }
    }

//    @After
//    public void tearDown() throws Exception {
//
//    }

    @Test
    public void retrieveAllVendingMachineItems() throws Exception{
        // arrange
        VendingMachineItem item = new VendingMachineItem("1");
        item.setItemName("Chips");
        item.setItemCost(new BigDecimal(".75"));
        item.setItemQuantity(4);
        dao.createVendingMachineItem(item);

        VendingMachineItem item2 = new VendingMachineItem("2");
        item2.setItemName("Soda");
        item2.setItemCost(new BigDecimal("1.75"));
        item2.setItemQuantity(5);
        dao.createVendingMachineItem(item2);

        // act
        List<VendingMachineItem> allItemsList = dao.retrieveAllVendingMachineItems();
        // assert
        assertEquals(2, allItemsList.size());
    }

    @Test
    public void updateItem() throws Exception {
        //arrange
        VendingMachineItem item = new VendingMachineItem("1");
        item.setItemName("Chips");
        item.setItemCost(new BigDecimal(".75"));
        item.setItemQuantity(4);
        dao.createVendingMachineItem(item);
        VendingMachineItem originalItem = dao.retrieveItemById("1");
        // act
        item.setItemQuantity(10);
        dao.updateItem(item);
        //assert
        assertNotEquals(dao.retrieveItemById("1"), originalItem);
    }

    @Test
    public void testRemoveVendingMachineItem() throws Exception{
        //arrange
        VendingMachineItem item = new VendingMachineItem("1");
        item.setItemName("Chips");
        item.setItemCost(new BigDecimal(".75"));
        item.setItemQuantity(4);
        dao.createVendingMachineItem(item);
        //act
        dao.removeVendingMachineItem(item.getItemId());
        //assert
        assertEquals(dao.retrieveItemById(item.getItemId()), null);
    }

    @Test
    public void testCreateGetVendingMachineItem() throws Exception{
        // arrange
        VendingMachineItem item = new VendingMachineItem("1");
        item.setItemName("Chips");
        item.setItemCost(new BigDecimal(".75"));
        item.setItemQuantity(4);

        //act
        dao.createVendingMachineItem(item);
        VendingMachineItem itemFromDao = dao.retrieveItemById("1");

        // assert
        assertEquals(item, itemFromDao);
        assertNotNull(item);

    }
}