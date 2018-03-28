package addressbookspringmvc.ui;


import addressbookspringmvc.dto.Address;

import java.util.List;

public class AddressBookView {
    UserIO io;

    public AddressBookView(UserIO io){
        this.io = io;
    }

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

        String firstName = io.readString("\tPlease enter First Name: ");
        String lastName = io.readString("\tPlease enter Last Name: ");
        String streetAddress = io.readString("\tPlease enter Street Address: ");
        String city = io.readString("\tPlease enter City: ");
        String state = io.readString("\tPlease enter State: ");
        String zip = io.readString("\tPlease enter Zip Code: ");

        Address newAddress = new Address(firstName,lastName,streetAddress,city,state,zip);
        return newAddress;
    } // end getNewAddressInfo()


    public void displayCreateAddressBanner() {
        io.print("Add Address Menu:");
    }


    public void displayAddSuccessBanner() {
        io.readString("\tAddress added. Please hit enter to continue. ");
    }


    public void displayAddressList(List<Address> addressList){
        for (Address currentAddress : addressList){
            io.print("\t" + currentAddress.getFirstName() + " " + currentAddress.getLastName() + "\n" +
                    "\t" + currentAddress.getStreetAddress() + "\n" +
                    "\t" + currentAddress.getCity() + ", " + currentAddress.getState() + " " + currentAddress.getZip() + "\n\n");
        }

        io.readString("\tPlease hit enter to continue.");
    }


    public void displayAllAddressesBanner() {
        io.print("List Addresses Menu:");
    }


    public void displayAddressBanner(){
        io.print("Find Address Menu:");
    }


    public String getAddressLastNameChoice(){
        return io.readString("\tPlease enter the last name of address to find: ");
    }


    public void displayAddress(Address currentAddress){
        if (currentAddress != null) {
            io.print("\t" + currentAddress.getFirstName() + " " + currentAddress.getLastName() + "\n" +
                    "\t" + currentAddress.getStreetAddress() + "\n" +
                    "\t" + currentAddress.getCity() + ", " + currentAddress.getState() + " " + currentAddress.getZip() + "\n\n");
        } else {
            io.print("No such address.");
        }
        io.readString("Please hit enter to continue.");
    }


    public void displayRemoveAddressBanner() {
        io.print("Delete Address Menu: ");
    }


    public void displayRemoveAddressSuccessBanner(){
        io.readString("Address successfully removed. Please hit enter to continue. ");
    }

    public void displayText(String message){
        io.print(message);
    }
}
