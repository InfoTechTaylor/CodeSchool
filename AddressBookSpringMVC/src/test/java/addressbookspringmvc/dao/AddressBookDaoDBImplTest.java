package addressbookspringmvc.dao;

import addressbookspringmvc.dto.Address;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

public class AddressBookDaoDBImplTest {

    AddressBookDao dao;

    @Before
    public void setUp() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("AddressBookDaoTest", AddressBookDao.class);

        // remove all addresses
        List<Address> addresses = dao.getAllAddresses();
        for(Address currentAddress : addresses) {
            dao.deleteAddress(currentAddress.getAddressId());
        }
    }

    @Test
    public void setJdbcTemplate() {
    }

    @Test
    public void addAndGetAddress() {
        Address newAddress = new Address();
        newAddress.setFirstName("Taylor");
        newAddress.setLastName("Lapointe");
        newAddress.setStreetAddress("1 main street");
        newAddress.setCity("Dover");
        newAddress.setState("NH");
        newAddress.setZip("03820");

        dao.addAddress(newAddress.getLastName(), newAddress);

        Address addressFromDao = dao.getAddress(newAddress.getLastName());
        assertNotNull(addressFromDao);
        assertEquals("Lapointe", addressFromDao.getLastName());

    }

    @Test
    public void deleteAddress() {

        Address newAddress = new Address();
        newAddress.setFirstName("Taylor");
        newAddress.setLastName("Lapointe");
        newAddress.setStreetAddress("1 main street");
        newAddress.setCity("Dover");
        newAddress.setState("NH");
        newAddress.setZip("03820");

        dao.addAddress(newAddress.getLastName(), newAddress);
        Address fromDao = dao.getAddress("Lapointe");

        dao.deleteAddress(fromDao.getAddressId());
        assertNull(dao.getAddress("Lapointe"));
    }

    @Test
    public void getAddressCount() {
        Address newAddress = new Address();
        newAddress.setFirstName("Taylor");
        newAddress.setLastName("Lapointe");
        newAddress.setStreetAddress("1 main street");
        newAddress.setCity("Dover");
        newAddress.setState("NH");
        newAddress.setZip("03820");

        Address newAddress2 = new Address();
        newAddress2.setFirstName("Brett");
        newAddress2.setLastName("Hicklin");
        newAddress2.setStreetAddress("1 main street");
        newAddress2.setCity("Dover");
        newAddress2.setState("NH");
        newAddress2.setZip("03820");

        dao.addAddress(newAddress.getLastName(), newAddress);
        dao.addAddress(newAddress2.getLastName(), newAddress2);
        assertEquals(2, dao.getAddressCount());
    }

    @Test
    public void getAllAddresses() {
        Address newAddress = new Address();
        newAddress.setFirstName("Taylor");
        newAddress.setLastName("Lapointe");
        newAddress.setStreetAddress("1 main street");
        newAddress.setCity("Dover");
        newAddress.setState("NH");
        newAddress.setZip("03820");

        Address newAddress2 = new Address();
        newAddress2.setFirstName("Brett");
        newAddress2.setLastName("Hicklin");
        newAddress2.setStreetAddress("1 main street");
        newAddress2.setCity("Dover");
        newAddress2.setState("NH");
        newAddress2.setZip("03820");

        dao.addAddress(newAddress.getLastName(), newAddress);
        dao.addAddress(newAddress2.getLastName(), newAddress2);
        assertEquals(2, dao.getAllAddresses().size());
    }
}