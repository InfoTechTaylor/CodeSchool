package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.Dvd;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

public class DvdLibraryView {

    // initialize UserIO object of console implementation
    private UserIO io;


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
            // while the user still wants to make edits
//            io.print("Title: " + dvd.getTitle());
            while (continueEdit) {
                io.print("Title: " + dvd.getTitle());
                io.print("\t1. Release Date: " + dvd.getReleaseDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
                io.print("\t2. MPAA Rating: " + dvd.getRatingMPAA());
                io.print("\t3. Director: " + dvd.getDirectorName());
                io.print("\t4. Studio: " + dvd.getStudio());
                io.print("\t5. User Rating: " + dvd.getUserRating());
                io.print("\t6. Exit Edit Menu");

                editItem = io.readInt("Please select which item you'd like to edit.", 1, 6);

                switch (editItem) {
//                    case 1:
//                        String newTitle = io.readString("Enter new title: ");
//                        dvd.setTitle(newTitle);
//                        break;
                    case 1:
                        LocalDate newReleaseDate = io.readLocalDate("Enter new release date: ");
                        dvd.setReleaseDate(newReleaseDate);
                        break;
                    case 2:
                        String newRatingMPAA = io.readString("Enter new MPAA Rating: ");
                        dvd.setRatingMPAA(newRatingMPAA);
                        break;
                    case 3:
                        String newDirector = io.readString("Enter new director name: ");
                        dvd.setDirectorName(newDirector);
                        break;
                    case 4:
                        String newStudio = io.readString("Enter new studio name: ");
                        dvd.setStudio(newStudio);
                        break;
                    case 5:
                        String newUserRating = io.readString("Enter new user rating: ");
                        dvd.setUserRating(newUserRating);
                        break;
                    case 6:
                        continueEdit = false;
                        break;
                    default:
                        io.print("Unrecognized entry.");
                } // end switch
            } // end while
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
