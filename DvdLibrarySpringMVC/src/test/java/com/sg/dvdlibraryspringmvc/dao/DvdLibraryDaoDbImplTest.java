package com.sg.dvdlibraryspringmvc.dao;

import com.sg.dvdlibraryspringmvc.model.Dvd;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

public class DvdLibraryDaoDbImplTest {

    DvdLibraryDao dao;

    @Before
    public void setUp() throws Exception {

        // ask Spring for our DAO
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext(
                "test-applicationContext.xml");
        dao = ctx.getBean("DvdLibraryDao", DvdLibraryDao.class);

        // remove all of the Contacts
        List<Dvd> contacts = dao.getAllDvds();
        for (Dvd currentContact : contacts) {
            dao.removeDvd(currentContact.getDvdId());
        }
    }

    @Test
    public void setJdbcTemplate() {
    }

    @Test
    public void getDvd() {
    }

    @Test
    public void getAllDvds() {
    }

    @Test
    public void removeDvd() {
    }

    @Test
    public void addDvd() {
    }

    @Test
    public void editDvd() {
    }

    @Test
    public void searchDvds() {
    }
}