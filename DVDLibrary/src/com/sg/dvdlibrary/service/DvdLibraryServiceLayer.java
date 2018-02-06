package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dao.DvdLibraryPersistenceException;
import com.sg.dvdlibrary.dto.Dvd;

import java.util.List;

public interface DvdLibraryServiceLayer {

    void createDvd(Dvd dvd) throws
            DvdLibraryDuplicateIdException,
            DvdLibraryDataValidationException,
            DvdLibraryPersistenceException;


    List<Dvd> getAllDvds() throws
            DvdLibraryPersistenceException;


    Dvd getDvd(String title) throws
            DvdLibraryPersistenceException;


    Dvd removeDvd(String title) throws
            DvdLibraryPersistenceException;


    Dvd editDvd(Dvd dvd) throws
            DvdLibraryPersistenceException;


    boolean titleExists(String title) throws
            DvdLibraryPersistenceException;
}
