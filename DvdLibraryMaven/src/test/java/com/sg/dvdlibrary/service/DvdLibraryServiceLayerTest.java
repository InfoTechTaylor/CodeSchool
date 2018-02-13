package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dao.*;
import com.sg.dvdlibrary.dto.Dvd;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class DvdLibraryServiceLayerTest {

    DvdLibraryDao dao = new DvdLibraryDaoStubImpl();
    DvdLibraryAuditDao auditDao = new DvdLibraryAuditStubImpl();
    DvdLibraryServiceLayer service = new DvdLibraryServiceLayerImpl(dao,auditDao);

    public DvdLibraryServiceLayerTest() {

    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCreateDvd() {
        Dvd newDvd = new Dvd("Batman 2", LocalDate.parse("2000-01-02" ), "PG-13", "Joe", "Fox", "Great movie");
    }

    @Test
    public void testCreateDvdDuplicateId() throws Exception {
        Dvd newDvd = new Dvd("Batman", LocalDate.parse("2000-01-02"), "PG-13", "Joe", "Fox", "Great movie");
        try {
            service.createDvd(newDvd);
            fail("Expected ClassRosterDuplicateIDException was not thrown");
        } catch (DvdLibraryDuplicateIdException e) {
            return;
        }
    }

    @Test
    public void testCreateDvdInvalidData() throws Exception {
        Dvd newDvd = new Dvd("Batman 2", LocalDate.parse("2000-01-02"), "", "Joe", "Fox", "Great movie");
        try{
            service.createDvd(newDvd);
            fail("Expected DvdLibraryDataValidationException was not thrown");
        } catch (DvdLibraryDataValidationException e){
            return;
        }
    }

    @Test
    public void testGetAllDvds() throws Exception {
        assertEquals(1, service.getAllDvds().size());
    }

    @Test
    public void testGetDvd() throws Exception {
        Dvd dvd = service.getDvd("Batman");
        assertNotNull(dvd);
        dvd = service.getDvd("Superman");
        assertNull(dvd);
    }

    @Test
    public void testRemoveDvd() throws Exception {
        Dvd dvd = service.removeDvd("Batman");
        assertNotNull(dvd);
        dvd = service.removeDvd("Superman");
        assertNull(dvd);
    }

    @Test
    public void editDvd() throws Exception {
        Dvd originalDvd = service.getDvd("Batman");
        service.editDvd(originalDvd);
    }

    @Test
    public void titleExists() {
    }
}