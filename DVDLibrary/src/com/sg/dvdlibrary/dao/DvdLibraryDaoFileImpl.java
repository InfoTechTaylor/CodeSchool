package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;

import java.io.*;
import java.util.*;

public class DvdLibraryDaoFileImpl implements DvdLibraryDao {

    private final String DELIMITER = "::";
    private final String DVD_FILE = "dvds.txt";
    private Map<String, Dvd> dvds = new HashMap<>();


    @Override
    public Dvd getDvd(String title) throws DvdLibraryDaoException {
        loadCollection();
        return dvds.get(title);
    }

    @Override
    public ArrayList<Dvd> getAllDvds() throws DvdLibraryDaoException {
        loadCollection();
        return new ArrayList<Dvd>(dvds.values());
    }

    @Override
    public Dvd removeDvd(String title) throws DvdLibraryDaoException{
        Dvd removedDvd = dvds.remove(title);
        writeCollection();
        return removedDvd;
    }

    @Override
    public Dvd addDvd(String title, Dvd dvd) throws DvdLibraryDaoException {
        Dvd newDvd = dvds.put(title, dvd);
        writeCollection();
        return newDvd;
    }


    /**
     * Reads from DVD_FILE line by line and parses the lines to create a
     * map of Dvd objects. File format includes :: as a delimiter
     * @throws DvdLibraryDaoException if an error occurs reading from file
     */
    @Override
    public void loadCollection() throws DvdLibraryDaoException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(DVD_FILE)));
        } catch (FileNotFoundException e) {
            throw new DvdLibraryDaoException(
                    "-_- Could not load dvd data into memory.", e);
        }

        // currentLine holds the most recent line read from the file
        String currentLine;

        // currentToken holds each of the parts of the currentLine after it has
        // been split on our DELIMITER
        String[] currentTokens;
        // Go through the DVD_FILE line by line, decoding each line into
        // a Dvd object
        // Process while we have more lines in the file
        while(scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // break up the line into tokens
            currentTokens = currentLine.split(DELIMITER);
            // Create a new Dvd object and put it into the map of students

            Dvd currentDvd = new Dvd(currentTokens[0]);
            // set the remaining values on currentDvd manually
            currentDvd.setReleaseDate(currentTokens[1]);
            currentDvd.setRatingMPAA(currentTokens[2]);
            currentDvd.setDirectorName(currentTokens[3]);
            currentDvd.setStudio(currentTokens[4]);
            currentDvd.setUserRating(currentTokens[5]);

            // Put currentDvd into the map using title as the key
            dvds.put(currentDvd.getTitle(), currentDvd);

        } // end while(scanner.hasNextLine())
        // close scanner
        scanner.close();
    }


    /**
     * Writes all dvds in the collection out to DVD_FILE.
     * @throws DvdLibraryDaoException if an error occurs writing to the file
     */
    @Override
    public void writeCollection() throws DvdLibraryDaoException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(DVD_FILE));
        } catch (IOException e) {
            throw new DvdLibraryDaoException(
                    "Could not save student data.", e);
        }

        // Write out the Dvd objects to the collections file (DVD_FILE)
        // reuse method to get list of dvds
        List<Dvd> dvdList = this.getAllDvds();
        for (Dvd currentDvd : dvdList){
            // write the Student object to the file
            out.println(currentDvd.getTitle() + DELIMITER
                        + currentDvd.getReleaseDate() + DELIMITER
                        + currentDvd.getRatingMPAA() + DELIMITER
                        + currentDvd.getDirectorName() + DELIMITER
                        + currentDvd.getStudio() + DELIMITER
                        + currentDvd.getUserRating());
            //force PrintWriter to write line to the file
            out.flush();
        } // end for
        // clean up
        out.close();

    }
}
