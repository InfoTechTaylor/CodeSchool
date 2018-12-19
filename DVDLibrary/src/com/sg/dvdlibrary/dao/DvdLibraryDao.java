package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;

import java.util.ArrayList;

public interface DvdLibraryDao {


    /**
     * Returns a Dvd object associated with the given title
     * @param title
     * @return Dvd object
     */
    Dvd getDvd(String title) throws DvdLibraryPersistenceException;


    /**
     * Gets all Dvds and returns as an ArrayList
     * @return ArrayList of Dvd objects
     */
    ArrayList<Dvd> getAllDvds() throws DvdLibraryPersistenceException;



    /**
     * Removes Dvd object associated with the given title and returns the object.
     * @param title
     * @return Dvd object that was removed
     */
    Dvd removeDvd(String title) throws DvdLibraryPersistenceException;


    /**
     * Creates a Dvd object with the provided String title as the key and the Dvd
     * object as the value
     * @param title
     * @param dvd
     * @return Dvd object of newly added Dvd
     */
    Dvd addDvd(String title, Dvd dvd) throws DvdLibraryPersistenceException;



    Dvd editDvd(Dvd dvd) throws DvdLibraryPersistenceException;



}
