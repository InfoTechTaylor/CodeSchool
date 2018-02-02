package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;

import java.util.ArrayList;

public interface DvdLibraryDao {


    /**
     * Returns a Dvd object associated with the given title
     * @param title
     * @return Dvd object
     */
    Dvd getDvd(String title) throws DvdLibraryDaoException;


    /**
     * Gets all Dvds and returns as an ArrayList
     * @return ArrayList of Dvd objects
     */
    ArrayList<Dvd> getAllDvds() throws DvdLibraryDaoException;


    /**
     * Removes Dvd object associated with the given title and returns the object.
     * @param title
     * @return Dvd object that was removed
     */
    Dvd removeDvd(String title) throws DvdLibraryDaoException;


    /**
     * Creates a Dvd object with the provided String title as the key and the Dvd
     * object as the value
     * @param title
     * @param dvd
     * @return Dvd object of newly added Dvd
     */
    Dvd addDvd(String title, Dvd dvd) throws DvdLibraryDaoException;



    /**
     * Method to use to load Dvds from a file
     */
    void loadCollection() throws DvdLibraryDaoException;


    /**
     * Method to write Dvds to a file
     */
    void writeCollection() throws DvdLibraryDaoException;

}
