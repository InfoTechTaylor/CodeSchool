package com.sg.addressbook.dao;

import com.sg.addressbook.dto.Address;

public interface AddressBookDao {
    /**
     * Add the given contact name and address to the Address Book.
     *
     * @param lastName of contact at address
     * @param address as an Address object passed in to be stored
     * @return void
     */
    Address addAddress(String lastName, Address address );


    Address deleteAddress(String firstName, String lastName);


    Address getAddress(String lastName);


    Address getAddressCount();


    Address listAllAddresses();
}
