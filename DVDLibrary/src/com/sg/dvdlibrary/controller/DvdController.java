package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DvdLibraryDao;
import com.sg.dvdlibrary.dao.DvdLibraryDaoException;
import com.sg.dvdlibrary.dto.Dvd;
import com.sg.dvdlibrary.ui.DvdLibraryView;
import java.util.List;

public class DvdController {

    DvdLibraryDao dao;
    DvdLibraryView view;


    public DvdController(DvdLibraryDao dao, DvdLibraryView view) {
        this.dao = dao;
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

        } catch (DvdLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    } // end run()


    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }


    private void createDvd() throws DvdLibraryDaoException {
        view.displayCreateDvdBanner();
        Dvd newDvd = view.getNewDvdInfo();
        dao.addDvd(newDvd.getTitle(), newDvd);
        view.displayCreateSuccessBanner();
    }


    private void displayDvdList() throws DvdLibraryDaoException {
        view.displayDisplayAllBanner();
        List<Dvd> dvdList = dao.getAllDvds();
        view.displayDvdList(dvdList);
    }


    private void viewDvd() throws DvdLibraryDaoException {
        view.displayDisplayDvdBanner();
        String dvdTitle = view.getDvdTitleChoice();
        Dvd dvd = dao.getDvd(dvdTitle);
        view.displayDvd(dvd);
    }


    private void editDvd() throws DvdLibraryDaoException {
        boolean titleNotFound = true;
        String dvdTitle = null;
        Dvd dvd = null;

        view.displayEditMenuBanner();

        while(titleNotFound) {
            try {
                dvdTitle = view.getDvdTitleChoice();
                dvd = dao.getDvd(dvdTitle);
                if(dvd != null){
                    titleNotFound = false;
                }

            } catch (NullPointerException e) {
                view.displayErrorMessage(e.getMessage());
            }
        }
        dao.removeDvd(dvdTitle);
        Dvd updatedDvd = view.displayEditMenuAndGetUpdates(dvd);
        dao.addDvd(updatedDvd.getTitle(), updatedDvd);
    }


    private void removeDvd() throws DvdLibraryDaoException {
        view.displayRemoveMenuBanner();
        String dvdTitle = view.getDvdTitleChoice();
        Dvd removedDvd = dao.removeDvd(dvdTitle);
        if (removedDvd != null) {
            view.displayRemoveSuccessBanner();
        } else {
            view.displayNoSuchDvd();
        }
    }


    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }


    private void exitMessage() {
        view.displayGoodBye();
    }

} // end DvdController
