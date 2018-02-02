package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DvdLibraryDaoException;
import com.sg.dvdlibrary.dao.DvdLibraryDaoFileImpl;
import com.sg.dvdlibrary.dto.Dvd;
import com.sg.dvdlibrary.ui.DvdLibraryView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;

import java.util.List;

public class DvdController {

    private DvdLibraryView view = new DvdLibraryView();
    private DvdLibraryDaoFileImpl dao = new DvdLibraryDaoFileImpl();
    private UserIO io = new UserIOConsoleImpl();


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
                        io.print("GOOD BYE");
                        break;
                    default:
                        io.print("UNKNOWN COMMAND");
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
        view.displayEditMenuBanner();
        String dvdTitle = view.getDvdTitleChoice();
        Dvd dvd = dao.getDvd(dvdTitle);
        dao.removeDvd(dvdTitle);
        Dvd updatedDvd = view.displayEditMenuAndGetUpdates(dvd);
        dao.addDvd(updatedDvd.getTitle(), updatedDvd);
    }


    private void removeDvd() throws DvdLibraryDaoException {
        view.displayRemoveMenuBanner();
        String dvdTitle = view.getDvdTitleChoice();
        dao.removeDvd(dvdTitle);
        view.displayRemoveSuccessBanner();
    }

} // end DvdController
