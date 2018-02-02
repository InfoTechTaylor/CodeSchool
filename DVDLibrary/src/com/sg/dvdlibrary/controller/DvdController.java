package com.sg.dvdlibrary.controller;

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

        while(keepGoing){

            // get MenuSelection from user
            menuSelection = getMenuSelection();

            // determine action based of menuSelection
            switch(menuSelection){
                case 1:
                    createDvd();
                    break;
                case 2:
                    io.print("REMOVE DVD");
                    break;
                case 3:
                    io.print("EDIT DVD");
                    break;
                case 4:
                    displayDvdList();
                    break;
                case 5:
                    io.print("SEARCH FOR DVD");
                    break;
                case 6:
                    keepGoing = false;
                    io.print("GOOD BYE");
                    break;
                default:
                    io.print("UNKNOWN COMMAND");
            } // end switch(menuSelection)

        } // end while(keepGoing)

    } // end run()


    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }


    private void createDvd(){
        view.displayCreateDvdBanner();
        Dvd newDvd = view.getNewDvdInfo();
        dao.addDvd(newDvd.getTitle(), newDvd);
        view.displayCreateSuccessBanner();
    }


    public void displayDvdList() {
        view.displayDisplayAllBanner();
        List<Dvd> dvdList = dao.getAllDvds();
        view.displayDvdList(dvdList);
    }

} // end DvdController