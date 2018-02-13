package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.*;

public class DvdLibraryDaoTest {

    private DvdLibraryDao dao = new DvdLibraryDaoFileImpl();

    public DvdLibraryDaoTest() {

    }


    @Before
    public void setUp() throws Exception {
        // run before each test method in the class and have a known good state as zero dvds
        // first get all dvds and store the returned list in a list variable
        List<Dvd> dvdList = dao.getAllDvds();
        // loop through the list of getAllDvds and remove each item in the list
        for(Dvd dvd : dvdList) {
            dao.removeDvd(dvd.getTitle());
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAddGetDvd() throws Exception {
        // arrange a Dvd to add
//        LocalDate releaseDate = LocalDate.parse("")
        Dvd dvd = new Dvd("Batman", LocalDate.parse("2000-01-02"), "PG-13", "Joe Schmoe", "Fox",
                "5 Stars");

        // act, add the new dvd above
        dao.addDvd(dvd.getTitle(), dvd);
        Dvd dvdFromDao = dao.getDvd(dvd.getTitle());

        // assert
        assertEquals(dvd, dvdFromDao);
    }

    @Test
    public void testGetAllDvds() throws Exception {
        // these unit tests will modify our actual dvds.txt file
        // arrange, create two new dvd objects
        Dvd dvd1 = new Dvd("Batman", LocalDate.parse("2000-01-02"), "PG-13", "Joe Schmoe", "Fox",
                "5 Stars");

        Dvd dvd2 = new Dvd("Harry Potter", LocalDate.parse("2000-01-02"), "PG-13", "Speilburg", "Paramour",
                "5 Stars");

        dao.addDvd(dvd1.getTitle(), dvd1);
        dao.addDvd(dvd2.getTitle(), dvd2);

        // act is the dao.getAllDvds().size()
        // assert that the size of the dvd collection is 2 since we added two dvds
        assertEquals(2, dao.getAllDvds().size());
    }

    @Test
    public void testRemoveDvd() throws Exception {

        // arrange, create two new dvd objects
        Dvd dvd1 = new Dvd("Batman", LocalDate.parse("2000-01-02"), "PG-13", "Joe Schmoe", "Fox",
                "5 Stars");

        Dvd dvd2 = new Dvd("Harry Potter", LocalDate.parse("2000-01-02"), "PG-13", "Speilburg", "Paramour",
                "5 Stars");

        dao.addDvd(dvd1.getTitle(), dvd1);
        dao.addDvd(dvd2.getTitle(), dvd2);

        // act
        dao.removeDvd(dvd1.getTitle());
        // assert
        assertEquals(1, dao.getAllDvds().size());
        // assert
        assertNull(dao.getDvd(dvd1.getTitle()));

        //act
        dao.removeDvd(dvd2.getTitle());
        // assert
        assertEquals(0, dao.getAllDvds().size());
        // assert
        assertNull(dao.getDvd(dvd2.getTitle()));
    }

    @Test
    public void editDvd() throws Exception{
        // arrange, create a dvd object to edit
        Dvd dvd = new Dvd("Batman", LocalDate.parse("2000-01-02"), "PG-13", "Joe Schmoe", "Fox",
                "5 Stars");
        dao.addDvd(dvd.getTitle(), dvd);
        Dvd originalDvd = dao.getDvd(dvd.getTitle());

        // act
        // edit dvd with setters, pass the edited object to dao edit method
        originalDvd.setUserRating("Not as good as the Dark Night");
        Dvd updatedDvd = dao.getDvd(dvd.getTitle());

        // assert that original dvd is not equal to updatedDvd
        assertNotEquals(originalDvd, updatedDvd);
        //assertNotEquals(originalDvd.getUserRating(), updatedDvd.getUserRating()); // this also works

    }
}