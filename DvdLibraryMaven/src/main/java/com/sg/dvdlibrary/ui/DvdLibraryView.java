package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.Dvd;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

public class DvdLibraryView {

    // initialize UserIO object of console implementation
    private UserIO io;
    private final String EMPTY_STRING = "";


    public DvdLibraryView(UserIO io){
        this.io = io;
    }


    public int printMenuAndGetSelection(){
        io.print("MAIN MENU:");
        io.print("===================================================================");
        io.print("\t1. Add a DVD to the collection");
        io.print("\t2. Remove a DVD from the collection");
        io.print("\t3. Edit the information for an existing DVD in the collection");
        io.print("\t4. List all DVDs in the collection");
        io.print("\t5. Search for a DVD by title");
        io.print("\t6. Exit program");

        return io.readInt("\nPlease select one of the above options: ", 1, 6);
    }


    public Dvd getNewDvdInfo(){
        String title = io.readString("\tEnter dvd title: ");
        LocalDate releaseDate = io.readLocalDate("\tEnter dvd release date: ");
        String ratingMPAA = io.readString("\tEnter dvd MPAA rating: ");
        String directorName = io.readString("\tEnter dvd director's name: ");
        String studio = io.readString("\tEnter dvd studio: ");
        String userRating = io.readString("\tEnter your rating of the dvd: ");

        Dvd newDvd = new Dvd(title, releaseDate, ratingMPAA, directorName, studio, userRating);
        return newDvd;
    }


    public void displayCreateDvdBanner() {
        io.print("\nADD DVD MENU: ");
        io.print("===================================================================");
    }


    public void displayCreateSuccessBanner() {
        io.readString("\nDVD created successfully. Please hit enter to continue.");
    }


    public void displayDvdList(List<Dvd> dvdList){
        for (Dvd currentDvd : dvdList){
            io.print("\t" + currentDvd.getTitle() );
        }
        io.readString("\nPlease hit enter to continue.");
    }


    public void displayDisplayAllBanner() {
        io.print("\nDISPLAY ALL DVDS: ");
        io.print("===================================================================");
    }


    public void displayErrorMessage(String errorMsg) {
        io.print("ERROR:");
        io.print("\t" + errorMsg);
    }


    public void displayDisplayDvdBanner() {
        io.print("\nDISPLAY DVD: ");
        io.print("===================================================================");
    }


    public String getDvdTitleChoice() {
        return io.readString("\tPlease enter DVD title: ");
    }


    public void displayDvd(Dvd dvd) {
        if (dvd != null) {
            io.print("\tTitle: " + dvd.getTitle());
            io.print("\tRelease Date: " + dvd.getReleaseDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
            io.print("\tMPAA Rating: " + dvd.getRatingMPAA());
            io.print("\tDirector: " + dvd.getDirectorName());
            io.print("\tStudio: " + dvd.getStudio());
            io.print("\tUser Rating: " + dvd.getUserRating());
        } else {
            io.print("\tNo such DVD");
        }
        io.readString("\tPlease hit enter to continue.");
    }


    public Dvd displayEditMenuAndGetUpdates(Dvd dvd){
        int editItem = 0;
        boolean continueEdit = true;

        if (dvd != null) {

            io.print("Title: " + dvd.getTitle());
            LocalDate editedReleaseDate = io.readLocalDate("\tRelease Date (" + dvd.getReleaseDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)) +"): ");
            String editedMPAARating = io.readString("\tMPAA Rating (" + dvd.getRatingMPAA() + "): ");
            String editedDirector = io.readString("\tDirector (" + dvd.getDirectorName() + "): ");
            String editedStudio = io.readString("\tStudio (" + dvd.getStudio() + "): ");
            String editedUserRating = io.readString("\tUser Rating (" + dvd.getUserRating() +"): ");


            if(!EMPTY_STRING.equals(editedReleaseDate)){
                dvd.setReleaseDate(editedReleaseDate);
            }

            if(!EMPTY_STRING.equals(editedMPAARating)){
                dvd.setRatingMPAA(editedMPAARating);
            }

            if(!EMPTY_STRING.equals(editedDirector)){
                dvd.setDirectorName(editedDirector);
            }

            if(!EMPTY_STRING.equals(editedStudio)){
                dvd.setStudio(editedStudio);
            }

            if(!EMPTY_STRING.equals(editedUserRating)){
                dvd.setUserRating(editedUserRating);
            }

        } else {
            io.print("No such DVD exists. ");
        }

        return dvd;
    } // end method


    public void displayEditMenuBanner(){
        io.print("\nEDIT MENU:");
        io.print("===================================================================");
    }


    public void displayRemoveMenuBanner(){
        io.print("\nREMOVE DVD MENU:");
        io.print("===================================================================");
    }


    public void displayRemoveSuccessBanner(){
        io.readString("\nSuccessfully removed DVD. Press enter to continue. ");
    }


    public void displayGoodBye(){
        io.print("Good Bye!!");
    }


    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayNoSuchDvd(){
        io.print("No such DVD exists.");
    }

}
