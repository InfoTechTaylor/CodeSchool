package com.sg.addressbook.dao;

import com.sg.addressbook.dto.Address;

import java.util.HashMap;
import java.util.Map;

public class AddressBookDaoFileImpl implements AddressBookDao {

    private Map<String, Address> addresses = new HashMap<>();

    @Override
    public Address addAddress(String lastName, Address address) {
        Address newAddress = addresses.put(lastName, address);
        return newAddress;
    }

    @Override
    public Address deleteAddress(String firstName, String lastName) {
        return null;
    }

    @Override
    public Address getAddress(String lastName) {
        return null;
    }

    @Override
    public Address getAddressCount() {
        return null;
    }

    @Override
    public Address listAllAddresses() {
        return null;
    }
}
