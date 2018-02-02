package com.sg.dvdlibrary.ui;

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

}
