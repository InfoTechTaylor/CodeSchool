package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;

public class DvdController {

    private UserIO io = new UserIOConsoleImpl();


    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;

        while(keepGoing){
            io.print("Main Menu:");
            io.print("\t1. Add a DVD to the collection");
            io.print("\t2. Remove a DVD from the collection");
            io.print("\t3. Edit the information for an existing DVD in the collection");
            io.print("\t4. List all DVDs in the collection");
            io.print("\t5. Search for a DVD by title");
            io.print("\t6. Exit program");

            menuSelection = io.readInt("Please select one of the above options: ");

            // determine action based of menuSelection
            switch(menuSelection){
                case 1:
                    io.print("ADD DVD");
                    break;
                case 2:
                    io.print("REMOVE DVD");
                    break;
                case 3:
                    io.print("EDIT DVD");
                    break;
                case 4:
                    io.print("LIST ALL DVDs");
                    break;
                case 5:
                    io.print("SEARCH FOR DVD");
                    break;
                case 6:
                    keepGoing = false;
                    io.print("GOOD BYE");
                default:
                    io.print("UNKNOWN COMMAND");
            } // end switch(menuSelection)

        } // end while(keepGoing)

    } // end run()

} // end DvdController
