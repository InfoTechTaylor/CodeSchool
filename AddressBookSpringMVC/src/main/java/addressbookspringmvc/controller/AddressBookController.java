package addressbookspringmvc.controller;

import addressbookspringmvc.dao.AddressBookDao;
import addressbookspringmvc.dao.AddressBookDaoDBImpl;
import addressbookspringmvc.dao.AddressBookDaoFileImpl;
import addressbookspringmvc.dto.Address;
import addressbookspringmvc.ui.AddressBookView;
import addressbookspringmvc.ui.UserIO;
import addressbookspringmvc.ui.UserIOConsoleImpl;

import java.util.List;

public class AddressBookController {
    AddressBookView view;
    AddressBookDao dao;

    public AddressBookController(AddressBookView view, AddressBookDao dao){
        this.view = view;
        this.dao = dao;
    }

    // create a run method for all the main controller logic
    public void run(){
        boolean keepGoing = true;
        int menuChoice = 0;

        // run program until user chooses to exit, in which keepGoing will be set to false
        while(keepGoing){
            view.displayText("==========");
            view.displayText("Initial Menu:");

            menuChoice = getMenuChoice();

            switch(menuChoice){
                case 1:
                    createAddress();
                    break;
                case 2:
                    removeAddress();
                    break;
                case 3:
                    viewAddress();
                    break;
                case 4:
                    view.displayText("LIST ADDRESS COUNT");
                    break;
                case 5:
                    getAllAddresses();
                    break;
                case 6:
                    keepGoing = false;
                    break;
            }

        } // end while of run()
    } // end run


    private int getMenuChoice(){
        return view.printMenuAndGetSelection();
    }

    private void createAddress() {
        view.displayCreateAddressBanner();
        Address newAddress = view.getNewAddressInfo();
        dao.addAddress(newAddress.getLastName(), newAddress);
        view.displayAddSuccessBanner();
    }

    private void getAllAddresses() {
        view.displayAllAddressesBanner();
        List<Address> addressList = dao.getAllAddresses();
        view.displayAddressList(addressList);
    }


    private void viewAddress() {
        view.displayAddressBanner();
        String addressLastName = view.getAddressLastNameChoice();
        Address address = dao.getAddress(addressLastName);
        view.displayAddress(address);
    }


    private void removeAddress(){
        view.displayRemoveAddressBanner();
        String lastName = view.getAddressLastNameChoice();
        Address fromDao = dao.getAddress(lastName);
        dao.deleteAddress(fromDao.getAddressId());
        view.displayRemoveAddressSuccessBanner();
    }
}
