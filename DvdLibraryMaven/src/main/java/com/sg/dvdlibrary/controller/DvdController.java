package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DvdLibraryDao;
import com.sg.dvdlibrary.dao.DvdLibraryPersistenceException;
import com.sg.dvdlibrary.dto.Dvd;
import com.sg.dvdlibrary.service.DvdLibraryDataValidationException;
import com.sg.dvdlibrary.service.DvdLibraryDuplicateIdException;
import com.sg.dvdlibrary.service.DvdLibraryServiceLayer;
import com.sg.dvdlibrary.ui.DvdLibraryView;
import java.util.List;

public class DvdController {

    private DvdLibraryServiceLayer service;
    DvdLibraryView view;


    public DvdController(DvdLibraryServiceLayer service, DvdLibraryView view) {
        this.service = service;
        this.view = view;
    }


    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;

        try {
            while (keepGoing) {

                // get MenuSelection from user
                menuSelection = getMenuSelection();

                // determine action based of menuSelection
                switch (menuSelection) {
                    case 1:
                        createDvd();
                        break;
                    case 2:
                        removeDvd();
                        break;
                    case 3:
                        editDvd();
                        break;
                    case 4:
                        displayDvdList();
                        break;
                    case 5:
                        viewDvd();
                        break;
                    case 6:
                        keepGoing = false;
                        exitMessage();
                        break;
                    default:
                        unknownCommand();
                } // end switch(menuSelection)

            } // end while(keepGoing)

        } catch (DvdLibraryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    } // end run()


    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }


    private void createDvd() throws DvdLibraryPersistenceException {
        view.displayCreateDvdBanner();
        boolean hasErrors = false;

        do {
            Dvd newDvd = view.getNewDvdInfo();
            try{
                service.createDvd(newDvd);
                view.displayCreateSuccessBanner();
                hasErrors = false;
            } catch (DvdLibraryDuplicateIdException | DvdLibraryDataValidationException e){
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while(hasErrors);

    } // end createDvd()


    private void displayDvdList() throws DvdLibraryPersistenceException {
        view.displayDisplayAllBanner();
        List<Dvd> dvdList = service.getAllDvds();
        view.displayDvdList(dvdList);
    }


    private void viewDvd() throws DvdLibraryPersistenceException {
        boolean titleExists = false;

        view.displayDisplayDvdBanner();
        while(titleExists == false) {
            String dvdTitle = view.getDvdTitleChoice();
            titleExists = service.titleExists(dvdTitle);

            if(titleExists == true) {
                Dvd dvd = service.getDvd(dvdTitle);
                view.displayDvd(dvd);
            } else {
                view.displayNoSuchDvd();
            }
        }
    }


    private void editDvd() throws DvdLibraryPersistenceException {
        boolean titleExists = false;
        String dvdTitle = null;
        Dvd dvd = null;

        view.displayEditMenuBanner();

        while(titleExists == false) {
            try {
                dvdTitle = view.getDvdTitleChoice();
                titleExists = service.titleExists(dvdTitle);

                if(titleExists == true){
                    dvd = service.getDvd(dvdTitle);
                } else{
                    view.displayNoSuchDvd();
                }

            } catch (NullPointerException e) {
                view.displayErrorMessage(e.getMessage());
            }
        }
//        dao.removeDvd(dvdTitle);
//        Dvd updatedDvd = view.displayEditMenuAndGetUpdates(dvd);
        view.displayEditMenuAndGetUpdates(dvd);
        service.editDvd(dvd);
//        dao.addDvd(updatedDvd.getTitle(), updatedDvd);
    }


    private void removeDvd() throws DvdLibraryPersistenceException {
        boolean titleExists = false;

        view.displayRemoveMenuBanner();
        while(titleExists == false) {
            String dvdTitle = view.getDvdTitleChoice();
            titleExists = service.titleExists(dvdTitle);
            if (titleExists == true) {
                service.removeDvd(dvdTitle);
                view.displayRemoveSuccessBanner();
            } else {
                view.displayNoSuchDvd();
            }
        }
    }


    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }


    private void exitMessage() {
        view.displayGoodBye();
    }

} // end DvdController
