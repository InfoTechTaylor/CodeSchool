package com.sg.addressbook.dao;

import com.sg.addressbook.dto.Address;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressBookDaoFileImpl implements AddressBookDao {

    private Map<String, Address> addresses = new HashMap<>();

    @Override
    public Address addAddress(String lastName, Address address) {
        Address newAddress = addresses.put(lastName, address);
        return newAddress;
    }

    @Override
    public Address deleteAddress(String lastName) {
        return null;
    }

    @Override
    public Address getAddress(String lastName) {
        return addresses.get(lastName);
    }

    @Override
    public Address getAddressCount() {
        return null;
    }

    @Override
    public List<Address> getAllAddresses() {
        return new ArrayList<Address>(addresses.values());
    }
}
