package com.sg.addressbook.ui;

import com.sg.addressbook.dto.Address;

import java.util.List;

public class AddressBookView {
    UserIO io = new UserIOConsoleImpl();

    public int printMenuAndGetSelection(){
        return io.readInt("\tPlease select the operation you wish to perform:\n" +
                "\t\t1. Add Address\n" +
                "\t\t2. Delete Address\n" +
                "\t\t3. Find Address\n" +
                "\t\t4. List Address Count\n" +
                "\t\t5. List All Addresses\n" +
                "\t\t6. Exit Program");

    }

    public Address getNewAddressInfo(){

        String firstName = io.readString("Please enter First Name: ");
        String lastName = io.readString("Please enter Last Name: ");
        String streetAddress = io.readString("Please enter Street Address: ");
        String city = io.readString("Please enter City: ");
        String state = io.readString("Please enter State: ");
        int zip = io.readInt("Please enter Zip Code: ");

        Address newAddress = new Address(firstName,lastName,streetAddress,city,state,zip);
        return newAddress;
    } // end getNewAddressInfo()


    public void displayCreateAddressBanner() {
        io.print("=== Add New Address === ");
    }


    public void displayAddSuccessBanner() {
        io.readString("Address added. Please hit enter to continue. ");
    }


    public void displayAddressList(List<Address> addressList){
        for (Address currentAddress : addressList){
            io.print(currentAddress.getFirstName() + " " + currentAddress.getLastName() + "\n" +
                        currentAddress.getStreetAddress() + "\n" +
                        currentAddress.getCity() + ", " + currentAddress.getState() + " " + currentAddress.getZip() + "\n\n");
        }

        io.readString("Please hit enter to continue.");
    }


    public void displayAllAddressesBanner() {
        io.print("=== List Addresses Menu ===");
    }
}
