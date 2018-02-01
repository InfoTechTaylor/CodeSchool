package com.sg.addressbook.controller;

import com.sg.addressbook.ui.UserIO;
import com.sg.addressbook.ui.UserIOConsoleImpl;

public class AddressBookController {

    // instantiate object of UserIOConsoleImpl in order to interact with user
    private UserIO io = new UserIOConsoleImpl();

    // create a run method for all the main controller logic
    public void run(){
        boolean keepGoing = true;
        int menuChoice = 0;

        // run program until user chooses to exit, in which keepGoing will be set to false
        while(keepGoing){
            io.print("==========");
            io.print("Initial Menu:");
            io.print("\tPlease select the operation you wish to perform:");
            io.print("\t\t1. Add Address");
            io.print("\t\t2. Delete Address");
            io.print("\t\t3. Find Address");
            io.print("\t\t4. List Address Count");
            io.print("\t\t5. List All Addresses\n");

        }
    }
}
