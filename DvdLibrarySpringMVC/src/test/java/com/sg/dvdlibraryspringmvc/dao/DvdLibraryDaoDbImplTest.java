package com.sg.dvdlibraryspringmvc.dao;

import com.sg.dvdlibraryspringmvc.model.Dvd;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void addAndGetDvd() {

        Dvd newDvd = new Dvd();
        newDvd.setTitle("Harry Potter");
        newDvd.setReleaseDate(LocalDate.parse("2000-01-01"));
        newDvd.setRating("PG");
        newDvd.setDirector(null);
        newDvd.setNotes(null);

        Dvd dvdWithId = dao.addDvd(newDvd);
        Dvd fromDao = dao.getDvd(dvdWithId.getDvdId());
        assertNotNull(fromDao);
        assertEquals("Harry Potter", fromDao.getTitle());
        assertEquals("PG", fromDao.getRating());
        assertEquals(LocalDate.parse("2000-01-01"), fromDao.getReleaseDate());
    }

    @Test
    public void getAllDvds() {

        Dvd newDvd = new Dvd();
        newDvd.setTitle("Harry Potter");
        newDvd.setReleaseDate(LocalDate.parse("2000-01-01"));
        newDvd.setRating("PG");
        newDvd.setDirector(null);
        newDvd.setNotes(null);

        Dvd newDvd2 = new Dvd();
        newDvd2.setTitle("Fantastic Beasts and Where to Find Them");
        newDvd2.setReleaseDate(LocalDate.parse("2017-11-01"));
        newDvd2.setRating("PG-13");
        newDvd2.setDirector(null);
        newDvd2.setNotes("fantastic!");

        dao.addDvd(newDvd);
        dao.addDvd(newDvd2);
        assertEquals(2, dao.getAllDvds().size());
    }

    @Test
    public void removeDvd() {

        Dvd newDvd = new Dvd();
        newDvd.setTitle("Harry Potter");
        newDvd.setReleaseDate(LocalDate.parse("2000-01-01"));
        newDvd.setRating("PG");
        newDvd.setDirector(null);
        newDvd.setNotes(null);

        Dvd fromDao = dao.addDvd(newDvd);
        dao.removeDvd(fromDao.getDvdId());
        assertNull(dao.getDvd(fromDao.getDvdId()));
    }


    @Test
    public void editDvd() {

        Dvd newDvd = new Dvd();
        newDvd.setTitle("Harry Potter");
        newDvd.setReleaseDate(LocalDate.parse("2000-01-01"));
        newDvd.setRating("PG");
        newDvd.setDirector(null);
        newDvd.setNotes(null);

        Dvd fromDao = dao.addDvd(newDvd);
        fromDao.setTitle("Harry Potter and The Sorceror's Stone");
        Dvd updatedDvd = dao.editDvd(fromDao);
        assertEquals("Harry Potter and The Sorceror's Stone", dao.getDvd(updatedDvd.getDvdId()).getTitle());
    }

    @Test
    public void searchDvds() {

        Dvd newDvd = new Dvd();
        newDvd.setTitle("Harry Potter");
        newDvd.setReleaseDate(LocalDate.parse("2000-01-01"));
        newDvd.setRating("PG");
        newDvd.setDirector(null);
        newDvd.setNotes(null);

        Dvd fromDao = dao.addDvd(newDvd);
        Map<SearchTerm, String> criteria = new HashMap<>();
        criteria.put(SearchTerm.valueOf("DVDTITLE"), "Harry Potter");
        List<Dvd> dvdList = dao.searchDvds(criteria);
        assertEquals("Harry Potter", dao.getDvd(dvdList.get(0).getDvdId()).getTitle());
    }
}