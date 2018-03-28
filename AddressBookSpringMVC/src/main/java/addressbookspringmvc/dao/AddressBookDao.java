package addressbookspringmvc.dao;



import addressbookspringmvc.dto.Address;

import java.util.List;

public interface AddressBookDao {
    /**
     * Add the given contact name and address to the Address Book.
     *
     * @param lastName of contact at address
     * @param address as an Address object passed in to be stored
     * @return void
     */
    Address addAddress(String lastName, Address address);


    Address deleteAddress(int dvdId);


    Address getAddress(String lastName);


    int getAddressCount();


    List<Address> getAllAddresses();
}
