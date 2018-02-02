package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.Dvd;

import java.util.List;

public class DvdLibraryView {

    // initialize UserIO object of console implementation
    UserIO io = new UserIOConsoleImpl();


    public int printMenuAndGetSelection(){
        io.print("Main Menu:");
        io.print("\t1. Add a DVD to the collection");
        io.print("\t2. Remove a DVD from the collection");
        io.print("\t3. Edit the information for an existing DVD in the collection");
        io.print("\t4. List all DVDs in the collection");
        io.print("\t5. Search for a DVD by title");
        io.print("\t6. Exit program");

        return io.readInt("Please select one of the above options: ");
    }


    public Dvd getNewDvdInfo(){
        String title = io.readString("Enter dvd title: ");
        String releaseDate = io.readString("Enter dvd release date: ");
        String ratingMPAA = io.readString("Enter dvd MPAA rating: ");
        String directorName = io.readString("Enter dvd director's name: ");
        String studio = io.readString("Enter dvd studio: ");
        String userRating = io.readString("Enter your rating of the dvd: ");

        Dvd newDvd = new Dvd(title, releaseDate, ratingMPAA, directorName, studio, userRating);
        return newDvd;
    }


    public void displayCreateDvdBanner() {
        io.print("Add DVD Menu: ");
    }


    public void displayCreateSuccessBanner() {
        io.readString("DVD created successfully. Please hit enter to continue.");
    }


    public void displayDvdList(List<Dvd> dvdList){
        for (Dvd currentDvd : dvdList){
            io.print("\t" + currentDvd.getTitle() + ": \n\t\t" +
                        currentDvd.getReleaseDate() + "\n\t\t" +
                        currentDvd.getRatingMPAA() + "\n\t\t" +
                        currentDvd.getDirectorName() + "\n\t\t" +
                        currentDvd.getStudio() + "\n\t\t" +
                        currentDvd.getUserRating() + "\n\n\t\t");
        }
        io.readString("Please hit enter to continue.");
    }


    public void displayDisplayAllBanner() {
        io.print("Display All Dvds: ");
    }


    public void displayErrorMessage(String errorMsg) {
        io.print("ERROR:");
        io.print("\t" + errorMsg);
    }


    public void displayDisplayDvdBanner() {
        io.print("Display DVD: ");
    }


    public String getDvdTitleChoice() {
        return io.readString("\tPlease enter DVD title: ");
    }


    public void displayDvd(Dvd dvd) {
        if (dvd != null) {
            io.print("\t" + dvd.getTitle());
            io.print("\t" + dvd.getReleaseDate());
            io.print("\t" + dvd.getRatingMPAA());
            io.print("\t" + dvd.getDirectorName());
            io.print("\t" + dvd.getStudio());
            io.print("\t" + dvd.getUserRating());
        } else {
            io.print("\tNo such DVD");
        }
        io.readString("\tPlease hit enter to continue.");
    }


    public Dvd displayEditMenuAndGetUpdates(Dvd dvd){
        int editItem = 0;
        boolean continueEdit = true;

        // while the user still wants to make edits
        while(continueEdit) {
            io.print("\t1. Title: " + dvd.getTitle());
            io.print("\t2. Release Date: " + dvd.getReleaseDate());
            io.print("\t3. MPAA Rating: " + dvd.getRatingMPAA());
            io.print("\t4. Director: " + dvd.getDirectorName());
            io.print("\t5. Studio: " + dvd.getStudio());
            io.print("\t6. User Rating: " + dvd.getUserRating());
            io.print("\t7. Exit Edit Menu");

            editItem = io.readInt("Please select which item you'd like to edit.");

            switch(editItem){
                case 1:
                    String newTitle = io.readString("Enter new title: ");
                    dvd.setTitle(newTitle);
                    break;
                case 2:
                    String newReleaseDate = io.readString("Enter new release date: ");
                    dvd.setReleaseDate(newReleaseDate);
                    break;
                case 3:
                    String newRatingMPAA = io.readString("Enter new MPAA Rating: ");
                    dvd.setRatingMPAA(newRatingMPAA);
                    break;
                case 4:
                    String newDirector = io.readString("Enter new director name: ");
                    dvd.setDirectorName(newDirector);
                    break;
                case 5:
                    String newStudio = io.readString("Enter new studio name: ");
                    dvd.setStudio(newStudio);
                    break;
                case 6:
                    String newUserRating = io.readString("Enter new user rating: ");
                    dvd.setUserRating(newUserRating);
                    break;
                case 7:
                    continueEdit = false;
                    break;
                default:
                    io.print("Unrecognized entry.");
            } // end switch
        } // end while

        return dvd;
    } // end method


    public void displayEditMenuBanner(){
        io.print("EDIT MENU:");
    }


    public void displayRemoveMenuBanner(){
        io.print("REMOVE DVD MENU:");
    }


    public void displayRemoveSuccessBanner(){
        io.readString("Successfully removed DVD. Press enter to continue. ");
    }

}
